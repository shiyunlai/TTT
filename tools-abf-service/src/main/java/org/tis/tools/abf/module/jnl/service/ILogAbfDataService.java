package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;

import java.util.List;

/**
 * logAbfData的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
public interface ILogAbfDataService extends IService<LogAbfData>  {

    /**
     * 查询某个日志对应的
     * @param operateGuid
     * @return
     */
    List<LogAbfData> queryForOperate(String operateGuid) throws OperateLogException;

}

