// 定义包路径，表示这个类在项目中的组织结构
package com.my.blog.website.modal.Vo;

// 导入Java集合框架类
// ArrayList是基于数组的动态列表，支持快速随机访问， List是集合接口，定义了列表的基本操作规范
import java.util.ArrayList;
import java.util.List;

/**日志查询条件示例类 (LogVo Example)
 * 用于构建系统操作日志的复杂数据库查询条件，对应LogVo对象的动态SQL查询构建
 */
public class LogVoExample {
    /** ORDER BY子句 - SQL排序条件
     * 用于指定查询结果的排序规则
     * 示例："created DESC" 按创建时间降序排列
     * protected修饰符：同包类或子类可以访问
     */
    protected String orderByClause;

    /**去重标志 - 对应SQL的DISTINCT关键字
     * 设置为true时，查询结果会去除重复记录
     * 常用于统计类查询，避免重复数据影响结果准确性
     */
    protected boolean distinct;

    /**
     * OR条件列表 - 存储所有用OR连接的查询条件组
     * 每个Criteria对象代表一组用AND连接的条件
     * 多个Criteria之间用OR连接，实现复杂逻辑查询
     */
    protected List<Criteria> oredCriteria;

    /**查询限制条数 - 对应SQL的LIMIT子句
     * 用于分页查询，限制单次查询返回的记录数量
     * 提高查询性能，避免一次性加载过多数据
     */
    private Integer limit;

    /**查询偏移量 - 对应SQL的OFFSET子句
     * 用于分页查询，指定跳过的记录数
     * 配合limit实现数据分页：offset = (pageNumber - 1) * pageSize
     */
    private Integer offset;

    /**
     * 构造函数 - 初始化日志查询条件对象
     * 创建空的OR条件列表，准备构建查询条件
     */
    public LogVoExample() {
        // 初始化条件列表为空ArrayList
        // ArrayList在内存中连续存储，支持快速随机访问
        oredCriteria = new ArrayList<Criteria>();
    }

    /**设置排序子句
     * @param orderByClause SQL排序条件字符串
     * 示例："created DESC, id ASC"
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**获取当前排序子句
     * @return 排序条件字符串，可能为null
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**设置是否去重
     * @param distinct true表示去重，false表示不去重
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 判断是否设置去重
     * @return 去重标志状态
     * 知识点：boolean类型的getter方法通常以is开头
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**获取所有OR条件组
     * @return OR条件组列表
     * 返回的是条件列表的引用，外部可以查看但不能直接修改
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**添加现有的条件组（OR连接）
     * @param criteria 已构建的条件组对象
     * 用于组合多个预定义的条件组
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**创建并添加新的条件组（OR连接）
     * @return 新创建的条件组对象，用于链式调用
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**创建查询条件组（主要入口方法）
     * 如果当前没有条件组，自动创建并添加一个
     * @return 条件组对象，用于构建具体查询条件
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**创建条件组内部方法
     * @return 新的条件组对象
     * protected修饰符限制只有同包或子类可以调用
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**清空所有查询条件
     * 重置查询构建器状态，用于构建新的查询
     * 知识点：这种方法常用于查询条件的复用和重置
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**设置查询限制条数
     * @param limit 最大返回记录数
     * 用于控制查询结果集大小，避免内存溢出
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**获取查询限制条数
     * @return 限制条数
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * 设置查询偏移量
     * @param offset 跳过的记录数
     * 用于分页查询，实现数据分批加载
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**获取查询偏移量
     * @return 偏移量
     */
    public Integer getOffset() {
        return offset;
    }

    /**抽象静态内部类 - 条件生成基类
     * 封装所有日志字段的通用查询条件方法
     * abstract表示这个类不能直接实例化，必须被继承
     * static表示这个内部类不依赖外部类实例
     * 知识点：抽象类用于定义通用行为，具体实现由子类完成
     */
    protected abstract static class GeneratedCriteria {
        /**条件元素列表
         * 存储具体的查询条件元素，每个Criterion代表一个SQL条件
         * 如：id = 1, action LIKE '%login%' 等
         */
        protected List<Criterion> criteria;

        /**
         * 构造函数 - 初始化条件列表
         * 创建空的ArrayList来存储查询条件
         */
        protected GeneratedCriteria() {
            super(); // 调用Object类的构造函数
            criteria = new ArrayList<Criterion>();
        }

        /**判断是否有有效条件
         * @return 条件列表不为空返回true，否则false
         * 用于在执行查询前验证条件是否有效
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

        /**获取条件列表
         * @return 条件元素列表
         */
        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**添加无值条件
         * @param condition 条件字符串，如："id is null"
         * 用于构建不需要值的条件，如IS NULL, IS NOT NULL
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * 添加单值条件
         * @param condition 条件字符串，如："action ="
         * @param value 条件值
         * @param property 属性名，用于错误信息
         * 用于构建需要单个值的条件，如=, <>, >, <, LIKE等
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /** 添加范围条件
         * @param condition 条件字符串，如："created between"
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @param property 属性名，用于错误信息
         * 用于构建范围查询条件，如BETWEEN value1 AND value2
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        // ========== 日志ID字段条件方法 ==========

        /**添加日志ID为null的条件
         * @return 条件对象，用于链式调用
         */
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        /**
         * 添加日志ID不为null的条件
         * @return 条件对象
         */
        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        /**添加日志ID等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**添加日志ID不等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**添加日志ID大于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**添加日志ID大于等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加日志ID小于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**添加日志ID小于等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加日志ID在指定列表中的条件
         * @param values 值列表
         * @return 条件对象
         * 知识点：List<Integer>使用泛型确保类型安全
         */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**添加日志ID不在指定列表中的条件
         * @param values 值列表
         * @return 条件对象
         */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**添加日志ID在指定范围内的条件
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @return 条件对象
         */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * 添加日志ID不在指定范围内的条件
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @return 条件对象
         */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        // ========== 操作动作字段条件方法 ==========

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        /**添加操作动作模糊匹配条件
         * @param value 模糊匹配模式
         * @return 条件对象
         * 常用于搜索包含特定关键词的操作，如："%login%"
         */
        public Criteria andActionLike(String value) {
            addCriterion("action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("action not between", value1, value2, "action");
            return (Criteria) this;
        }

        // ========== 操作数据字段条件方法 ==========

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(String value) {
            addCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(String value) {
            addCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(String value) {
            addCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(String value) {
            addCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThan(String value) {
            addCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(String value) {
            addCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        /**添加操作数据模糊匹配条件
         * @param value 模糊匹配模式
         * @return 条件对象
         * 用于搜索包含特定数据的操作日志
         */
        public Criteria andDataLike(String value) {
            addCriterion("data like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotLike(String value) {
            addCriterion("data not like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataIn(List<String> values) {
            addCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<String> values) {
            addCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(String value1, String value2) {
            addCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(String value1, String value2) {
            addCriterion("data not between", value1, value2, "data");
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

        // ========== IP地址字段条件方法 ==========

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        /**添加IP地址模糊匹配条件
         * @param value 模糊匹配模式
         * @return 条件对象
         * 常用于搜索特定网段的IP地址，如："192.168.1.%"
         */
        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
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

        /**添加创建时间在指定范围内的条件
         * @param value1 范围起始时间戳
         * @param value2 范围结束时间戳
         * @return 条件对象
         * 常用于按时间范围查询日志，如查询某段时间内的操作记录
         */
        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }
    }

    /**具体条件类 - 继承GeneratedCriteria
     * 这个类目前没有额外的方法，但提供了扩展点
     * 可以在子类中添加特定于日志查询的业务条件方法
     * 知识点：继承使得代码可以分层组织，基类处理通用逻辑，子类处理特殊逻辑
     */
    public static class Criteria extends GeneratedCriteria {
        /**受保护的构造函数
         * 只能被外部类访问，防止外部直接创建实例
         * 确保条件对象只能通过Example类的createCriteria方法创建
         */
        protected Criteria() {
            super(); // 调用父类GeneratedCriteria的构造函数
        }
    }

    /**
     * 条件元素类 - 表示单个查询条件
     * 封装了SQL条件的所有信息：条件字符串、值、类型等
     * 知识点：这是一个值对象(Value Object)，主要用于存储和传递数据
     */
    public static class Criterion {
        /**条件字符串 - 存储SQL条件模板
         * 如："id =", "action like", "created between"
         */
        private String condition;

        /**条件值 - 对于单值或范围条件的第一个值
         */
        private Object value;

        /**
         * 第二个条件值 - 对于范围条件的第二个值
         */
        private Object secondValue;

        /**是否无值条件标志
         * 如："action is null" 这种没有值的条件
         */
        private boolean noValue;

        /**是否单值条件标志
         * 如："id = 1" 这种单个值的条件
         */
        private boolean singleValue;

        /**
         * 是否范围条件标志
         * 如："created between 1000 and 2000"
         */
        private boolean betweenValue;

        /**是否列表条件标志
         * 如："id in (1,2,3,4,5)"
         */
        private boolean listValue;

        /**类型处理器 - 用于特殊类型的值转换
         * 在MyBatis中用于处理日期、枚举等特殊类型的转换
         */
        private String typeHandler;

        // ========== Getter方法 ==========
        // 这些方法提供对私有属性的只读访问，保护数据的完整性

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

        // ========== 构造函数 ==========

        /**无值条件的构造函数
         * @param condition 条件字符串，如："action is null"
         */
        protected Criterion(String condition) {
            super(); // 调用Object的构造函数
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true; // 标记为无值条件
        }

        /**单值条件的构造函数
         * @param condition 条件字符串
         * @param value 条件值
         * @param typeHandler 类型处理器
         */
        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            // 根据值的类型设置相应的标志
            if (value instanceof List<?>) {
                // 如果值是List类型，标记为列表条件
                this.listValue = true;
            } else {
                // 否则标记为单值条件
                this.singleValue = true;
            }
        }

        /**
         * 单值条件的简化构造函数
         * @param condition 条件字符串
         * @param value 条件值
         */
        protected Criterion(String condition, Object value) {
            // 调用三参数构造函数，typeHandler为null
            this(condition, value, null);
        }

        /**范围条件的构造函数
         * @param condition 条件字符串
         * @param value 范围起始值
         * @param secondValue 范围结束值
         * @param typeHandler 类型处理器
         */
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true; // 标记为范围条件
        }

        /**范围条件的简化构造函数
         * @param condition 条件字符串
         * @param value 范围起始值
         * @param secondValue 范围结束值
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            // 调用四参数构造函数，typeHandler为null
            this(condition, value, secondValue, null);
        }
    }
}