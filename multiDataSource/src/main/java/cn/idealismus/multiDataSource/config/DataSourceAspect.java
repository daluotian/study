package cn.idealismus.multiDataSource.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class DataSourceAspect implements Ordered {
    
    @Pointcut("@within(cn.idealismus.multiDataSource.config.DataSource) || @annotation(cn.idealismus.multiDataSource.config.DataSource)")
    public void pointCut () {
        
    }
    
    @Before("pointCut() && @annotation(dataSource)")
    public void beforeDataSource (DataSource dataSource) {
        log.info("选择了数据源-----" + dataSource.value().getValue());
        DataSourceContentHolder.setDataSource(dataSource.value().getValue());
    }
    
    @After("pointCut()")
    public void afterPointCut () {
        DataSourceContentHolder.clearDataSource();
    }
    
    @Override
    public int getOrder() {
        //@Order(1) 或者使用这个注解实现 这里是设置加载顺序 数字越低，加载越前
        return 1;
    }
}
