package org.tis.tools.abf.module.jnl.service.impl;

import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.jnl.dao.LogAbfDataMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.springframework.transaction.annotation.Transactional;

/**
 * logAbfData的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/05/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfDataServiceImpl extends ServiceImpl<LogAbfDataMapper, LogAbfData> implements ILogAbfDataService {

}

