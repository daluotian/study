package cn.idealismus.pay.controller;

import cn.idealismus.pay.entity.AccountPay;
import cn.idealismus.pay.service.AccountPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class AccountPayController {
    @Autowired
    private AccountPayService accountPayService;
    
    
    //充值
    @RequestMapping("/paydo")
    public AccountPay pay (AccountPay accountPay) {
        log.info("接收到充值请求...");
        accountPay.setId(UUID.randomUUID().toString());
        return accountPayService.insertAccountPay(accountPay);
    }
    
    //查询充值结果
    @RequestMapping("/payresult/{txNo}")
    public AccountPay payresult (@RequestParam("txNo") String txNo) {
        log.info("接收到查询充值结果的请求...");
        return accountPayService.getAccountPay(txNo);
    }
}
