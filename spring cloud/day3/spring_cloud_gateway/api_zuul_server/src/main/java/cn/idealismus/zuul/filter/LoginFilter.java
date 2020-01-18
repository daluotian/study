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
     * pre
     * routing
     * post
     * error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序
     * 返回值越小，执行顺序越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 当前过滤器是否生效
     * true : 使用该过滤器
     * false : 不适用该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 指定过滤器中的业务逻辑
     *  身份认证:
     *      1.所有的请求需要携带一个参数 : access-token
     *      2.获取request请求
     *      3.通过request获取参数access-token
     *      4.判断token是否为空
     *      4.1 token==null : 身份验证失败
     *      4.2 token!=null : 执行后续操作
     *  在zuul网关中,通过RequestContext的上下问对象,可以获取对象request对象
     * 过滤器业务
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("access-token");
        if (null == token) {
            //拦截请求，返回失败
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
