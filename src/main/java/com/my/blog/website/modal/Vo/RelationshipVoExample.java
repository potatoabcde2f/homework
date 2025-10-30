package com.my.blog.website.modal.Vo;

import java.util.ArrayList;
import java.util.List;

/**MyBatis Generator自动生成的Example类，用于构建relationship表的动态查询条件
 * 主要用于构建文章与分类/标签的多对多关系查询
 */
public class RelationshipVoExample {
    // ORDER BY 子句，用于排序
    protected String orderByClause;

    // 是否去重查询
    protected boolean distinct;

    // 查询条件列表，可以包含多个OR条件
    protected List<Criteria> oredCriteria;

    // 分页限制，限制返回记录数
    private Integer limit;

    // 分页偏移量，用于分页查询
    private Integer offset;

    /**
     * 构造函数，初始化查询条件列表
     */
    public RelationshipVoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    // ================ 基本getter/setter方法 ================

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 添加OR条件
     * @param criteria 已创建的Criteria对象
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**创建OR条件并返回，用于链式调用
     * @return 新创建的Criteria对象
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**创建查询条件
     * 如果当前没有条件，则添加一个默认条件
     * @return Criteria对象
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 内部创建Criteria对象
     * @return 新创建的Criteria对象
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清空所有查询条件、排序和去重设置
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    // ================ 分页相关方法 ================

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * 抽象的GeneratedCriteria类
     * 包含所有字段的查询条件方法，由MyBatis Generator自动生成
     */
    protected abstract static class GeneratedCriteria {
        // 条件列表，存储所有的查询条件
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        /**
         * 判断是否有有效条件
         * @return 如果有条件返回true，否则返回false
         */
        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**
         * 添加无条件查询条件（如IS NULL, IS NOT NULL）
         * @param condition 条件表达式
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

/**
 * 添加单值查询条件
 * @param condition 条件表达式
 * @param value 条件值
 * @param property 属性