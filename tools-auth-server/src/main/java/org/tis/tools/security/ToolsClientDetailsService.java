package org.tis.tools.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.security.entity.ToolsClientDetails;
import org.tis.tools.security.feign.IAuthFeignService;
import org.tis.tools.security.service.IToolsClientDetailsService;

/**
 * @author zhuwj
 * @version V1.0
 * @Description: oauth客户端实现
 * @date 2018/1/16.
 */
@Service
public class ToolsClientDetailsService implements ClientDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ToolsClientDetailsService.class);

    @Autowired
    private IToolsClientDetailsService clientDetailsService;

    @Autowired
    private IAuthFeignService authFeignService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ResultVO resultVO = authFeignService.loadClientById("1001403266359119874");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + resultVO);


        ToolsClientDetails client = clientDetailsService.selectById(clientId);
        String resourceIds = client.getResourceIds();
        String scopes = client.getScope();
        String grantTypes = client.getAuthorizedGrantTypes();
        String authorities = client.getAuthorities();
        String redirectUris = client.getWebServerRedirectUri();
        Integer refreshTokenValiditySeconds = client.getRefreshTokenValiditySeconds();
        Integer accessTokenValiditySeconds = client.getAccessTokenValiditySeconds();
        String clientSecret = client.getClientSecret();
        logger.info("clientDetailsDao  info : " + client.toString());
        BaseClientDetails baseClientDetails = new BaseClientDetails(clientId, resourceIds,
                scopes, grantTypes, authorities, redirectUris);
        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        baseClientDetails.setClientSecret(clientSecret);
//        baseClientDetails.setClientId("webapp");
//        baseClientDetails.setClientSecret("webapp");
        return baseClientDetails;
    }
}
