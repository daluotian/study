package ink.taofu.dubbo.multi.controller;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService1;
    
    @Autowired
    private UserService userService2;
    
    @RequestMapping("/user")
    public String user(Model model, Integer id, String name) {
        User user1 = userService1.getUserById(id, name);
        model.addAttribute("user1", user1);
        User user2 = userService2.getUserById(id, name);
        model.addAttribute("user2", user2);
        return "user";
    }
}
