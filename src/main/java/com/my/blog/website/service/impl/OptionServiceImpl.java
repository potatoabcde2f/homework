package com.my.blog.website.service.impl;

import com.my.blog.website.dao.OptionVoMapper;
import com.my.blog.website.modal.Vo.OptionVo;
import com.my.blog.website.modal.Vo.OptionVoExample;
import com.my.blog.website.service.IOptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统配置项服务实现类
 * 负责处理系统配置项（如网站名称、描述等）的增删改查操作，依赖OptionVoMapper与数据库交互
 * Created by BlueT on 2017/3/7.
 */
@Service
public class OptionServiceImpl implements IOptionService {

    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);

    // 配置项数据访问对象，用于与数据库交互执行配置项相关的CRUD操作
    @Resource
    private OptionVoMapper optionDao;

    /**
     * 插入配置项（通过OptionVo对象）
     * 选择性插入配置项信息，只插入非空字段
     * @param optionVo 配置项对象，包含配置项名称、值等信息
     */
    @Override
    public void insertOption(OptionVo optionVo) {
        LOGGER.debug("Enter insertOption method:optionVo={}" ,optionVo);
        optionDao.insertSelective(optionVo);
        LOGGER.debug("Exit insertOption method.");
    }

    /**
     * 插入或更新配置项（通过名称和值）
     * 若配置项不存在则插入，若已存在则更新其值
     * @param name 配置项名称
     * @param value 配置项值
     */
    @Override
    public void insertOption(String name, String value) {
        LOGGER.debug("Enter insertOption method:name={},value={}",name,value );
        OptionVo optionVo = new OptionVo();
        optionVo.setName(name);
        optionVo.setValue(value);
        // 检查是否已有配置项记录
        if(optionDao.selectByExample(new OptionVoExample()).size()==0){
            // 无记录则插入新配置项
            optionDao.insertSelective(optionVo);
        }else{
            // 有记录则更新配置项值
            optionDao.updateByPrimaryKeySelective(optionVo);
        }
        LOGGER.debug("Exit insertOption method.");
    }

    /**
     * 批量保存配置项
     * 遍历配置项Map，调用单条插入/更新方法处理每个配置项
     * @param options 配置项键值对Map（key为配置项名称，value为配置项值）
     */
    @Override
    public void saveOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            // 遍历Map，对每个配置项执行插入或更新操作
            options.forEach(this::insertOption);
        }
    }

    /**
     * 获取所有配置项
     * 查询数据库中所有配置项并返回列表
     * @return 所有系统配置项的列表
     */
    @Override
    public List<OptionVo> getOptions(){
        return optionDao.selectByExample(new OptionVoExample());
    }
}