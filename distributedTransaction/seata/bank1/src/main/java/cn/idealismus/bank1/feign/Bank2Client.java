package cn.idealismus.bank1.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bank2", fallback = Bank2ClientFallback.class)
public interface Bank2Client {
    //远程调用服务
    @GetMapping("/bank2/transfer")
    String transfer (@RequestParam("amount") Double amount);
}
