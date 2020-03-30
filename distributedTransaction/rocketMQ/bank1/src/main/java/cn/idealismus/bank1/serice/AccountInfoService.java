package cn.idealismus.bank1.serice;

import cn.idealismus.bank1.model.AccountChangeEvent;

public interface AccountInfoService {
    //发送消息
    void sendUpdateAccountBalance (AccountChangeEvent accountChangeEvent);
    //扣减金额，更新账户
    void doUpdateAccountBalance (AccountChangeEvent accountChangeEvent);
}
