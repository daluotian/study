package ink.taofu.test;

import ink.taofu.jpabase.entity.Customer;
import ink.taofu.jpabase.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JPQLTest {
    /**
     * 查询全部
     * sql: select * from cst_customer
     * jpql: from ink.taofu.jpabase.entity.Customer
     */
    @Test
    public void testFindAll () {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //语句
        //String jpql = "from ink.taofu.jpabase.entity.Customer";
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //发送查询，并获取结果集
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj.toString());
        }
        
        transaction.commit();
        entityManager.close();
    }

    /**
     * 根据ID倒叙
     * sql: select * from cst_customer order by cust_id desc
     * jpql: from ink.taofu.jpabase.entity.Customer order by custId desc
     * 
     * jpql查询步骤
     *  创建查询对象
     *  对参数进行赋值
     *  查询，并返回结果
     */
    @Test
    public void testOrder () {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //语句
        //String jpql = "from ink.taofu.jpabase.entity.Customer";
        String jpql = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);
        //发送查询，并获取结果集
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj.toString());
        }

        transaction.commit();
        entityManager.close();
    }

    /**
     * jpql统计
     *  sql: select count(cust_id) from cst_customer
     *  jpql: select count(custId) from Customer 
     */
    @Test
    public void testCount () {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //语句
        //String jpql = "from ink.taofu.jpabase.entity.Customer";
        String jpql = "select count(custId) from Customer ";
        //创建查询对象
        Query query = entityManager.createQuery(jpql);
        //对参数赋值 省略
        
        //发送查询，并获取结果集
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        transaction.commit();
        entityManager.close();
    }

    /**
     * jpql分页
     *  sql: select * from cst_customer limit ?,?
     *  jpql: from Customer
     */
    @Test
    public void testPage () {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //语句
        //String jpql = "from ink.taofu.jpabase.entity.Customer";
        String jpql = "from Customer ";
        //创建查询对象
        Query query = entityManager.createQuery(jpql);
        //对参数赋值 设置分页参数
        //设置起始索引
        query.setFirstResult(0);
        //设置每页数量
        query.setMaxResults(1);
        //发送查询，并获取结果集
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * jpql条件查询
     * 客户名称以ta开头的客户
     *  sql: select * from cst_customer where cust_name like 'ta%'
     *  jpql: from Customer where custName like ? 
     */
    @Test
    public void testCondition () {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //语句
        //String jpql = "from ink.taofu.jpabase.entity.Customer";
        String jpql = "from Customer where custName like ?";
        //创建查询对象
        Query query = entityManager.createQuery(jpql);
        //对参数赋值
        //设置参数占位符位置(1开始)
        query.setParameter(1, "ta%");
        //发送查询，并获取结果集
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }
        transaction.commit();
        entityManager.close();
    }
}
