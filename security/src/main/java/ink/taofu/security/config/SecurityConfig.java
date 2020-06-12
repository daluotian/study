package ink.taofu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    //定义密码编码器
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /*//定义用户信息服务
    @Bean
    public UserDetailsService userDetailsService () {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password(passwordEncoder().encode("123")).authorities("one").build());
        manager.createUser(User.withUsername("lisi").password(passwordEncoder().encode("456")).authorities("two").build());
        return manager;
    }*/
    
    //定义安全拦截器

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                //.antMatchers("/page/one").hasAuthority("one")
                //.antMatchers("/page/two").hasAuthority("two")
                //配置所有page路径下的页面都需要被认证
                .antMatchers("/page/**").authenticated()
                //其余所有路径都直接通过
                .anyRequest().permitAll()
                .and()
                //允许表单登录
                .formLogin()
                //登录页面
                .loginPage("/myLogin")
                //登录处理的url
                .loginProcessingUrl("/login")
                .successForwardUrl("/success")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/myLogin?logout");
    }
}
