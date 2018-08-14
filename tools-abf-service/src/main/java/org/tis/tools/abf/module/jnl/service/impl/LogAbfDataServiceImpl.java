package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfDataMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;
import org.tis.tools.abf.module.jnl.exception.OperateLogExceptionCodes;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.tis.tools.core.utils.StringUtil;

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
    public Page<LogAbfData> queryByDataId(Page<LogAbfData> page, Wrapper<LogAbfData> wrapper,String dataGuid) throws OperateLogException {

        if (null == wrapper){
            wrapper = new EntityWrapper<LogAbfData>();
        }

        wrapper.eq(LogAbfData.COLUMN_DATA_GUID,dataGuid);

        Page<LogAbfData> pageData = selectPage(page,wrapper);

        return pageData;
    }

    /**
     * 查询信息信息
     *
     * @param id
     * @throws OperateLogException
     */
    @Override
    public Object queryDetialMessage(String id) throws OperateLogException {

        //通过id查询操作的数据记录
        LogAbfData logAbfData = selectById(id);

        if (null == logAbfData){
            throw new OperateLogException(OperateLogExceptionCodes.RETURN_RESULT_IS_NULL,wrap("找不到对应记录或已经被删除"));
        }

        Object entity = new Object();

        //获取到该数据的详细信息
        String dataString = logAbfData.getDataString();
        String dataClass = logAbfData.getDataClass();
        if (!StringUtil.isEmpty(dataString) && !StringUtil.isEmpty(dataClass)){
            ObjectMapper mapper = new ObjectMapper();
            try {
                entity = mapper.readValue(dataString,Class.forName(dataClass));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return entity;
    }
}

