package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
import java.util.List;

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
    public List<LogAbfOperate> queryByCondition(String condition) throws OperateLogException {

        if (null == condition){
            throw new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY,wrap("","LOG_OPERATE_QUERYBYCONDITION"));
        }

        Wrapper<LogAbfOperate> wrapper = new EntityWrapper<LogAbfOperate>();
        wrapper.orderBy(LogAbfOperate.COLUMN_OPERATE_TIME,false);
        wrapper.like(LogAbfOperate.COLUMN_OPERATE_TIME,condition);

        List<LogAbfOperate> logAbfOperateList = selectList(wrapper);


        return logAbfOperateList;
    }
}
