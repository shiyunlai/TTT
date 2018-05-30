package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfDataMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * logAbfData的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfDataServiceImpl extends ServiceImpl<LogAbfDataMapper, LogAbfData> implements ILogAbfDataService {

    /**
     * 查询
     * @param operateGuid
     * @return
     */
    @Override
    public List<LogAbfData> queryForOperate(String operateGuid) throws OperateLogException {

        if (null == operateGuid){
            throw  new OperateLogException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY,wrap("","LOG_DATA_QUERY_FOR_OPERATE"));
        }

        Wrapper<LogAbfData> wrapper = new EntityWrapper<LogAbfData>();
        wrapper.eq(LogAbfData.COLUMN_GUID_OPERATE,operateGuid);

        List<LogAbfData> logAbfDataList = selectList(wrapper);

        return logAbfDataList;
    }
}

