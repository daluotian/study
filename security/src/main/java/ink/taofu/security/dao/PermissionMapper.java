package ink.taofu.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.taofu.security.entity.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> getPermissionsByUserId (String userId);
    
    Permission getPermissionById (String id);
}
