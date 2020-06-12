package ink.taofu.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @RequestMapping("success")
    public String login () {
        return getUsername() + "登录成功";
    }
    
    @RequestMapping("page/one")
    @PreAuthorize("hasAuthority('one')")
    public String pageOne() {
        return getUsername() + "访问了one";
    }

    @RequestMapping("page/two")
    @PreAuthorize("hasAuthority('two')")
    public String pageTwo() {
        return getUsername() + "访问了two";
    }
    
    private String getUsername () {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户身份
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        } else {
            return principal.toString();
        }
    }
}
