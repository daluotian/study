package cn.idealismus.bank1.service;

import cn.idealismus.bank1.entity.AccountPay;
import cn.idealismus.bank1.model.AccountChangeEvent;

public interface AccountInfoService {
    
    //更新账户金额
    void updateAccountInfoBalance (AccountChangeEvent accountChangeEvent);
    
    //查询充值结果（远程调用）
    AccountPay queryPayResult (String txNo);
}
