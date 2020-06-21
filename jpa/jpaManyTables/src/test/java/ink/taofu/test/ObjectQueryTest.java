package ink.taofu.test;

import ink.taofu.jpaManyTables.dao.CustomerDao;
import ink.taofu.jpaManyTables.dao.LinkManDao;
import ink.taofu.jpaManyTables.entity.Customer;
import ink.taofu.jpaManyTables.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 查询一个对象时，通过此对象查询到所有的关联对象
     * 
     * could not initialize proxy - no Session 由于两次不在同一次查询中完成 需要事务
     * 
     * 对象导航查询默认是延迟加载查询，在调用 get 方法的时候并不会查询，而是在使用对象是才会去查询
     * 如果不想延迟加载，修改 entity 配置，将延迟加载修改为立即加载
     *      fetch: 配置到多表映射关系的注解上面
     */
    @Test
    @Transactional
    public void testQuery() {
        //查询客户ID为1的客户
        Customer customer = customerDao.getOne(1l);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkManSet = customer.getLinkManSet();
        for (LinkMan linkMan : linkManSet) {
            System.out.println(linkMan);
        }
    }

    /**
     * 从多的一方导航查询
     * 默认立即加载
     * 查询主体配置多表关联映射的地方设置 fetch
     */
    @Test
    @Transactional
    public void testQuery1(){
        LinkMan linkMan = linkManDao.findOne(2l);

        Customer customer = linkMan.getCustomer();

        System.out.println(customer);
    }
}
