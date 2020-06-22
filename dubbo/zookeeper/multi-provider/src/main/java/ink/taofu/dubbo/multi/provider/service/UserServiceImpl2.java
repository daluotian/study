package ink.taofu.dubbo.multi.provider.service;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;

public class UserServiceImpl2 implements UserService {
    
    @Override
    public User getUserById(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setAge(28);
        user.setName(name+"--2");
        return user;
    }
}
