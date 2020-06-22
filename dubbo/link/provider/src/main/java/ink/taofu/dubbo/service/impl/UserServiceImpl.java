package ink.taofu.dubbo.service.impl;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("zhangsan");
        user.setAge(23);
        return user;
    }
}
