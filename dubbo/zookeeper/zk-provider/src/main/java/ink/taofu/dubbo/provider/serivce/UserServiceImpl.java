package ink.taofu.dubbo.provider.serivce;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(23);
        return user;
    }
}
