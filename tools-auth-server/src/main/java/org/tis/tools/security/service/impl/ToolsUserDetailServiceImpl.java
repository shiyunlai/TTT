package org.tis.tools.security.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tis.tools.security.dao.ToolsUserDetailsMapper;
import org.tis.tools.security.entity.ToolsUserDetails;
import org.tis.tools.security.service.IToolsUserDetailsService;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/3
 **/
@Service
public class ToolsUserDetailServiceImpl extends ServiceImpl<ToolsUserDetailsMapper, ToolsUserDetails> implements IToolsUserDetailsService {
}
