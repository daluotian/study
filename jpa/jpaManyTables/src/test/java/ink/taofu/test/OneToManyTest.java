package ink.taofu.test;

import ink.taofu.jpaManyTables.dao.CustomerDao;
import ink.taofu.jpaManyTables.dao.LinkManDao;
import ink.taofu.jpaManyTables.entity.Customer;
import ink.taofu.jpaManyTables.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private LinkManDao linkManDao;
    @Autowired
    private CustomerDao customerDao;
    
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("baidu");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("xiaoli");
        //配置联系人到客户 三条语句 两条insert 一条update
        //customer.getLinkManSet().add(linkMan);
        //配置客户到联系人 三条语句 两条insert
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testAdd1() {
        Customer customer = new Customer();
        customer.setCustName("baidu");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("xiaoli");
        //配置联系人到客户 三条语句 两条insert 一条update
        customer.getLinkManSet().add(linkMan);
        customerDao.save(customer);

        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testAdd2() {
        Customer customer = new Customer();
        customer.setCustName("baidu");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("xiaoli");
        //配置客户到联系人 两条语句 两条insert
        linkMan.setCustomer(customer);
        customerDao.save(customer);

        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testAdd3() {
        Customer customer = new Customer();
        customer.setCustName("baidu");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("xiaoli");
        //配置联系人到客户
        customer.getLinkManSet().add(linkMan);
        //配置客户到联系人
        linkMan.setCustomer(customer);
        
        //最终还是三条sql 两条insert 一条update 但是这个update是多余的
        //所以需要在在一这一方放弃维护外键
        //放弃的做法思路 修改entity
        customerDao.save(customer);

        linkManDao.save(linkMan);
    }

    /**
     * 级联添加: 保存客户的同时将联系人同时保存
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testCascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("baidu1");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("xiaoli1");
        //配置联系人到客户
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
    }

    /**
     * 级联删除: 删除一号客户的同时将一号客户的所有联系人删除
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testCascadeDel() {
        Customer customer = customerDao.findOne(2l);
        customerDao.delete(customer);
    }
}
