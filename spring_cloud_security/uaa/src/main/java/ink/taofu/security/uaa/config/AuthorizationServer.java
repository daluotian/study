package ink.taofu.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Resource
    private TokenStore tokenStore;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtAccessTokenConverter accessTokenConverter;
    @Resource
    private PasswordEncoder passwordEncoder;
    
    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices () {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //客户端信息服务
        tokenServices.setClientDetailsService(clientDetailsService);
        //是否产生刷新令牌
        tokenServices.setSupportRefreshToken(true);
        //令牌存储策略
        tokenServices.setTokenStore(tokenStore);
        //设置令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        
        //令牌默认失效时间 单位为秒
        tokenServices.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期 3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }
    
    //令牌访问端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //oauth/token_key 公开
        security.tokenKeyAccess("permitAll")
                //检测令牌的路径 oauth/check_token 公开
                .checkTokenAccess("permitAll")
                //允许表单模式申请 申请令牌
                .allowFormAuthenticationForClients();
    }

    //客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        //使用内存方式
        /*clients.inMemory()
                //客户端ID
                .withClient("c1")
                //客户端秘钥
                .secret(new BCryptPasswordEncoder().encode( "secret"))
                //客户端可访问的资源列表
                .resourceIds("res1")
                //允许客户端申请授权方式
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                //授权范围，客户端权限
                .scopes("all")
                //是否默认授权 false 跳转到授权页面 true 不会跳转到授权页面
                .autoApprove(false)
                //回调地址
                .redirectUris("https://www.baidu.com");*/
    }
    
    //将客户端信息存到数据库
    @Bean
    public ClientDetailsService clientDetailsService (DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return  clientDetailsService;
    }
    
    //令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //设置密码模式,认证管理器
        endpoints.authenticationManager(authenticationManager)
                //授权码需要，授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(tokenServices())
                //允许访问方式 post
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    //设置授权码，使用内存的方式存储
    /*@Bean
    public AuthorizationCodeServices authorizationCodeServices () {
        return new InMemoryAuthorizationCodeServices();
    }*/
    //使用数据库的方式存储授权码
    @Bean
    public AuthorizationCodeServices authorizationCodeServices (DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
