package cn.idealismus.bank1.feign;

import cn.idealismus.bank1.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用 pay 充值结果
 */
@FeignClient(value = "pay",fallback = PayFallback.class)
public interface PayClient {

    /**
     * 远程调用查询充值结果的路劲
     * @param txNo
     * @return
     */
    @RequestMapping("/pay/payresult/{txNo}")
    AccountPay payresult (@RequestParam("txNo") String txNo);
}
