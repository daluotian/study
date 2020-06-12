package ink.taofu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.dao.RolePermissionMapper;
import ink.taofu.security.entity.RolePermission;
import ink.taofu.security.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> 
        implements IRolePermissionService {
    
}
