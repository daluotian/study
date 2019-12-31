package cn.idealismus.bean;


import org.springframework.context.annotation.Bean;

/**
 * 类上没有spring注解，所以这段代码不会被执行，bean不会被创建
 */
public class UserConfiguration {
    @Bean
    public User getUser () {
        User user = new User();
        user.setAge(12);
        user.setUserName("变态");
        return user;
    }
}
