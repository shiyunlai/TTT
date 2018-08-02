package org.tis.tools.security;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.tis.tools.model.common.ResultVO;
import org.tis.tools.model.auth.TisApp;
import org.tis.tools.security.feign.IAuthFeignService;

/**
 * description: oauth客户端实现
 * @date 2018/1/16.
 */
@Service
public class ToolsClientDetailsService implements ClientDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ToolsClientDetailsService.class);


    @Autowired
    private IAuthFeignService authFeignService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ResultVO resultVO = authFeignService.loadClientByAppCode(clientId);
        if (resultVO.getResult() == null) {
            throw new ClientRegistrationException("找不到应用！");
        }
        TisApp tisApp = JSONObject.parseObject(resultVO.getResult().toString(), TisApp.class);

//        String resourceIds = client.getResourceIds();
//        String scopes = client.getScope();
//        String grantTypes = client.getAuthorizedGrantTypes();
//        String authorities = client.getAuthorities();
//        String redirectUris = client.getWebServerRedirectUri();
//        Integer refreshTokenValiditySeconds = client.getRefreshTokenValiditySeconds();
//        Integer accessTokenValiditySeconds = client.getAccessTokenValiditySeconds();
//        String clientSecret = client.getClientSecret();
//        logger.info("clientDetailsDao  info : " + client.toString());
        BaseClientDetails baseClientDetails = new BaseClientDetails(clientId, null,
                "all", "password", null, tisApp.getUrl());

        // token有效期自定义设置，默认12小时
        baseClientDetails.setRefreshTokenValiditySeconds(60 * 60 * 12);
        //默认30天，这里修改
        baseClientDetails.setAccessTokenValiditySeconds(60 * 60 * 24 * 7);
        baseClientDetails.setClientSecret("123456");
//        baseClientDetails.setClientId("webapp");
//        baseClientDetails.setClientSecret("webapp");
        return baseClientDetails;
    }
}
