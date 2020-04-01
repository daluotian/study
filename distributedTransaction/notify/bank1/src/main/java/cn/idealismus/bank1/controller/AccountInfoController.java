package cn.idealismus.bank1.controller;

import cn.idealismus.bank1.entity.AccountPay;
import cn.idealismus.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;
    
    //主动查询充值结果
    @RequestMapping("/payresult")
    public AccountPay result (@RequestParam("txNo") String txNo) {
        return accountInfoService.queryPayResult(txNo);
    }
}
