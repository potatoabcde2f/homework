package com.my.blog.website.modal.Vo;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Generator自动生成的Example类，用于构建用户表的动态查询条件
 * 提供用户信息的各种查询条件构建功能
 */
public class UserVoExample {
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
    public UserVoExample() {
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

    /**
     * 创建OR条件并返回，用于链式调用
     * @return 新创建的Criteria对象
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 创建查询条件
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
         * @param property 属性名
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 添加范围查询条件
         * @param condition 条件表达式
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

        // ================ uid字段的查询条件 ================
        // 用户ID，主键

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        // ================ username字段的查询条件 ================
        // 用户名，用于登录

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        // ================ password字段的查询条件 ================
        // 密码，加密存储

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        // ================ email字段的查询条件 ================
        // 邮箱地址

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        // ================ home_url字段的查询条件 ================
        // 个人主页URL

        public Criteria andHomeUrlIsNull() {
            addCriterion("home_url is null");
            return (Criteria) this;
        }

        public Criteria andHomeUrlIsNotNull() {
            addCriterion("home_url is not null");
            return (Criteria) this;
        }

        public Criteria andHomeUrlEqualTo(String value) {
            addCriterion("home_url =", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlNotEqualTo(String value) {
            addCriterion("home_url <>", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlGreaterThan(String value) {
            addCriterion("home_url >", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("home_url >=", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlLessThan(String value) {
            addCriterion("home_url <", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlLessThanOrEqualTo(String value) {
            addCriterion("home_url <=", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlLike(String value) {
            addCriterion("home_url like", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlNotLike(String value) {
            addCriterion("home_url not like", value, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlIn(List<String> values) {
            addCriterion("home_url in", values, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlNotIn(List<String> values) {
            addCriterion("home_url not in", values, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlBetween(String value1, String value2) {
            addCriterion("home_url between", value1, value2, "homeUrl");
            return (Criteria) this;
        }

        public Criteria andHomeUrlNotBetween(String value1, String value2) {
            addCriterion("home_url not between", value1, value2, "homeUrl");
            return (Criteria) this;
        }

        // ================ screen_name字段的查询条件 ================
        // 显示名称，昵称

        public Criteria andScreenNameIsNull() {
            addCriterion("screen_name is null");
            return (Criteria) this;
        }

        public Criteria andScreenNameIsNotNull() {
            addCriterion("screen_name is not null");
            return (Criteria) this;
        }

        public Criteria andScreenNameEqualTo(String value) {
            addCriterion("screen_name =", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameNotEqualTo(String value) {
            addCriterion("screen_name <>", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameGreaterThan(String value) {
            addCriterion("screen_name >", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameGreaterThanOrEqualTo(String value) {
            addCriterion("screen_name >=", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameLessThan(String value) {
            addCriterion("screen_name <", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameLessThanOrEqualTo(String value) {
            addCriterion("screen_name <=", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameLike(String value) {
            addCriterion("screen_name like", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameNotLike(String value) {
            addCriterion("screen_name not like", value, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameIn(List<String> values) {
            addCriterion("screen_name in", values, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameNotIn(List<String> values) {
            addCriterion("screen_name not in", values, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameBetween(String value1, String value2) {
            addCriterion("screen_name between", value1, value2, "screenName");
            return (Criteria) this;
        }

        public Criteria andScreenNameNotBetween(String value1, String value2) {
            addCriterion("screen_name not between", value1, value2, "screenName");
            return (Criteria) this;
        }

        // ================ created字段的查询条件 ================
        // 创建时间，时间戳格式

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

        // ================ activated字段的查询条件 ================
        // 激活时间，时间戳格式

        public Criteria andActivatedIsNull() {
            addCriterion("activated is null");
            return (Criteria) this;
        }

        public Criteria andActivatedIsNotNull() {
            addCriterion("activated is not null");
            return (Criteria) this;
        }

        public Criteria andActivatedEqualTo(Integer value) {
            addCriterion("activated =", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedNotEqualTo(Integer value) {
            addCriterion("activated <>", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedGreaterThan(Integer value) {
            addCriterion("activated >", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("activated >=", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedLessThan(Integer value) {
            addCriterion("activated <", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedLessThanOrEqualTo(Integer value) {
            addCriterion("activated <=", value, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedIn(List<Integer> values) {
            addCriterion("activated in", values, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedNotIn(List<Integer> values) {
            addCriterion("activated not in", values, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedBetween(Integer value1, Integer value2) {
            addCriterion("activated between", value1, value2, "activated");
            return (Criteria) this;
        }

        public Criteria andActivatedNotBetween(Integer value1, Integer value2) {
            addCriterion("activated not between", value1, value2, "activated");
            return (Criteria) this;
        }

        // ================ logged字段的查询条件 ================
        // 最后登录时间，时间戳格式

        public Criteria andLoggedIsNull() {
            addCriterion("logged is null");
            return (Criteria) this;
        }

        public Criteria andLoggedIsNotNull() {
            addCriterion("logged is not null");
            return (Criteria) this;
        }

        public Criteria andLoggedEqualTo(Integer value) {
            addCriterion("logged =", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedNotEqualTo(Integer value) {
            addCriterion("logged <>", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedGreaterThan(Integer value) {
            addCriterion("logged >", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedGreaterThanOrEqualTo(Integer value) {
            addCriterion("logged >=", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedLessThan(Integer value) {
            addCriterion("logged <", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedLessThanOrEqualTo(Integer value) {
            addCriterion("logged <=", value, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedIn(List<Integer> values) {
            addCriterion("logged in", values, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedNotIn(List<Integer> values) {
            addCriterion("logged not in", values, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedBetween(Integer value1, Integer value2) {
            addCriterion("logged between", value1, value2, "logged");
            return (Criteria) this;
        }

        public Criteria andLoggedNotBetween(Integer value1, Integer value2) {
            addCriterion("logged not between", value1, value2, "logged");
            return (Criteria) this;
        }

        // ================ group_name字段的查询条件 ================
        // 用户组名称，用于权限控制

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }
    }

    /**
     * Criteria类，继承GeneratedCriteria
     * 用于外部创建查询条件，提供公共的构造函数
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * Criterion类
     * 表示单个查询条件，封装条件表达式和值
     */
    public static class Criterion {
        // 条件表达式，如"uid =", "username is null"等
        private String condition;

        // 条件值
        private Object value;

        // 第二个条件值（用于BETWEEN操作）
        private Object secondValue;

        // 标记类型：无值条件（如IS NULL）
        private boolean noValue;

        // 标记类型：单值条件（如=, >, <等）
        private boolean singleValue;

        // 标记类型：范围值条件（BETWEEN）
        private boolean betweenValue;

        // 标记类型：列表值条件（IN）
        private boolean listValue;

        // 类型处理器，用于处理特定类型的值
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

        /**
         * 无值构造函数（用于IS NULL, IS NOT NULL等）
         * @param condition 条件表达式
         */
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        /**
         * 单值或列表值构造函数
         * @param condition 条件表达式
         * @param value 条件值
         * @param typeHandler 类型处理器
         */
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

        /**
         * 范围值构造函数（用于BETWEEN操作）
         * @param condition 条件表达式
         * @param value1 起始值
         * @param value2 结束值
         * @param typeHandler 类型处理器
         */
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