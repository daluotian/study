package cn.idealismus.bean;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class UserImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //根据实际需求配置类的名称
        return new String[]{UserConfiguration.class.getName()};
    }
    
}
