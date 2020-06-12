package ink.taofu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.dao.RoleMapper;
import ink.taofu.security.entity.Role;
import ink.taofu.security.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    
}
