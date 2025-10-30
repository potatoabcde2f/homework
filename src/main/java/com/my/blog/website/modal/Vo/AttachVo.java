// 包声明
package com.my.blog.website.modal.Vo;

// 导入序列化接口
import java.io.Serializable;

/**附件值对象（Attach Value Object）,用于存储上传文件的信息，对应数据库中的附件表
 * @author 开发者信息
 */
public class AttachVo implements Serializable {
    //序列化版本ID
    private static final long serialVersionUID = 1L;

    // ========== 附件相关属性 ==========

    // 附件主键ID (附件的唯一标识)

    private Integer id;

    //文件名称,上传文件的原始名称
    private String fname;

    //文件类型,image/jpeg, application/pdf等
    private String ftype;

    //文件存储键,文件在存储系统中的唯一标识（如OSS中的key）
    private String fkey;

    //上传者用户ID,记录是谁上传的这个文件
    private Integer authorId;

    // 创建时间, 文件上传的时间戳
    private Integer created;

    // ========== Getter和Setter方法 ==========

    //获取附件ID,@return 附件ID
    public Integer getId() {
        return id;
    }

    // 设置附件ID, @param id 附件ID
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取文件名称,@return 文件名称
    public String getFname() {
        return fname;
    }

    // 设置文件名称, @param fname 文件名称
    public void setFname(String fname) {
        this.fname = fname;
    }

    /*获取文件类型
     * MIME类型，如：image/png
     * @return 文件类型
     */
    public String getFtype() {
        return ftype;
    }

    /*设置文件类型
     * @param ftype 文件类型
     */
    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    /**获取文件存储键
     * 这个key用于在文件存储系统中定位文件
     * @return 文件存储键
     */
    public String getFkey() {
        return fkey;
    }

    /*设置文件存储键
     * @param fkey 文件存储键
     */
    public void setFkey(String fkey) {
        this.fkey = fkey;
    }

    /*获取上传者ID
     * @return 上传者用户ID
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**设置上传者ID
     * @param authorId 上传者用户ID
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /** 获取创建时间
     * @return 创建时间戳
     */
    public Integer getCreated() {
        return created;
    }

    /**设置创建时间
     * @param created 创建时间戳
     */
    public void setCreated(Integer created) {
        this.created = created;
    }
}