package ink.taofu.security.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.uaa.dao.RolePermissionMapper;
import ink.taofu.security.uaa.entity.RolePermission;
import ink.taofu.security.uaa.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> 
        implements IRolePermissionService {
    
}
