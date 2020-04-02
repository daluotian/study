package cn.idealismus.multiDataSource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("cn.idealismus.multiDataSource.mapper")
public class MyBatisPlusConfiguration {
    
    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
    
    @Bean(name = "db0")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db0")
    public DataSource dbo () {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1 () {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    public DataSource multiDataSource (@Qualifier("db0") DataSource db0, @Qualifier("db1") DataSource db1) {
        MultiDataSource multiDataSource = new MultiDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB0, db0);
        targetDataSources.put(DataSourceEnum.DB1, db1);
        //添加数据库
        multiDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multiDataSource.setDefaultTargetDataSource(db0);
        return multiDataSource;
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory () throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multiDataSource(dbo(),db1()));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor()});
        return sqlSessionFactoryBean.getObject();
    }
}
