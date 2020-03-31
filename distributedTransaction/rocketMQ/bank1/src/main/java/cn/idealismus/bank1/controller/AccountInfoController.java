package cn.idealismus.bank1.controller;

import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.serice.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;
    
    @RequestMapping("transfer")
    public String transfer (@RequestParam("amount") long amount) {
        String tx_no = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent("1",amount,tx_no);
        //发送消息
        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功";
    }
}
