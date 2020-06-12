package ink.taofu.security.uaa.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ink.taofu.security.uaa.entity.Permission;
import ink.taofu.security.uaa.entity.TUser;
import ink.taofu.security.uaa.service.IPermissionService;
import ink.taofu.security.uaa.service.IUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //账号
        System.out.println(s);
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", s);
        TUser user = userService.getOne(queryWrapper);
        List<Permission> permission = permissionService.getPermissionsByUserId(user.getId());
        String[] auths = new String[permission.size()];
        for (int i = 0; i < permission.size(); i++) {
            auths[i] = permission.get(i).getCode();
        }
        UserDetails userDetails = User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities(auths).build();
        return userDetails;
    }
}
