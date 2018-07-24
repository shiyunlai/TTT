package org.tis.tools.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tis.tools.core.web.vo.ResultVO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by zhuwj on 2018/1/24.
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @PostMapping("/login")
    public ResultVO login(@RequestParam Map<String, String> parameters){
        try {
            logger.info("login  start ......");
            // 设置授权类型为密码模式
            parameters.put("grant_type","password");
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
            // 此处不能为空
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
            Authentication authentication = new UsernamePasswordAuthenticationToken("webapp", "webapp",grantedAuthorities);
            ResponseEntity<OAuth2AccessToken> responseEntity= tokenEndpoint.postAccessToken(authentication ,parameters);
            logger.info("login  end ......");
            return ResultVO.success(responseEntity.getBody());
        }catch (InvalidGrantException e){
            logger.error("login error  用户名密码不正确 ....",e);
            return ResultVO.error("用户名密码不正确");
        }
        catch (Exception e){
            logger.error("login error ....",e);
            return ResultVO.error(e.getMessage());
        }
    }


    @GetMapping("/logout")
    public ResultVO logout(String accessToken){
        try {
            logger.info("logout  start ......");
            consumerTokenServices.revokeToken(accessToken);
            logger.info("logout  end ......");
            return ResultVO.success("退出登入成功");
        }
        catch (Exception e){
            logger.error("logout error ....",e);
            return ResultVO.error(e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResultVO test(){
        return ResultVO.success("String");
    }
}
