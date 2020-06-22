package ink.taofu.dubbo.provider.service.impl;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("zhangsan");
        user.setAge(23);
        return user;
    }

    @Override
    public Integer getUserCount() {
        return 42;
    }
}
