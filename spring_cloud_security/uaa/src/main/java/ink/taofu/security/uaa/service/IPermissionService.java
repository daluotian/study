package ink.taofu.security.uaa.service;


import com.baomidou.mybatisplus.extension.service.IService;
import ink.taofu.security.uaa.entity.Permission;

import java.util.List;

public interface IPermissionService extends IService<Permission> {
    List<Permission> getPermissionsByUserId(String userId);

    Permission getPermissionById(String id);
}
