// 定义这个类所在的包路径
package com.my.blog.website.modal.Vo;

// 导入集合框架相关的类
// ArrayList是动态数组实现，List是集合接口
import java.util.ArrayList;
import java.util.List;

/**
 * 内容查询条件示例类 (ContentVo Example)
 * 用于构建文章内容的复杂数据库查询条件
 * 对应ContentVo对象的动态SQL查询构建
 * 知识点：Example类是MyBatis Generator的核心组件，用于替代手写SQL
 */
public class ContentVoExample {
    /**
     * ORDER BY子句 - SQL排序条件
     * 示例："created DESC, hits DESC" 按创建时间降序，点击量降序
     * protected修饰符：同包类或子类可以访问
     */
    protected String orderByClause;

    /**
     * 去重标志 - 对应SQL的DISTINCT关键字
     * 用于去除查询结果中的重复记录
     */
    protected boolean distinct;

    /**
     * OR条件列表 - 存储所有用OR连接的查询条件组
     * List<Criteria>使用泛型确保类型安全
     * 知识点：每个Criteria代表一组用AND连接的条件
     */
    protected List<Criteria> oredCriteria;

    /**
     * 查询限制条数 - 对应SQL的LIMIT子句
     * 用于分页查询，限制返回记录数量
     */
    private Integer limit;

    /**
     * 查询偏移量 - 对应SQL的OFFSET子句
     * 用于分页查询，指定跳过的记录数
     */
    private Integer offset;

    /**
     * 构造函数 - 初始化Example对象
     * 创建空的OR条件列表，为后续条件构建做准备
     */
    public ContentVoExample() {
        // 初始化条件列表为空ArrayList
        // ArrayList初始容量为10，随着元素添加自动扩容
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序子句
     * @param orderByClause 排序条件字符串
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取当前排序子句
     * @return 排序条件字符串，可能为null
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置是否去重
     * @param distinct true表示去重，false表示不去重
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 判断是否设置去重
     * @return 去重标志状态
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取所有OR条件组
     * @return OR条件组列表
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 添加现有的条件组（OR连接）
     * @param criteria 已构建的条件组对象
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 创建并添加新的条件组（OR连接）
     * @return 新创建的条件组对象，用于链式调用
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 创建查询条件组（主要入口方法）
     * 如果当前没有条件组，自动创建并添加一个
     * @return 条件组对象
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 创建条件组内部方法
     * @return 新的条件组对象
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清空所有查询条件
     * 重置查询构建器状态
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 设置查询限制条数
     * @param limit 最大返回记录数
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 获取查询限制条数
     * @return 限制条数
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * 设置查询偏移量
     * @param offset 跳过的记录数
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * 获取查询偏移量
     * @return 偏移量
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 抽象静态内部类 - 条件生成基类
     * 封装所有字段的通用查询条件方法
     * abstract表示不能直接实例化
     * static表示不依赖外部类实例
     */
    protected abstract static class GeneratedCriteria {
        /**
         * 条件元素列表
         * 存储具体的查询条件，每个Criterion代表一个条件
         */
        protected List<Criterion> criteria;

        /**
         * 构造函数 - 初始化条件列表
         */
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        /**
         * 判断是否有有效条件
         * @return 条件列表不为空返回true
         */
        public boolean isValid() {
            return criteria.size() > 0;
        }

        /**
         * 获取所有条件元素
         * @return 条件元素列表
         */
        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        /**
         * 获取条件列表
         * @return 条件元素列表
         */
        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**
         * 添加无值条件
         * @param condition 条件字符串
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * 添加单值条件
         * @param condition 条件字符串
         * @param value 条件值
         * @param property 属性名
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 添加范围条件
         * @param condition 条件字符串
         * @param value1 起始值
         * @param value2 结束值
         * @param property 属性名
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        // ========== 文章ID字段条件方法 ==========

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        // ========== 文章标题字段条件方法 ==========

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        /**
         * 添加标题模糊匹配条件
         * @param value 模糊匹配模式
         * @return 条件对象
         */
        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        // ========== 文章缩略名字段条件方法 ==========

        public Criteria andSlugIsNull() {
            addCriterion("slug is null");
            return (Criteria) this;
        }

        public Criteria andSlugIsNotNull() {
            addCriterion("slug is not null");
            return (Criteria) this;
        }

        public Criteria andSlugEqualTo(String value) {
            addCriterion("slug =", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotEqualTo(String value) {
            addCriterion("slug <>", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugGreaterThan(String value) {
            addCriterion("slug >", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugGreaterThanOrEqualTo(String value) {
            addCriterion("slug >=", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLessThan(String value) {
            addCriterion("slug <", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLessThanOrEqualTo(String value) {
            addCriterion("slug <=", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLike(String value) {
            addCriterion("slug like", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotLike(String value) {
            addCriterion("slug not like", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugIn(List<String> values) {
            addCriterion("slug in", values, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotIn(List<String> values) {
            addCriterion("slug not in", values, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugBetween(String value1, String value2) {
            addCriterion("slug between", value1, value2, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotBetween(String value1, String value2) {
            addCriterion("slug not between", value1, value2, "slug");
            return (Criteria) this;
        }

        // ========== 创建时间字段条件方法 ==========

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Integer value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Integer value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Integer value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Integer value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Integer value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Integer> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Integer> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        // ========== 修改时间字段条件方法 ==========

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Integer value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Integer value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Integer value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Integer value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Integer value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Integer value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Integer> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Integer> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Integer value1, Integer value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Integer value1, Integer value2) {
            addCriterion("modified not between", value1, value2, "modified");
            return (Criteria) this;
        }

        // ========== 作者ID字段条件方法 ==========

        public Criteria andAuthorIdIsNull() {
            addCriterion("author_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIdIsNotNull() {
            addCriterion("author_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorIdEqualTo(Integer value) {
            addCriterion("author_id =", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotEqualTo(Integer value) {
            addCriterion("author_id <>", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdGreaterThan(Integer value) {
            addCriterion("author_id >", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("author_id >=", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdLessThan(Integer value) {
            addCriterion("author_id <", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdLessThanOrEqualTo(Integer value) {
            addCriterion("author_id <=", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdIn(List<Integer> values) {
            addCriterion("author_id in", values, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotIn(List<Integer> values) {
            addCriterion("author_id not in", values, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdBetween(Integer value1, Integer value2) {
            addCriterion("author_id between", value1, value2, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("author_id not between", value1, value2, "authorId");
            return (Criteria) this;
        }

        // ========== 内容类型字段条件方法 ==========

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        // ========== 内容状态字段条件方法 ==========

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        // ========== 标签字段条件方法 ==========

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        // ========== 分类字段条件方法 ==========

        public Criteria andCategoriesIsNull() {
            addCriterion("categories is null");
            return (Criteria) this;
        }

        public Criteria andCategoriesIsNotNull() {
            addCriterion("categories is not null");
            return (Criteria) this;
        }

        public Criteria andCategoriesEqualTo(String value) {
            addCriterion("categories =", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotEqualTo(String value) {
            addCriterion("categories <>", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThan(String value) {
            addCriterion("categories >", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThanOrEqualTo(String value) {
            addCriterion("categories >=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThan(String value) {
            addCriterion("categories <", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThanOrEqualTo(String value) {
            addCriterion("categories <=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLike(String value) {
            addCriterion("categories like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotLike(String value) {
            addCriterion("categories not like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesIn(List<String> values) {
            addCriterion("categories in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotIn(List<String> values) {
            addCriterion("categories not in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesBetween(String value1, String value2) {
            addCriterion("categories between", value1, value2, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotBetween(String value1, String value2) {
            addCriterion("categories not between", value1, value2, "categories");
            return (Criteria) this;
        }

        // ========== 点击数字段条件方法 ==========

        public Criteria andHitsIsNull() {
            addCriterion("hits is null");
            return (Criteria) this;
        }

        public Criteria andHitsIsNotNull() {
            addCriterion("hits is not null");
            return (Criteria) this;
        }

        public Criteria andHitsEqualTo(Integer value) {
            addCriterion("hits =", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotEqualTo(Integer value) {
            addCriterion("hits <>", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThan(Integer value) {
            addCriterion("hits >", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hits >=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThan(Integer value) {
            addCriterion("hits <", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThanOrEqualTo(Integer value) {
            addCriterion("hits <=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsIn(List<Integer> values) {
            addCriterion("hits in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotIn(List<Integer> values) {
            addCriterion("hits not in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsBetween(Integer value1, Integer value2) {
            addCriterion("hits between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotBetween(Integer value1, Integer value2) {
            addCriterion("hits not between", value1, value2, "hits");
            return (Criteria) this;
        }

        // ========== 评论数字段条件方法 ==========

        public Criteria andCommentsNumIsNull() {
            addCriterion("comments_num is null");
            return (Criteria) this;
        }

        public Criteria andCommentsNumIsNotNull() {
            addCriterion("comments_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsNumEqualTo(Integer value) {
            addCriterion("comments_num =", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumNotEqualTo(Integer value) {
            addCriterion("comments_num <>", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumGreaterThan(Integer value) {
            addCriterion("comments_num >", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments_num >=", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumLessThan(Integer value) {
            addCriterion("comments_num <", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumLessThanOrEqualTo(Integer value) {
            addCriterion("comments_num <=", value, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumIn(List<Integer> values) {
            addCriterion("comments_num in", values, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumNotIn(List<Integer> values) {
            addCriterion("comments_num not in", values, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumBetween(Integer value1, Integer value2) {
            addCriterion("comments_num between", value1, value2, "commentsNum");
            return (Criteria) this;
        }

        public Criteria andCommentsNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comments_num not between", value1, value2, "commentsNum");
            return (Criteria) this;
        }

        // ========== 允许评论字段条件方法 ==========

        public Criteria andAllowCommentIsNull() {
            addCriterion("allow_comment is null");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIsNotNull() {
            addCriterion("allow_comment is not null");
            return (Criteria) this;
        }

        /**
         * 添加允许评论等于条件
         * @param value Boolean值
         * @return 条件对象
         * 知识点：Boolean是包装类，可以存储null值
         */
        public Criteria andAllowCommentEqualTo(Boolean value) {
            addCriterion("allow_comment =", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotEqualTo(Boolean value) {
            addCriterion("allow_comment <>", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThan(Boolean value) {
            addCriterion("allow_comment >", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment >=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThan(Boolean value) {
            addCriterion("allow_comment <", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment <=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIn(List<Boolean> values) {
            addCriterion("allow_comment in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotIn(List<Boolean> values) {
            addCriterion("allow_comment not in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment between", value1, value2, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment not between", value1, value2, "allowComment");
            return (Criteria) this;
        }

        // ========== 允许Ping字段条件方法 ==========

        public Criteria andAllowPingIsNull() {
            addCriterion("allow_ping is null");
            return (Criteria) this;
        }

        public Criteria andAllowPingIsNotNull() {
            addCriterion("allow_ping is not null");
            return (Criteria) this;
        }

        public Criteria andAllowPingEqualTo(Boolean value) {
            addCriterion("allow_ping =", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingNotEqualTo(Boolean value) {
            addCriterion("allow_ping <>", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingGreaterThan(Boolean value) {
            addCriterion("allow_ping >", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_ping >=", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingLessThan(Boolean value) {
            addCriterion("allow_ping <", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_ping <=", value, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingIn(List<Boolean> values) {
            addCriterion("allow_ping in", values, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingNotIn(List<Boolean> values) {
            addCriterion("allow_ping not in", values, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_ping between", value1, value2, "allowPing");
            return (Criteria) this;
        }

        public Criteria andAllowPingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_ping not between", value1, value2, "allowPing");
            return (Criteria) this;
        }

        // ========== 允许Feed字段条件方法 ==========

        public Criteria andAllowFeedIsNull() {
            addCriterion("allow_feed is null");
            return (Criteria) this;
        }

        public Criteria andAllowFeedIsNotNull() {
            addCriterion("allow_feed is not null");
            return (Criteria) this;
        }

        public Criteria andAllowFeedEqualTo(Boolean value) {
            addCriterion("allow_feed =", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedNotEqualTo(Boolean value) {
            addCriterion("allow_feed <>", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedGreaterThan(Boolean value) {
            addCriterion("allow_feed >", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_feed >=", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedLessThan(Boolean value) {
            addCriterion("allow_feed <", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_feed <=", value, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedIn(List<Boolean> values) {
            addCriterion("allow_feed in", values, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedNotIn(List<Boolean> values) {
            addCriterion("allow_feed not in", values, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_feed between", value1, value2, "allowFeed");
            return (Criteria) this;
        }

        public Criteria andAllowFeedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_feed not between", value1, value2, "allowFeed");
            return (Criteria) this;
        }
    }

    /**
     * 具体条件类 - 继承GeneratedCriteria
     * 提供扩展点，可以在子类中添加业务特定的条件方法
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * 受保护的构造函数
         * 确保只能通过外部类的createCriteria方法创建实例
         */
        protected Criteria() {
            super();
        }
    }

    /**
     * 条件元素类 - 封装单个查询条件
     * 包含条件字符串、值、类型等信息
     * 知识点：这是一个不可变的值对象
     */
    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}