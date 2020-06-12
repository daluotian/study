package ink.taofu.security.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {
    private final String SIGNING_KEY = "taofu"; 

    //令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        //JWT令牌方案
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter () {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置解密秘钥
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }
    
    /*//令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        //内存方式，生成普通令牌
        return new InMemoryTokenStore();
    }*/
}
