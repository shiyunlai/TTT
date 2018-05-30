package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import com.baomidou.mybatisplus.service.IService;

/**
 * logAbfChange的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
public interface ILogAbfChangeService extends IService<LogAbfChange>  {

    /**
     * 查询数据记录对应的日志差异值
     * @param page
     * @param wrapper
     * @return
     */
    Page<LogAbfChange> queryPageById(Page<LogAbfChange> page, Wrapper<LogAbfChange> wrapper, String id);
}

