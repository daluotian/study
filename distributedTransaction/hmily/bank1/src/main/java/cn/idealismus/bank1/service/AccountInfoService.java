package cn.idealismus.bank1.service;

/**
 * Created by Administrator.
 */
public interface AccountInfoService {

    //账户扣款
    public  void updateAccountBalance(String accountNo, Double amount);
}
