package com.my.blog.website.service.impl;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.MetaVo;
import com.my.blog.website.modal.Vo.RelationshipVoKey;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.dao.MetaVoMapper;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.MetaVoExample;
import com.my.blog.website.service.IContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 元数据服务实现类
 * 负责处理分类（category）和标签（tag）等元数据的CRUD、与文章的关联管理等业务逻辑
 * 依赖元数据DAO、关系服务和内容服务实现关联操作
 * Created by BlueT on 2017/3/17.
 */
@Service
public class MetaServiceImpl implements IMetaService {
    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaServiceImpl.class);

    // 元数据数据访问对象，用于元数据相关的数据库操作
    @Resource
    private MetaVoMapper metaDao;

    // 关系服务，用于处理文章与元数据（分类/标签）的关联关系
    @Resource
    private IRelationshipService relationshipService;

    // 内容服务，用于关联文章的查询与更新操作
    @Resource
    private IContentService contentService;

    /**
     * 根据类型和名称获取元数据DTO
     * 用于查询指定类型（分类/标签）和名称的元数据详情（包含关联文章数等扩展信息）
     * @param type 元数据类型（如分类Types.CATEGORY、标签Types.TAG）
     * @param name 元数据名称（如分类名、标签名）
     * @return 元数据DTO对象，查询条件不完整时返回null
     */
    @Override
    public MetaDto getMeta(String type, String name) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            return metaDao.selectDtoByNameAndType(name, type);
        }
        return null;
    }

    /**
     * 统计元数据关联的文章数量
     * 用于查询指定元数据（分类/标签）关联的文章总数
     * @param mid 元数据ID
     * @return 关联的文章数量
     */
    @Override
    public Integer countMeta(Integer mid) {
        return metaDao.countWithSql(mid);
    }

    /**
     * 根据类型获取元数据列表
     * 查询指定类型的所有元数据，按排序字段和ID降序排列
     * @param types 元数据类型（如分类Types.CATEGORY、标签Types.TAG）
     * @return 元数据列表，类型为空时返回null
     */
    @Override
    public List<MetaVo> getMetas(String types) {
        if (StringUtils.isNotBlank(types)) {
            MetaVoExample metaVoExample = new MetaVoExample();
            // 按排序字段降序并ID降序排列
            metaVoExample.setOrderByClause("sort desc, mid desc");
            metaVoExample.createCriteria().andTypeEqualTo(types);
            return metaDao.selectByExample(metaVoExample);
        }
        return null;
    }

    /**
     * 按条件分页获取元数据列表
     * 支持按类型、排序方式和数量限制查询元数据，返回包含关联文章数的DTO列表
     * @param type 元数据类型
     * @param orderby 排序方式（默认按文章数降序、ID降序）
     * @param limit 最大返回数量（默认10，不超过系统配置最大值）
     * @return 元数据DTO列表，类型为空时返回null
     */
    @Override
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        if (StringUtils.isNotBlank(type)) {
            // 处理默认的排序
            if (StringUtils.isBlank(orderby)) {
                orderby = "count desc, a.mid desc";
            }
            // 处理数量限制（默认10，不超过系统最大配置）
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            // 封装查询参数
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderby);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    /**
     * 删除元数据
     * 1. 删除元数据本身
     * 2. 更新关联文章的分类/标签信息（移除该元数据）
     * 3. 删除文章与该元数据的关联关系
     * @param mid 元数据ID
     */
    @Override
    public void delete(int mid) {
        MetaVo metas = metaDao.selectByPrimaryKey(mid);
        if (null != metas) {
            String type = metas.getType(); // 元数据类型（分类/标签）
            String name = metas.getName(); // 元数据名称

            // 删除元数据
            metaDao.deleteByPrimaryKey(mid);

            // 查询该元数据关联的所有文章关系
            List<RelationshipVoKey> rlist = relationshipService.getRelationshipById(null, mid);
            if (null != rlist) {
                for (RelationshipVoKey r : rlist) {
                    // 获取关联的文章
                    ContentVo contents = contentService.getContents(String.valueOf(r.getCid()));
                    if (null != contents) {
                        ContentVo temp = new ContentVo();
                        temp.setCid(r.getCid());
                        // 若为分类，更新文章的分类信息（移除当前分类）
                        if (type.equals(Types.CATEGORY.getType())) {
                            temp.setCategories(reMeta(name, contents.getCategories()));
                        }
                        // 若为标签，更新文章的标签信息（移除当前标签）
                        if (type.equals(Types.TAG.getType())) {
                            temp.setTags(reMeta(name, contents.getTags()));
                        }
                        contentService.updateContentByCid(temp);
                    }
                }
            }
            // 删除文章与该元数据的所有关联关系
            relationshipService.deleteById(null, mid);
        }
    }

    /**
     * 保存或更新元数据（分类/标签）
     * 1. 校验元数据是否已存在（同类型同名称不可重复）
     * 2. 若为更新（指定mid），更新元数据并同步关联文章的分类/标签名称
     * 3. 若为新增，创建新元数据
     * @param type 元数据类型
     * @param name 元数据名称
     * @param mid 元数据ID（更新时传入，新增时为null）
     */
    @Override
    public void saveMeta(String type, String name, Integer mid) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            // 校验同类型同名称的元数据是否已存在
            MetaVoExample metaVoExample = new MetaVoExample();
            metaVoExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
            List<MetaVo> metaVos = metaDao.selectByExample(metaVoExample);
            MetaVo metas;
            if (metaVos.size() != 0) {
                throw new TipException("已经存在该项");
            } else {
                metas = new MetaVo();
                metas.setName(name);
                // 若指定mid，则为更新操作
                if (null != mid) {
                    MetaVo original = metaDao.selectByPrimaryKey(mid);
                    metas.setMid(mid);
                    metaDao.updateByPrimaryKeySelective(metas);
                    // 同步更新关联文章的分类名称（标签无需此操作，因标签通过名称关联）
                    contentService.updateCategory(original.getName(), name);
                } else {
                    // 新增元数据
                    metas.setType(type);
                    metaDao.insertSelective(metas);
                }
            }
        }
    }

    /**
     * 批量保存文章与元数据的关联
     * 为指定文章绑定多个元数据（如给文章添加多个标签或分类），自动处理元数据的创建与关联关系
     * @param cid 文章ID
     * @param names 元数据名称集合（逗号分隔，如"标签1,标签2"）
     * @param type 元数据类型
     */
    @Override
    public void saveMetas(Integer cid, String names, String type) {
        if (null == cid) {
            throw new TipException("项目关联id不能为空");
        }
        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            // 拆分元数据名称为数组
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                // 处理单个元数据与文章的关联（新增元数据或关联已有元数据）
                this.saveOrUpdate(cid, name, type);
            }
        }
    }

    /**
     * 保存或更新文章与单个元数据的关联
     * 1. 若元数据已存在，直接关联文章与元数据
     * 2. 若元数据不存在，先创建元数据，再关联文章与元数据
     * @param cid 文章ID
     * @param name 元数据名称
     * @param type 元数据类型
     */
    private void saveOrUpdate(Integer cid, String name, String type) {
        // 查询同类型同名称的元数据
        MetaVoExample metaVoExample = new MetaVoExample();
        metaVoExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
        List<MetaVo> metaVos = metaDao.selectByExample(metaVoExample);

        int mid;
        MetaVo metas;
        if (metaVos.size() == 1) {
            // 元数据已存在，获取其ID
            metas = metaVos.get(0);
            mid = metas.getMid();
        } else if (metaVos.size() > 1) {
            // 存在多条同名同类型元数据，抛出异常
            throw new TipException("查询到多条数据");
        } else {
            // 元数据不存在，创建新元数据
            metas = new MetaVo();
            metas.setSlug(name);
            metas.setName(name);
            metas.setType(type);
            metaDao.insertSelective(metas);
            mid = metas.getMid();
        }
        // 若元数据ID有效，且文章与元数据未关联，则创建关联关系
        if (mid != 0) {
            Long count = relationshipService.countById(cid, mid);
            if (count == 0) {
                RelationshipVoKey relationships = new RelationshipVoKey();
                relationships.setCid(cid);
                relationships.setMid(mid);
                relationshipService.insertVo(relationships);
            }
        }
    }

    /**
     * 从元数据字符串中移除指定名称
     * 用于删除元数据后，更新文章的分类/标签字符串（移除已删除的元数据名称）
     * @param name 要移除的元数据名称
     * @param metas 原元数据字符串（逗号分隔，如"标签1,标签2"）
     * @return 处理后的元数据字符串
     */
    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            // 拼接未被删除的元数据名称
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        // 移除开头的逗号（若有）
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }

    /**
     * 保存元数据（直接使用MetaVo对象）
     * 将传入的元数据对象插入到数据库
     * @param metas 元数据对象
     */
    @Override
    public void saveMeta(MetaVo metas) {
        if (null != metas) {
            metaDao.insertSelective(metas);
        }
    }

    /**
     * 更新元数据
     * 验证元数据对象及ID非空后，选择性更新元数据（只更新非空字段）
     * @param metas 包含更新信息的元数据对象（需包含元数据ID）
     */
    @Override
    public void update(MetaVo metas) {
        if (null != metas && null != metas.getMid()) {
            metaDao.updateByPrimaryKeySelective(metas);
        }
    }
}