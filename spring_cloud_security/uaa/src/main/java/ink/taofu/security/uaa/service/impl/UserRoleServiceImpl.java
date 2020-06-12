package ink.taofu.security.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.uaa.dao.UserRoleMapper;
import ink.taofu.security.uaa.entity.UserRole;
import ink.taofu.security.uaa.service.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    
}
