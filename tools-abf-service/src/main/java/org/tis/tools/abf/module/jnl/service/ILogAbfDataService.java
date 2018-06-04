package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;

/**
 * logAbfData的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
public interface ILogAbfDataService extends IService<LogAbfData>  {

    /**
     * 查询日志对应ID的操作数据记录
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws OperateLogException
     */
    Page<LogAbfData> queryPageById(Page<LogAbfData> page, Wrapper<LogAbfData> wrapper,String id) throws
            OperateLogException;
}

