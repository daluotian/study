package ink.taofu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.dao.UserRoleMapper;
import ink.taofu.security.entity.UserRole;
import ink.taofu.security.service.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    
}
