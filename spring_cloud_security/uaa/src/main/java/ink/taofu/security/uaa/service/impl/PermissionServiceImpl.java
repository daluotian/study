package ink.taofu.security.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.uaa.dao.PermissionMapper;
import ink.taofu.security.uaa.entity.Permission;
import ink.taofu.security.uaa.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> 
        implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    
    @Override
    public List<Permission> getPermissionsByUserId(String userId) {
        return permissionMapper.getPermissionsByUserId(userId);
    }

    @Override
    public Permission getPermissionById(String id) {
        return permissionMapper.getPermissionById(id);
    }
}
