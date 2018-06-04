package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfDataMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;

import java.util.List;

/**
 * logAbfData的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfDataServiceImpl extends ServiceImpl<LogAbfDataMapper, LogAbfData> implements ILogAbfDataService {

    @Autowired
    ILogAbfDataService logAbfDataService;

    @Override
    public Page<LogAbfData> queryPageById(Page<LogAbfData> page, Wrapper<LogAbfData> wrapper, String id) throws OperateLogException {

        if (null == wrapper){
            wrapper = new EntityWrapper<LogAbfData>();
        }

        wrapper.eq(LogAbfData.COLUMN_GUID_OPERATE,id);
        Page<LogAbfData> pageData = logAbfDataService.selectPage(page,wrapper);

        return pageData;
    }

    @Override
    public List<LogAbfData> queryByDataId(String dataGuid) throws OperateLogException {

        Wrapper<LogAbfData> wrapper = new EntityWrapper<LogAbfData>();
        wrapper.eq(LogAbfData.COLUMN_DATA_GUID,dataGuid);

        List<LogAbfData> list = selectList(wrapper);

        return list;
    }
}

