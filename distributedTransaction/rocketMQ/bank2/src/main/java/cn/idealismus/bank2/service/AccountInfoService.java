package cn.idealismus.bank2.service;

import cn.idealismus.bank2.model.AccountChangeEvent;

public interface AccountInfoService {
    //更新账户，增加金额
    void addAccountInfoBalance (AccountChangeEvent accountChangeEvent);
}
