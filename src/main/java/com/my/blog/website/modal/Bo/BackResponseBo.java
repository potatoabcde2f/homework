package com.my.blog.website.modal.Bo;

import java.io.Serializable;

/**
 * 后台返回的路径集合（用于封装上传、主题、SQL 导出等路径）
 */
public class BackResponseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 附件路径
    private String attachPath;
    // 主题路径
    private String themePath;
    // SQL 导出路径
    private String sqlPath;

    /**
     * 获取附件路径
     * @return 附件存放路径
     */
    public String getAttachPath() {
        return attachPath;
    }

    /**
     * 设置附件路径
     * @param attachPath 附件存放路径
     */
    public void setAttachPath(String attachPath) {
        // 直接赋值，保持与旧逻辑一致
        this.attachPath = attachPath;
    }

    /**
     * 获取主题路径
     * @return 主题目录路径
     */
    public String getThemePath() {
        return themePath;
    }

    /**
     * 设置主题路径
     * @param themePath 主题目录路径
     */
    public void setThemePath(String themePath) {
        // 直接赋值，调用方负责路径合法性
        this.themePath = themePath;
    }

    /**
     * 获取 SQL 导出路径
     * @return SQL 文件路径
     */
    public String getSqlPath() {
        return sqlPath;
    }

    /**
     * 设置 SQL 导出路径
     * @param sqlPath SQL 文件路径
     */
    public void setSqlPath(String sqlPath) {
        // 直接赋值，保持与旧逻辑一致
        this.sqlPath = sqlPath;
    }
}
