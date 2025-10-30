// 包声明
package com.my.blog.website.modal.Vo;

// 导入集合类
import java.util.ArrayList;
import java.util.List;

/**元数据查询条件示例类
 * 用于构建分类、标签等元数据的数据库查询条件
 * 对应MetaVo对象的动态SQL查询构建
 */
public class MetaVoExample {
    // 排序子句，用于SQL的ORDER BY
    protected String orderByClause;

    // 去重标志，对应SQL的DISTINCT
    protected boolean distinct;

    // OR条件列表，存储多个用OR连接的查询条件组
    protected List<Criteria> oredCriteria;

    // 查询限制条数，对应SQL的LIMIT
    private Integer limit;

    // 查询偏移量，对应SQL的OFFSET
    private Integer offset;

    //构造函数，初始化条件列表
    public MetaVoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    //设置排序子句
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    //获取排序子句
    public String getOrderByClause() {
        return orderByClause;
    }

    //设置是否去重
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    //判断是否去重
    public boolean isDistinct() {
        return distinct;
    }

    //获取OR条件列表
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    //添加现有的条件组
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    //创建并添加新的条件组
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    //创建查询条件组
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    //创建条件组内部方法
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    //清空所有查询条件
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    //设置查询限制条数
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    //获取查询限制条数
    public Integer getLimit() {
        return limit;
    }

    //设置查询偏移量
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    //获取查询偏移量
    public Integer getOffset() {
        return offset;
    }

    /**抽象静态内部类 - 条件生成基类
     * 封装所有元数据字段的查询条件方法
     */
    protected abstract static class GeneratedCriteria {
        // 条件元素列表
        protected List<Criterion> criteria;

        //构造函数，初始化条件列表
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        //判断是否有有效条件
        public boolean isValid() {
            return criteria.size() > 0;
        }

        //获取所有条件元素
        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        // 获取条件列表

        public List<Criterion> getCriteria() {
            return criteria;
        }

        //添加无值条件
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        //添加单值条件
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        //添加范围条件
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        // ========== 元数据ID字段条件方法 ==========

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Integer value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Integer value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Integer value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Integer value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Integer value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Integer> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Integer> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Integer value1, Integer value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Integer value1, Integer value2) {
            addCriterion("mid not between", value1, value2, "mid");
            return (Criteria) this;
        }

        // ========== 名称字段条件方法 ==========

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        // ========== 缩略名字段条件方法 ==========

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

        // ========== 类型字段条件方法 ==========

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

        // ========== 描述字段条件方法 ==========

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        // ========== 排序字段条件方法 ==========

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        // ========== 父级ID字段条件方法 ==========

        public Criteria andParentIsNull() {
            addCriterion("parent is null");
            return (Criteria) this;
        }

        public Criteria andParentIsNotNull() {
            addCriterion("parent is not null");
            return (Criteria) this;
        }

        public Criteria andParentEqualTo(Integer value) {
            addCriterion("parent =", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotEqualTo(Integer value) {
            addCriterion("parent <>", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThan(Integer value) {
            addCriterion("parent >", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent >=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThan(Integer value) {
            addCriterion("parent <", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThanOrEqualTo(Integer value) {
            addCriterion("parent <=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentIn(List<Integer> values) {
            addCriterion("parent in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotIn(List<Integer> values) {
            addCriterion("parent not in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentBetween(Integer value1, Integer value2) {
            addCriterion("parent between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotBetween(Integer value1, Integer value2) {
            addCriterion("parent not between", value1, value2, "parent");
            return (Criteria) this;
        }
    }

    //具体条件类 - 继承GeneratedCriteria

    public static class Criteria extends GeneratedCriteria {
        //受保护的构造函数
        protected Criteria() {
            super();
        }
    }

    /**
     * 条件元素类 - 表示单个查询条件
     */
    public static class Criterion {
        // 条件字符串
        private String condition;
        // 条件值
        private Object value;
        // 第二个条件值（用于范围条件）
        private Object secondValue;
        // 是否无值条件标志
        private boolean noValue;
        // 是否单值条件标志
        private boolean singleValue;
        // 是否范围条件标志
        private boolean betweenValue;
        // 是否列表条件标志
        private boolean listValue;
        // 类型处理器
        private String typeHandler;

        //Getter方法
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

        // 无值条件的构造函数
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        // 单值条件的构造函数
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

        //单值条件的简化构造函数
        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        //范围条件的构造函数

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        /**
         * 范围条件的简化构造函数
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}