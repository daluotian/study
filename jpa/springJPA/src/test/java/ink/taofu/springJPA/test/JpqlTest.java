package ink.taofu.springJPA.test;

import ink.taofu.springJPA.dao.CustomerDao;
import ink.taofu.springJPA.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 如果结果集有多条，但是返回参数为单个类的话 会报错
     * javax.persistence.NonUniqueResultException: result returns more than one elements
     */
    @Test
    public void testFindJPQL() {
        /*
        List<Customer> list = customerDao.findJqpl("taofu");
        for (Customer customer : list) {
            System.out.println(customer);
        }
        */
        Customer customer = customerDao.findJqpl("taofu");
        System.out.println(customer);
    }
    
    @Test
    public void testFindByCustNameAndId() {
        Customer customer = customerDao.findByCustNameAndId(2l, "taofu");
        System.out.println(customer);
    }

    /**
     * 若不加事务 
     * org.springframework.dao.InvalidDataAccessApiUsageException: 
     * Executing an update/delete query; 
     * nested exception is javax.persistence.TransactionRequiredException: Executing an update/delete query
     * 
     * spring data jpa 中使用 jqpl 进行 修改/删除操作
     *  需要手动添加事务
     *  默认结束执行之后，回滚事务
     *  
     * 需要添加注解
     * @RollBack false-取消自动回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(5l, "myth");
    }
    
    @Test
    public void testFindSql() {
        List<Object[]> findsql = customerDao.findsql("taofu%");
        for (Object[] obj : findsql) {
            System.out.println(Arrays.toString(obj));
        }
    }
    
    @Test
    public void testNaming() {
        List<Customer> list = customerDao.findByCustName("taofu");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindByLike() {
        List<Customer> list = customerDao.findByCustNameLike("taofu%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    
    @Test
    public void testFindByLikeAnd() {
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("taofu%", "myth");
        System.out.println(customer);
    }
}
