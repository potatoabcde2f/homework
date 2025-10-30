package com.my.blog.website.controller.admin;

import com.my.blog.website.controller.BaseController;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.dto.Types;
import com.my.blog.website.modal.Vo.MetaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 友链管理控制器。
 *
 * - 展示、保存与删除友情链接
 */
@Controller
@RequestMapping("admin/links")
public class LinksController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Resource
    private IMetaService metasService;

    /**
     * 友链列表页。
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<MetaVo> metas = metasService.getMetas(Types.LINK.getType()); // 查询全部友情链接
        request.setAttribute("links", metas); // 绑定到模型
        return "admin/links";
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveLink(@RequestParam String title, @RequestParam String url,
                                   @RequestParam String logo, @RequestParam Integer mid,
                                   @RequestParam(value = "sort", defaultValue = "0") int sort) {
        try {
            MetaVo metas = new MetaVo();
            metas.setName(title); // 显示标题
            metas.setSlug(url); // 链接地址
            metas.setDescription(logo); // logo 地址
            metas.setSort(sort); // 排序
            metas.setType(Types.LINK.getType()); // 元类型为链接
            if (null != mid) {
                metas.setMid(mid);
                metasService.update(metas); // 更新已存在记录
            } else {
                metasService.saveMeta(metas); // 新增记录
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metasService.delete(mid); // 删除友链
        } catch (Exception e) {
            String msg = "友链删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
