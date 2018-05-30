package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfChangeMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import org.tis.tools.abf.module.jnl.service.ILogAbfChangeService;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/3/29
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfChangeServiceImpl extends ServiceImpl<LogAbfChangeMapper, LogAbfChange>
        implements ILogAbfChangeService {

    @Override
    public Page<LogAbfChange> queryPageById(Page<LogAbfChange> page, Wrapper<LogAbfChange> wrapper, String id) {

        if (null == wrapper){
            wrapper = new EntityWrapper<LogAbfChange>();
        }

        wrapper.eq(LogAbfChange.COLUMN_GUID_DATA,id);

        Page<LogAbfChange> pageChange = selectPage(page,wrapper);

        return null;
    }
}
