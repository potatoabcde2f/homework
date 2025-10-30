// 导入ArrayList和List， ArrayList是基于数组的动态列表，List是列表接口，定义了列表的基本操作
import java.util.ArrayList;
import java.util.List;

public class CommentVoExample {
    /**ORDER BY子句
     * 用于SQL查询中的排序
     * 例如："created DESC"
     */
    protected String orderByClause;

    /**
     * 是否去重
     * 对应SQL中的DISTINCT关键字
     */
    protected boolean distinct;

    /**
     * 条件列表
     * 存储多个查询条件，用OR连接
     * List是接口，ArrayList是实现类
     */
    protected List<Criteria> oredCriteria;

    /**
     * 查询限制条数
     * 对应SQL中的LIMIT
     */
    private Integer limit;

    /**
     * 查询偏移量
     * 对应SQL中的OFFSET，用于分页
     */
    private Integer offset;

    /**
     * 构造函数
     * 初始化条件列表
     */
    public CommentVoExample() {
        // 创建ArrayList实例
        // new关键字用于创建对象实例
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序子句
     * @param orderByClause 排序条件
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    // 其他方法类似...

    /**
     * 创建条件内部方法
     * protected: 只能在同包或子类中访问
     * @return 条件对象
     */
    protected Criteria createCriteriaInternal() {
        // 创建Criteria对象
        // Criteria是内部类，用于构建查询条件
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清空所有条件
     * 用于重置查询条件
     */
    public void clear() {
        // 清空列表
        oredCriteria.clear();
        // 重置排序
        orderByClause = null;
        // 重置去重标志
        distinct = false;
    }
}