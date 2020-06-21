package ink.taofu.test;

import ink.taofu.jpaManyTables.dao.RoleDao;
import ink.taofu.jpaManyTables.dao.UserDao;
import ink.taofu.jpaManyTables.entity.Role;
import ink.taofu.jpaManyTables.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserName("xiaoli");

        Role role = new Role();
        role.setRoleName("ceo");
        //用户与角色的关系
        user.getRoles().add(role);
        userDao.save(user);
        roleDao.save(role);
    }
    
    /**
     * 保存一个用户，保存一个角色
     * 报错 Duplicate entry '1-1' for key 'sys_user_role.PRIMARY' 主键冲突
     * 解决方法，一方放弃维护权
     * 多对多中放弃维护权
     * 被动的一方放弃维护权，此处 role 放弃维护权，修改 entity 中 role 实体类
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd1(){
        User user = new User();
        user.setUserName("xiaoli");

        Role role = new Role();
        role.setRoleName("ceo");
        //用户与角色的关系，可以对中间表的数据进行维护 1-1
        user.getRoles().add(role);
        //角色与用户的关系，可以对中间表的数据进行维护 1-1
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 多对多级联 保存一个用户的同时，保存用户的关联角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        User user = new User();
        user.setUserName("xiaoli");

        Role role = new Role();
        role.setRoleName("ceo");
        //用户与角色的关系，可以对中间表的数据进行维护 1-1
        user.getRoles().add(role);
        //角色与用户的关系，可以对中间表的数据进行维护 1-1
        role.getUsers().add(user);
        userDao.save(user);
    }

    /**
     * 多对多级联 删除ID为1的用户同时，删除用户的关联角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeDel(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }
}
