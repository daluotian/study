package ink.taofu.dubbo.consumer.controller;

import ink.taofu.dubbo.entity.User;
import ink.taofu.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/user")
    public String getUser(Model model, Integer id, String name){
        User user = userService.getUserById(id, name);
        model.addAttribute("user", user);
        return "user";
    }
    
}
