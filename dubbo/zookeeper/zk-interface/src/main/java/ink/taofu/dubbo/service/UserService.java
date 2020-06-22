package ink.taofu.dubbo.service;

import ink.taofu.dubbo.entity.User;

public interface UserService {
    /**
     * 根据ID获取用户的信息
     * @param id
     * @return
     */
    User getUserById(Integer id, String name);
}
