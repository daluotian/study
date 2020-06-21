package ink.taofu.springJPA.test;

import ink.taofu.springJPA.dao.CustomerDao;
import ink.taofu.springJPA.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//声明单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//制定 spring 容器的配置信息
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据ID查询
     */
    @Test
    public void testFindOne () {
        Customer one = customerDao.findOne(1l);
        System.out.println(one);
    }

    /**
     * save: 保存或更新
     *  根据传递的实体类是否主键ID在表中存在
     *      若存在 修改
     *      不存在 保存
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustId(5l);
        customer.setCustName("ideal");
        customer.setCustAddress("hz");
        customer.setCustLevel("vip2");
        customerDao.save(customer);
    }

    /**
     * delete: 删除
     *  主键ID
     */
    @Test
    public void testDelete() {
        customerDao.delete(4l);
    }

    /**
     * findAll: 查询所有
     * 
     */
    @Test
    public void testFindAll() {
        List<Customer> all = customerDao.findAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /**
     * 测试count
     */
    @Test
    public void testCount() {
        System.out.println(customerDao.count());
    }

    /**
     * 测试exists
     */
    @Test
    public void testExists() {
        System.out.println(customerDao.exists(4l));
    }

    /**
     * 测试getOne
     *  需要加一个Transactional注解
     *  findOne em.find()
     *  getOne em.getReference 延迟加载,所以需要事务注解
     */
    @Test
    @Transactional
    public void testGetOne() {
        System.out.println(customerDao.getOne(5l));
    }
}
