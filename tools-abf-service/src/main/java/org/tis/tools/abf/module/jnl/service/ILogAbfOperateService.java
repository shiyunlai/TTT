package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.entity.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;

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


    Page<LogAbfOperate> queryListByUser(Page<LogAbfOperate> page, Wrapper<LogAbfOperate> wrapper ,String userId)throws
            OperateLogException;

}

