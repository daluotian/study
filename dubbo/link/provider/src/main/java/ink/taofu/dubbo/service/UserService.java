package ink.taofu.dubbo.service;

import ink.taofu.dubbo.entity.User;

public interface UserService {
    User queryUserById(Integer id);
}
