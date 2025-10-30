package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.dao.AttachVoMapper;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.AttachVoExample;
import com.my.blog.website.service.IAttachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附件服务实现类，负责处理附件相关的业务逻辑（增删改查等），依赖AttachVoMapper与数据库交互
 * Created by wangq on 2017/3/20.
 */
@Service
public class AttachServiceImpl implements IAttachService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachServiceImpl.class);

    @Resource
    private AttachVoMapper attachDao; // 附件数据访问对象，用于数据库操作

    @Override
    public PageInfo<AttachVo> getAttachs(Integer page, Integer limit) {
        // 分页查询附件列表，按ID降序排列
        PageHelper.startPage(page, limit);
        AttachVoExample attachVoExample = new AttachVoExample();
        attachVoExample.setOrderByClause("id desc");
        List<AttachVo> attachVos = attachDao.selectByExample(attachVoExample);
        return new PageInfo<>(attachVos);
    }

    @Override
    public AttachVo selectById(Integer id) {
        // 根据ID查询附件信息，ID为null时返回null
        if(null != id){
            return attachDao.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public void save(String fname, String fkey, String ftype, Integer author) {
        // 保存新附件信息，包括文件名、存储键、类型和作者ID，并设置创建时间
        AttachVo attach = new AttachVo();
        attach.setFname(fname);
        attach.setAuthorId(author);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachDao.insertSelective(attach);
    }

    @Override
    public void deleteById(Integer id) {
        // 通过ID删除附件，ID为null时不执行操作
        if (null != id) {
            attachDao.deleteByPrimaryKey( id);
        }
    }
}