package ink.taofu.dubbo.multi.provider.service;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;

public class UserServiceImpl1 implements UserService {
    @Override
    public User getUserById(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setAge(23);
        user.setName(name+"--1");
        return user;
    }
}
