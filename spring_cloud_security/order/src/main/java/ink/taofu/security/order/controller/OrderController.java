package ink.taofu.security.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping("one")
    @PreAuthorize("hasAnyAuthority('one')")
    public String one () {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name + "one";
    }
}
