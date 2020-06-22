package ink.taofu.dubbo.service;

import ink.taofu.dubbo.entity.User;

public interface UserService {
    /**
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 获取用户总数
     */
    Integer getUserCount();
}
