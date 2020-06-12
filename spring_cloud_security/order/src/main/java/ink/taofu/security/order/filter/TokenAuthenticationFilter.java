package ink.taofu.security.order.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ink.taofu.security.order.util.EncryptUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String tokenStr = httpServletRequest.getHeader("json-token");
        if (tokenStr != null) {
            //解密
            String json = EncryptUtil.decodeUTF8StringBase64(tokenStr);
            //转回jsonObject
            JSONObject jsonObject = JSONObject.parseObject(json);
            //获取用户信息
            String principal = jsonObject.getString("principal");
            JSONArray authoritiesArrays = jsonObject.getJSONArray("authorities");
            String[] authorities = authoritiesArrays.toArray(new String[authoritiesArrays.size()]);
            
            //将身份信息和权限填充到用户身份token中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            //将 authenticationToken 填充到 security 上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
