package org.tis.tools.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tis.tools.model.common.ResultVO;
import org.tis.tools.model.auth.TisOperator;
import org.tis.tools.security.entity.ToolsUserDetails;
import org.tis.tools.security.feign.IAuthFeignService;

import java.util.Collection;
import java.util.HashSet;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/1
 **/
@Service
public class ToolsUserDetailsService implements UserDetailsService {

    @Autowired
    private IAuthFeignService feignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResultVO resultVO = feignService.loadUserByUserId(username);
        if (resultVO.getResult() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        TisOperator tisOperator = JSONObject.parseObject(resultVO.getResult().toString(), TisOperator.class);
        // TODO 查询用户的角色
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("findclient"));
        ToolsUserDetails userDO = new ToolsUserDetails();
        userDO.setUsername(tisOperator.getUserId());
        userDO.setPassword(tisOperator.getPassword());
        userDO.setEnabled(true);
        userDO.setAuthorities(grantedAuthorities);
        return userDO;
    }
}
