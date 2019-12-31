package cn.idealismus.mybatis_plus_mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.idealismus.novel.dao")
public class MybatisPlusMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusMysqlApplication.class, args);
    }

}
