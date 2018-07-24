package org.tis.tools.security.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tis.tools.security.dao.ToolsClientDetailsMapper;
import org.tis.tools.security.entity.ToolsClientDetails;
import org.tis.tools.security.service.IToolsClientDetailsService;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/6/4
 **/
@Service
public class ToolsClientDetailsServiceImpl extends ServiceImpl<ToolsClientDetailsMapper, ToolsClientDetails>
        implements IToolsClientDetailsService {
}
