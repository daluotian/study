package ink.taofu.test;

import ink.taofu.jpabase.entity.Customer;
import ink.taofu.jpabase.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    /**
     * 测试JPA的保存
     * Jpa操作步骤
     *  1: 加载配置文件创建工厂(实体类管理工厂)对象
     *  2: 通过实体类管理工厂获得实体管理器
     *  3: 获取事务对象，开启事务
     *  4: 完成增删改查操作
     *  5: 提交事务或者回滚事务
     *  6: 释放资源
     */
    @Test
    public void testSave() {
        //加载配置文件创建工厂(实体类管理工厂)对象
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJPA");
        //通过实体类管理工厂获得实体管理器
        EntityManager em = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Customer customer = new Customer();
        customer.setCustName("taofu");
        customer.setCustIndustry("myths");
        //保存
        em.persist(customer);
        //提交事务或者回滚事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test
    public void testFind() {
        //通过实体类管理工厂获得实体管理器
        EntityManager em = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //保存
        Customer customer = em.find(Customer.class, 1L);
        System.out.println(customer);
        //提交事务或者回滚事务
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 延迟加载
     *  得到一个代理对象
     *  在使用到这个代理对象的时候才会真正去查询
     */
    @Test
    public void testReference() {
        //通过实体类管理工厂获得实体管理器
        EntityManager em = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //保存
        Customer customer = em.getReference(Customer.class, 1L);
        System.out.println(customer);
        //提交事务或者回滚事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test
    public void testRemove() {
        //通过实体类管理工厂获得实体管理器
        EntityManager em = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.getReference(Customer.class, 3L);
        //保存
        em.remove(customer);
        //提交事务或者回滚事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test
    public void testMerge() {
        //通过实体类管理工厂获得实体管理器
        EntityManager em = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.getReference(Customer.class, 1L);
        customer.setCustName("taofu1");
        //保存
        em.merge(customer);
        //提交事务或者回滚事务
        tx.commit();
        //释放资源
        em.close();
    }
}
