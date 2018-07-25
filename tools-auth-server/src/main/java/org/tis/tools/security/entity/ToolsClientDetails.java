package org.tis.tools.security.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/1
 **/
@Data
public class ToolsClientDetails {

    @TableId
    private String clientId;

    private String clientSecret;

    private String resourceIds;

    private String scope;

    private String authorities;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String  autoApproveScopes;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String additionalInformation;

}
