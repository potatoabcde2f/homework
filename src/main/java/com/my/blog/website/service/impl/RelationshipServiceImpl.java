package com.my.blog.website.service.impl;

import com.my.blog.website.modal.Vo.RelationshipVoExample;
import com.my.blog.website.modal.Vo.RelationshipVoKey;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.dao.RelationshipVoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关联关系服务实现类
 * 负责处理文章（cid）与元数据（mid，如分类、标签）之间的关联关系管理，包括增删查、统计等操作
 * Created by BlueT on 2017/3/18.
 */
@Service
public class RelationshipServiceImpl implements IRelationshipService {
    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipServiceImpl.class);

    // 关系数据访问对象，用于与数据库交互执行关联关系的CRUD操作
    @Resource
    private RelationshipVoMapper relationshipVoMapper;

    /**
     * 根据文章ID和元数据ID删除关联关系
     * 支持单条件删除（仅传cid或仅传mid）或组合条件删除（同时传cid和mid）
     * @param cid 文章ID（可为null）
     * @param mid 元数据ID（可为null）
     */
    @Override
    public void deleteById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        // 若文章ID非空，添加文章ID查询条件
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        // 若元数据ID非空，添加元数据ID查询条件
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        // 按条件删除关联关系
        relationshipVoMapper.deleteByExample(relationshipVoExample);
    }

    /**
     * 根据文章ID和元数据ID查询关联关系列表
     * 支持单条件查询（仅查某文章的所有关联，或某元数据的所有关联）或组合条件查询
     * @param cid 文章ID（可为null）
     * @param mid 元数据ID（可为null）
     * @return 符合条件的关联关系列表
     */
    @Override
    public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        // 若文章ID非空，添加文章ID查询条件
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        // 若元数据ID非空，添加元数据ID查询条件
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        // 按条件查询关联关系列表
        return relationshipVoMapper.selectByExample(relationshipVoExample);
    }

    /**
     * 插入文章与元数据的关联关系
     * 将文章ID和元数据ID的关联记录插入数据库
     * @param relationshipVoKey 关联关系对象（包含cid和mid）
     */
    @Override
    public void insertVo(RelationshipVoKey relationshipVoKey) {
        relationshipVoMapper.insert(relationshipVoKey);
    }

    /**
     * 统计符合条件的关联关系数量
     * 支持统计某文章的关联数、某元数据的关联数，或某文章与某元数据的关联是否存在
     * @param cid 文章ID（可为null）
     * @param mid 元数据ID（可为null）
     * @return 符合条件的关联关系总数
     */
    @Override
    public Long countById(Integer cid, Integer mid) {
        LOGGER.debug("Enter countById method:cid={},mid={}",cid,mid);
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        // 若文章ID非空，添加文章ID查询条件
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        // 若元数据ID非空，添加元数据ID查询条件
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        // 按条件统计关联关系数量
        long num = relationshipVoMapper.countByExample(relationshipVoExample);
        LOGGER.debug("Exit countById method return num={}",num);
        return num;
    }
}