package cn.idealismus.pay.controller;

import cn.idealismus.pay.entity.AccountPay;
import cn.idealismus.pay.service.AccountPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountPayController {
    @Autowired
    private AccountPayService accountPayService;
    
    
    //充值
    @RequestMapping("/paydo")
    public AccountPay pay (AccountPay accountPay) {
        accountPay.setId(UUID.randomUUID().toString());
        return accountPayService.insertAccountPay(accountPay);
    }
    
    //查询充值结果
    @RequestMapping("/payresult/{txNo}")
    public AccountPay payresult (@RequestParam("txNo") String txNo) {
        return accountPayService.getAccountPay(txNo);
    }
}
