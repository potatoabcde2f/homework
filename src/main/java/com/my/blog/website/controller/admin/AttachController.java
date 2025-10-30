package com.my.blog.website.controller.admin;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.IAttachService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.utils.Commons;
import com.my.blog.website.utils.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台附件管理控制器。
 *
 * - 支持附件列表展示
 * - 支持上传与删除附件
 */
@Controller
@RequestMapping("admin/attach")
public class AttachController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUplodFilePath();

    @Resource
    private IAttachService attachService;

    @Resource
    private ILogService logService;

    /**
     * 附件列表页。
     * @param request HTTP 请求对象
     * @param page 页码，默认1
     * @param limit 每页数量，默认12
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit) {
        PageInfo<AttachVo> attachPaginator = attachService.getAttachs(page, limit); // 分页查询附件
        request.setAttribute("attachs", attachPaginator);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url())); // 附件访问前缀
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024); // KB 显示上限
        return "admin/attach";
    }

    /**
     * 上传文件接口。
     * @param request HTTP 请求对象
     * @param multipartFiles 多文件表单字段
     * @return 成功与失败文件列表
     */
    @PostMapping(value = "upload")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        UserVo users = this.user(request);
        Integer uid = users.getUid();
        List<String> errorFiles = new ArrayList<>(); // 记录超出大小或失败的文件名
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                String fname = multipartFile.getOriginalFilename();
                if (multipartFile.getSize() <= WebConst.MAX_FILE_SIZE) {
                    String fkey = TaleUtils.getFileKey(fname); // 生成唯一文件名
                    String ftype = TaleUtils.isImage(multipartFile.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType(); // 判断是否图片
                    File file = new File(CLASSPATH+fkey);
                    try {
                        FileCopyUtils.copy(multipartFile.getInputStream(),new FileOutputStream(file)); // 写入磁盘
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    attachService.save(fname, fkey, ftype, uid); // 保存数据库记录
                } else {
                    errorFiles.add(fname); // 超出大小限制
                }
            }
        } catch (Exception e) {
            return RestResponseBo.fail();
        }
        return RestResponseBo.ok(errorFiles);
    }

    /**
     * 删除附件。
     * @param id 附件主键ID
     * @param request HTTP 请求对象
     * @return 操作结果
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam Integer id, HttpServletRequest request) {
        try {
            AttachVo attach = attachService.selectById(id);
            if (null == attach) return RestResponseBo.fail("不存在该附件"); // 校验存在
            attachService.deleteById(id); // 删除数据库记录
            new File(CLASSPATH+attach.getFkey()).delete(); // 删除物理文件
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), attach.getFkey(), request.getRemoteAddr(), this.getUid(request)); // 记录日志
        } catch (Exception e) {
            String msg = "附件删除失败";
            if (e instanceof TipException) msg = e.getMessage();
            else LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
