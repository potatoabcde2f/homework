// 定义这个类所在的包路径
package com.my.blog.website.modal.Vo;

// 导入ArrayList和List类
// ArrayList是基于数组实现的动态列表，支持快速随机访问, List是集合框架的接口，定义了列表的基本操作
import java.util.ArrayList;
import java.util.List;

/**附件查询条件示例类 (AttachVo Example)
 * 这个类用于构建复杂的数据库查询条件，对应AttachVo对象的查询
 * 采用建造者模式(Builder Pattern)，支持链式调用
 */
public class AttachVoExample {
    /**
     * ORDER BY子句 - 用于SQL查询的排序
     * 示例："created DESC" 表示按创建时间降序排列, protected: 同包或子类可以访问，其他包不能访问
     */
    protected String orderByClause;

    //是否去重标志 - 对应SQL中的DISTINCT关键字, 如果为true，查询结果会去除重复行
    protected boolean distinct;

    /**
     * 条件列表 - 存储所有用OR连接的查询条件
     * List<Criteria>: 使用泛型，确保列表中只能存放Criteria对象
     */
    protected List<Criteria> oredCriteria;

    /**
     * 查询限制条数 - 对应SQL中的LIMIT子句
     * 用于限制返回的记录数量
     */
    private Integer limit;

    /**
     * 查询偏移量 - 对应SQL中的OFFSET子句
     * 用于分页查询，表示跳过的记录数
     */
    private Integer offset;

    /**
     * 构造函数 - 创建AttachVoExample对象时自动调用
     * 初始化条件列表为一个空的ArrayList
     */
    public AttachVoExample() {
        // 创建ArrayList实例来存储条件
        // ArrayList<>中的泛型可以省略，编译器会自动推断
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序子句
     * @param orderByClause 排序条件，如："id DESC, name ASC"
     * void: 表示这个方法不返回任何值
     */
    public void setOrderByClause(String orderByClause) {
        // this关键字指向当前对象，用于区分参数和成员变量
        this.orderByClause = orderByClause;
    }

    /**
     * 获取当前设置的排序子句
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
     * 判断是否设置了去重
     * @return true如果设置了去重，否则false
     * 知识点：boolean类型的getter方法通常以is开头
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取所有OR条件列表
     * @return 条件列表，可能为空但不会为null
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 添加一个OR条件
     * @param criteria 已经构建好的条件对象
     * 知识点：这个方法允许外部直接添加预定义的条件
     */
    public void or(Criteria criteria) {
        // 将条件添加到列表中
        oredCriteria.add(criteria);
    }

    /**
     * 创建并添加一个新的OR条件
     * @return 新创建的条件对象，用于链式调用
     * 知识点：这是工厂方法模式的简单应用
     */
    public Criteria or() {
        // 创建新的条件对象
        Criteria criteria = createCriteriaInternal();
        // 添加到条件列表
        oredCriteria.add(criteria);
        // 返回条件对象，支持链式调用
        return criteria;
    }

    /**
     * 创建查询条件（主要入口方法）
     * 如果当前没有条件，会自动创建一个并添加到列表中
     * @return 条件对象，用于构建具体的查询条件
     */
    public Criteria createCriteria() {
        // 创建新的条件对象
        Criteria criteria = createCriteriaInternal();
        // 如果当前条件列表为空，添加这个新条件
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 创建条件内部方法
     * protected: 只能在同包或子类中访问
     * @return 新创建的条件对象
     */
    protected Criteria createCriteriaInternal() {
        // 创建Criteria对象实例
        // new关键字在堆内存中分配空间并创建对象
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清空所有查询条件
     * 用于重置查询构建器，开始新的条件构建
     */
    public void clear() {
        // 清空条件列表
        oredCriteria.clear();
        // 重置排序条件为null
        orderByClause = null;
        // 重置去重标志为false
        distinct = false;
    }

    //设置查询限制条数, @param limit 最大返回记录数

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 获取查询限制条数
     * @return 限制条数，可能为null
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
     * @return 偏移量，可能为null
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 抽象静态内部类 - 生成的条件基类
     * abstract: 不能被实例化，只能被继承
     * static: 不依赖于外部类实例，可以独立存在
     * 这个类封装了所有条件生成的通用逻辑
     * 知识点：静态内部类不能访问外部类的非静态成员
     */
    protected abstract static class GeneratedCriteria {
        /**条件列表 - 存储所有的查询条件元素
         * 每个Criterion代表一个具体的查询条件
         */
        protected List<Criterion> criteria;

        //构造函数 - 初始化条件列表, protected: 只能被同包或子类调用
        protected GeneratedCriteria() {
            super(); // 调用父类Object的构造函数
            // 初始化条件列表
            criteria = new ArrayList<Criterion>();
        }

        /**判断是否有有效条件
         * @return 如果条件列表不为空返回true，否则false
         */
        public boolean isValid() {
            // 检查条件列表是否有元素
            return criteria.size() > 0;
        }

        /**获取所有条件元素
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

        /**添加无值条件（如：字段 IS NULL）
         * @param condition 条件字符串，如："id is null"
         */
        protected void addCriterion(String condition) {
            // 参数校验：条件不能为null
            if (condition == null) {
                // 抛出运行时异常，不需要在方法签名中声明
                throw new RuntimeException("Value for condition cannot be null");
            }
            // 创建无值条件并添加到列表
            criteria.add(new Criterion(condition));
        }

        /**
         * 添加单值条件（如：字段 = 值）
         * @param condition 条件字符串，如："id ="
         * @param value 条件值
         * @param property 属性名，用于错误信息
         */
        protected void addCriterion(String condition, Object value, String property) {
            // 参数校验：值不能为null
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            // 创建单值条件并添加到列表
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 添加范围条件（如：字段 BETWEEN 值1 AND 值2）
         * @param condition 条件字符串，如："id between"
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @param property 属性名，用于错误信息
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            // 参数校验：两个值都不能为null
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            // 创建范围条件并添加到列表
            criteria.add(new Criterion(condition, value1, value2));
        }

        // ========== ID字段的条件方法 ==========

        /**添加id为null的条件
         * @return 条件对象，用于链式调用
         */
        public Criteria andIdIsNull() {
            // 添加"id is null"条件
            addCriterion("id is null");
            // 返回this实现链式调用（Fluent Interface）
            return (Criteria) this;
        }

        /**
         * 添加id不为null的条件
         * @return 条件对象
         */
        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        /**
         * 添加id等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdEqualTo(Integer value) {
            // "id =" 是SQL条件模板
            // value 是要比较的具体值
            // "id" 是属性名，用于错误信息
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加id不等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加id大于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加id大于等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * 添加id小于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**添加id小于等于指定值的条件
         * @param value 要比较的值
         * @return 条件对象
         */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**添加id在指定列表中的条件
         * @param values 值列表
         * @return 条件对象
         * 知识点：List<Integer>使用了泛型，确保列表中只能是Integer
         */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**添加id不在指定列表中的条件
         * @param values 值列表
         * @return 条件对象
         */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**
         * 添加id在指定范围内的条件
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @return 条件对象
         */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**添加id不在指定范围内的条件
         * @param value1 范围起始值
         * @param value2 范围结束值
         * @return 条件对象
         */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        // ========== Fname字段的条件方法 ==========
        // 以下方法模式与ID字段相同，针对字符串类型增加了LIKE操作

        public Criteria andFnameIsNull() {
            addCriterion("fname is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("fname is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("fname =", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("fname <>", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("fname >", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("fname >=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("fname <", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("fname <=", value, "fname");
            return (Criteria) this;
        }

        /** 添加文件名模糊匹配条件
         * @param value 模糊匹配模式，如："%.txt"
         * @return 条件对象
         * 知识点：LIKE操作符用于模糊匹配，%表示任意字符
         */
        public Criteria andFnameLike(String value) {
            addCriterion("fname like", value, "fname");
            return (Criteria) this;
        }

        /**添加文件名不匹配条件
         * @param value 模糊匹配模式
         * @return 条件对象
         */
        public Criteria andFnameNotLike(String value) {
            addCriterion("fname not like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("fname in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("fname not in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("fname between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("fname not between", value1, value2, "fname");
            return (Criteria) this;
        }

        // ========== Ftype字段的条件方法 ==========
        // 文件类型字段的条件方法，与fname字段类似

        public Criteria andFtypeIsNull() {
            addCriterion("ftype is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("ftype is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(String value) {
            addCriterion("ftype =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(String value) {
            addCriterion("ftype <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(String value) {
            addCriterion("ftype >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(String value) {
            addCriterion("ftype >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(String value) {
            addCriterion("ftype <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(String value) {
            addCriterion("ftype <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLike(String value) {
            addCriterion("ftype like", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotLike(String value) {
            addCriterion("ftype not like", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<String> values) {
            addCriterion("ftype in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<String> values) {
            addCriterion("ftype not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(String value1, String value2) {
            addCriterion("ftype between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(String value1, String value2) {
            addCriterion("ftype not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        // ========== Fkey字段的条件方法 ==========
        // 文件存储键字段的条件方法
        public Criteria andFkeyIsNull() {
            addCriterion("fkey is null");
            return (Criteria) this;
        }

        public Criteria andFkeyIsNotNull() {
            addCriterion("fkey is not null");
            return (Criteria) this;
        }

        public Criteria andFkeyEqualTo(String value) {
            addCriterion("fkey =", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyNotEqualTo(String value) {
            addCriterion("fkey <>", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyGreaterThan(String value) {
            addCriterion("fkey >", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyGreaterThanOrEqualTo(String value) {
            addCriterion("fkey >=", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyLessThan(String value) {
            addCriterion("fkey <", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyLessThanOrEqualTo(String value) {
            addCriterion("fkey <=", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyLike(String value) {
            addCriterion("fkey like", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyNotLike(String value) {
            addCriterion("fkey not like", value, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyIn(List<String> values) {
            addCriterion("fkey in", values, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyNotIn(List<String> values) {
            addCriterion("fkey not in", values, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyBetween(String value1, String value2) {
            addCriterion("fkey between", value1, value2, "fkey");
            return (Criteria) this;
        }

        public Criteria andFkeyNotBetween(String value1, String value2) {
            addCriterion("fkey not between", value1, value2, "fkey");
            return (Criteria) this;
        }

        // ========== AuthorId字段的条件方法 ==========
        // 作者ID字段的条件方法，与ID字段类似

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

        // ========== Created字段的条件方法 ==========
        // 创建时间字段的条件方法

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
    }

    /**具体条件类 - 继承自GeneratedCriteria
     * 这个类目前没有额外的方法，但提供了扩展点
     * 可以在子类中添加特定于业务的条件方法
     * 知识点：继承使得代码可以分层扩展
     */
    public static class Criteria extends GeneratedCriteria {
        /**受保护的构造函数
         * 只能被外部类访问，防止外部直接创建实例
         */
        protected Criteria() {
            super(); // 调用父类GeneratedCriteria的构造函数
        }
    }

    /**条件元素类 - 表示单个查询条件
     * 封装了SQL条件的所有信息：条件字符串、值、类型等
     * 知识点：这是一个值对象(Value Object)，主要用来存储数据
     */
    public static class Criterion {
        /**条件字符串 - 存储SQL条件模板
         * 如："id =", "name like", "created between"
         */
        private String condition;

        /**条件值 - 对于单值或范围条件的第一个值
         */
        private Object value;

        /**第二个条件值 - 对于范围条件的第二个值
         */
        private Object secondValue;

        /*是否无值条件标志，如："name is null" 这种没有值的条件
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
         * 在MyBatis中用于处理日期、枚举等特殊类型
         */
        private String typeHandler;

        // ========== 构造函数 ==========

        /**无值条件的构造函数
         * @param condition 条件字符串，如："name is null"
         */
        protected Criterion(String condition) {
            super(); // 调用Object的构造函数
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true; // 标记为无值条件
        }

        /**
         * 单值条件的构造函数
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

        /*单值条件的简化构造函数
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

        // ========== Getter方法 ==========
        // 这些方法提供对私有属性的只读访问

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
    }
}