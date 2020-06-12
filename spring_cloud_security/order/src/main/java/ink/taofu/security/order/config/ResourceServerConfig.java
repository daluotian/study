package ink.taofu.security.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Resource
    private TokenStore tokenStore;
    
    //这里的这个ID与uaa中配置的 .resourceIds("res1") 一致
    private static final String RESOURCE_ID = "res1";
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源ID
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                //验证令牌的服务
                //.tokenServices(tokenServices())
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
                .and().csrf().disable()
                //取消session认证，因为这里是用的token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    /*//资源服务令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenServices () {
        RemoteTokenServices services = new RemoteTokenServices();
        //配置验证解析token解析服务的端点url
        services.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
        services.setClientId("c1");
        services.setClientSecret("secret");
        return services;
    }*/
}
