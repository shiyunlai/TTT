package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.entity.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;

import java.util.List;

/**
 * logAbfOperate的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
public interface ILogAbfOperateService extends IService<LogAbfOperate>  {

    /**
     *
     * @param log
     * @throws OperateLogException
     */
    void insertOperatorLog(LogOperateDetail log) throws OperateLogException;

    /**
     * 根据条件查询日志
     * @param condition
     * @return List<LogAbfOperate>
     * @throws OperateLogException
     */
    List<LogAbfOperate> queryByCondition(String condition) throws OperateLogException;

}

