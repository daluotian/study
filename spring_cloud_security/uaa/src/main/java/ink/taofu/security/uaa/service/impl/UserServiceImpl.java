package ink.taofu.security.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.uaa.dao.UserMapper;
import ink.taofu.security.uaa.entity.TUser;
import ink.taofu.security.uaa.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements IUserService {
    
}
