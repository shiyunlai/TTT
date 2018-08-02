package org.tis.tools.gateway.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.tis.tools.security.ToolsAuthorizationTokenServices;
import org.tis.tools.security.ToolsClientDetailsService;
import org.tis.tools.security.ToolsJwtTokenEnhancer;
import org.tis.tools.security.ToolsUserDetailsService;


/**
 * describe: 
 *
 * @author zhaoch
 * @date 2018/5/31
 **/
@Configuration
@EnableAuthorizationServer
public class ToolsAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;


//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ToolsClientDetailsService clientDetailsService;

    @Autowired
    private ToolsUserDetailsService toolsUserDetailsService;

    /**
     * @return
     */
    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
//
//    @Bean
//    public ClientDetailsService clientDetailsService() {
//        return new ToolsClientDetailsService();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new ToolsUserDetailsService();
//    }

//    @Bean
//    public JdbcTokenStore tokenStore(DataSource dataSource) {
//        return new JdbcTokenStore(dataSource);
//    }

    /**
     * tokenKey的访问权限表达式配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    /**
     * 客户端配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 认证及token配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(redisTokenStore())
//                .tokenStore(new InMemoryTokenStore())
//                .tokenServices(authorizationServerTokenServices())
                .tokenServices(defaultTokenServices())
                .accessTokenConverter(accessTokenConverter())
//                .exceptionTranslator(webResponseExceptionTranslator) // TODO 添加Hystrix时使用
                .userDetailsService(toolsUserDetailsService);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new ToolsJwtTokenEnhancer();
        // 添加JWT的签名key
        converter.setSigningKey("secret");
        return converter;
    }

//    @Primary
//    @Bean
//    public AuthorizationServerTokenServices authorizationServerTokenServices() {
//        ToolsAuthorizationTokenServices customTokenServices = new ToolsAuthorizationTokenServices();
//        customTokenServices.setTokenStore(redisTokenStore());
////        customTokenServices.setTokenStore(new InMemoryTokenStore());
//        customTokenServices.setSupportRefreshToken(true);
//        customTokenServices.setReuseRefreshToken(true);
//        customTokenServices.setClientDetailsService(clientDetailsService());
//        customTokenServices.setTokenEnhancer(accessTokenConverter());
//        return customTokenServices;
//    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenEnhancer(accessTokenConverter());
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }


}
