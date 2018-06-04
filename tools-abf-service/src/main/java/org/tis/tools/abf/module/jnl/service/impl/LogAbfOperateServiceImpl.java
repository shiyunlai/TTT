package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfOperateMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.entity.vo.LogDataDetail;
import org.tis.tools.abf.module.jnl.entity.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;
import org.tis.tools.abf.module.jnl.service.ILogAbfChangeService;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.tis.tools.abf.module.jnl.service.ILogAbfOperateService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.Date;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/3/29
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfOperateServiceImpl extends ServiceImpl<LogAbfOperateMapper, LogAbfOperate>
        implements ILogAbfOperateService{

    @Autowired
    private ILogAbfChangeService logAbfChangeService;
    @Autowired
    private ILogAbfDataService logAbfDataService;

    @Override
    public void insertOperatorLog(LogOperateDetail log) throws OperateLogException {
        if (null == log || null == log.getInstance()) {
            throw new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT, wrap("", "LOG_OPERATE"));
        }
        LogAbfOperate logAbfOperate = log.getInstance();
        logAbfOperate.setOperateTime(new Date());
        insert(logAbfOperate);
        for (LogDataDetail dataDetail : log.getAllLogData()) {
            LogAbfData logAbfData = dataDetail.getInstance();
            logAbfData.setGuidOperate(logAbfOperate.getGuid());
            logAbfDataService.insert(logAbfData);
            for (LogAbfChange logAbfChange: dataDetail.getChanges()) {
                logAbfChange.setGuidData(logAbfData.getGuid());
                logAbfChangeService.insert(logAbfChange);
            }
        }
    }

    @Override
    public Page<LogAbfOperate> queryByCondition(Page<LogAbfOperate> page, Wrapper<LogAbfOperate> wrapper, String condition) throws OperateLogException {

        if (null == condition){
            throw new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY,wrap("操作时间为空", "LOG_OPERATE_QUERYBYCONDITION"));
        }

        if (null == wrapper){
            wrapper = new EntityWrapper<LogAbfOperate>();
        }

        wrapper.orderBy(LogAbfOperate.COLUMN_OPERATE_TIME,false);
        wrapper.like(LogAbfOperate.COLUMN_OPERATE_TIME,condition);

        Page<LogAbfOperate> logAbfOperatePage = selectPage(page,wrapper);


        return logAbfOperatePage;
    }

    @Override
    public Page<LogAbfOperate> queryByTimeAndUser(Page<LogAbfOperate> page, Wrapper<LogAbfOperate> wrapper,String operateTime, String userId) throws OperateLogException {

        if (null == operateTime){
            throw new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY,wrap("操作时间为空", "LOG_OPERATE_QUERYBYTIMEANDUSER"));
        }
        if (null == userId){
            throw new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY,wrap("操作员不能为空","LOG_OPERATE_QUERYBYTIMEANDUSER"));
        }

        if (null == wrapper){
            wrapper = new EntityWrapper<LogAbfOperate>();
        }

        wrapper.orderBy(LogAbfOperate.COLUMN_OPERATE_TIME,false);
        wrapper.like(LogAbfOperate.COLUMN_OPERATE_TIME,operateTime);
        wrapper.eq(LogAbfOperate.COLUMN_USER_ID,userId);

        Page<LogAbfOperate> logAbfOperatePage = selectPage(page,wrapper);

        return logAbfOperatePage;
    }
}
