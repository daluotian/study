package ink.taofu.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.taofu.security.entity.Permission;

import java.util.List;

public interface IPermissionService extends IService<Permission> {
    List<Permission> getPermissionsByUserId (String userId);

    Permission getPermissionById(String id);
}
