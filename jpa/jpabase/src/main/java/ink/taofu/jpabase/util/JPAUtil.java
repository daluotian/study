package ink.taofu.jpabase.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂的浪费资源问题
 *      使用静态代码块，在程序第一次访问该工具类时，创建一个公共的实体管理器工厂对象
 * 程序第一次访问，getEntityManager 经过静态代码块创建一个工厂对象赋值给静态属性
 *      再调用方法创建一个entityManager
 * 第二次访问，通过已经创建好的factory创建entityManager
 */
public class JPAUtil {
    private static EntityManagerFactory factory;
    
    static {
        //加载配置文件，创建 EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJPA");
    }

    /**
     * 获取entityManager对象
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
