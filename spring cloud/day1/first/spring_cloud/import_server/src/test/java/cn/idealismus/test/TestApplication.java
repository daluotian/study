package cn.idealismus.test;

import cn.idealismus.bean.EnableUserBean;
import cn.idealismus.bean.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableUserBean
public class TestApplication {
    public static void main(String[] args) {
        //获取spring容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestApplication.class);
        User user = ac.getBean(User.class);
        System.out.println(user);
    }
}
