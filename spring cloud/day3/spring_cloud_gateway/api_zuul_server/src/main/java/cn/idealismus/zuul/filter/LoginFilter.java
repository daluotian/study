package cn.idealismus.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 定义过滤器类型
     *  pre 在调用之前访问 做访问验证
     *  routing 开启调用和调用结束 做负载均衡和服务降级
     *  post 在请求返回之前 在返回之前添加信息
     *  error 发生错误
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序
     *  返回值越小 执行顺序越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 当前过滤器是是够生效
     * true 使用
     * false 不适用
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中的业务逻辑
     * 在zuul网关中，通过RequestContext的上下文，可以获取Request对象
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //1 获得zuul的RequestContext对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //2 从 RequestContext 中获取Request
        HttpServletRequest request = currentContext.getRequest();
        //3 从 Request 中获取参数
        String token = request.getParameter("access-token");
        if (token == null) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
