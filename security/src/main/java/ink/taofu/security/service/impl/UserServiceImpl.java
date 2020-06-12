package ink.taofu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.taofu.security.dao.UserMapper;
import ink.taofu.security.entity.TUser;
import ink.taofu.security.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements IUserService {
    
}
