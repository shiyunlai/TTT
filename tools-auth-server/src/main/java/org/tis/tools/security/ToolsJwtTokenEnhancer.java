package org.tis.tools.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.tis.tools.security.entity.ToolsUserDetails;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: JWT TOKEN 增强类
 *
 * @author zhaoch
 * @date 2018/5/31
 **/
public class ToolsJwtTokenEnhancer extends JwtAccessTokenConverter implements Serializable {

    private static final String TOKEN_SEG_USER_ID = "X-Tools-UserId";
    private static final String TOKEN_SEG_CLIENT = "X-Tools-ClientId";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        ToolsUserDetails userDetails = (ToolsUserDetails) authentication.getPrincipal();
//        authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put(TOKEN_SEG_USER_ID, userDetails.getUserId());
        info.put(TOKEN_SEG_CLIENT, userDetails.getClientId());
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        //        enhancedToken.getAdditionalInformation().put(TOKEN_SEG_CLIENT, userDetails.getClientId());
        return super.enhance(customAccessToken, authentication);
    }

}
