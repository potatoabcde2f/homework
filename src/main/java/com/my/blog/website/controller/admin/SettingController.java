package com.my.blog.website.controller.admin;

import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.ISiteService;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.BackResponseBo;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.OptionVo;
import com.my.blog.website.service.IOptionService;
import com.my.blog.website.utils.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统设置与站点维护控制器。
 *
 * - 提供系统参数查看与保存
 * - 提供站点备份能力
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);

    @Resource
    private IOptionService optionService;

    @Resource
    private ILogService logService;

    @Resource
    private ISiteService siteService;

    /**
     * 系统设置页面。
     * @param request HTTP 请求对象
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String setting(HttpServletRequest request) {
        List<OptionVo> voList = optionService.getOptions(); // 读取所有配置项
        Map<String, String> options = new HashMap<>();
        voList.forEach((option) -> {
            options.put(option.getName(), option.getValue()); // 转为 Map 便于模板使用
        });
        request.setAttribute("options", options);
        return "admin/setting";
    }

    /**
     * 保存系统设置。
     * @param site_theme 主题名称（可选）
     * @param request HTTP 请求对象，包含表单参数
     * @return 操作结果
     */
    @PostMapping(value = "")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveSetting(@RequestParam(required = false) String site_theme, HttpServletRequest request) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap(); // 表单参数
            Map<String, String> querys = new HashMap<>();
            parameterMap.forEach((key, value) -> {
                querys.put(key, join(value)); // 多值拼接
            });

            optionService.saveOptions(querys); // 批量保存配置

            WebConst.initConfig = querys; // 刷新内存配置

            if (StringUtils.isNotBlank(site_theme)) {
                BaseController.THEME = "themes/" + site_theme; // 切换主题
            }
            logService.insertLog(LogActions.SYS_SETTING.getAction(), GsonUtils.toJsonString(querys), request.getRemoteAddr(), this.getUid(request)); // 记录设置变更
            return RestResponseBo.ok();
        } catch (Exception e) {
            String msg = "保存设置失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }


    /**
     * 系统备份。
     * @param bk_type 备份类型
     * @param bk_path 备份路径
     * @param request HTTP 请求对象
     * @return 备份结果
     */
    @PostMapping(value = "backup")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo backup(@RequestParam String bk_type, @RequestParam String bk_path,
                                 HttpServletRequest request) {
        if (StringUtils.isBlank(bk_type)) {
            return RestResponseBo.fail("请确认信息输入完整"); // 必填校验
        }
        try {
            BackResponseBo backResponse = siteService.backup(bk_type, bk_path, "yyyyMMddHHmm"); // 触发备份
            logService.insertLog(LogActions.SYS_BACKUP.getAction(), null, request.getRemoteAddr(), this.getUid(request)); // 记录备份日志
            return RestResponseBo.ok(backResponse);
        } catch (Exception e) {
            String msg = "备份失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }


    /**
     * 数组转逗号分隔字符串。
     * @param arr 字符串数组
     * @return 拼接后的字符串
     */
    private String join(String[] arr) {
        StringBuilder ret = new StringBuilder();
        String[] var3 = arr;
        int var4 = arr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            ret.append(',').append(item);
        }
        return ret.length() > 0 ? ret.substring(1) : ret.toString();
    }

}
