package com.sq.ic.common.shiro;

import com.sq.ic.common.cache.Caches;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.result.CodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class TokenFilter extends AccessControlFilter {
    public static final String HEADER_TOKEN = "Token";

    /**
     * 当请求被TokenFilter拦截时，就会调用这个方法
     * 可以在这个方法中做初步判断
     *
     * 如果返回true：允许访问。可以进入下一个链条调用（比如Filter、拦截器、控制器等）
     * 如果返回false：不允许访问。会进入onAccessDenied方法，不会进入下一个链条调用（比如Filter、拦截器、控制器等）
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o)
            throws Exception {
        return false;
    }

    /**
     * 当isAccessAllowed返回false时，就会调用这个方法
     * 在这个方法中进行token的校验
     *
     * 如果返回true：允许访问。可以进入下一个链条调用（比如Filter、拦截器、控制器等）
     * 如果返回false：不允许访问。不会进入下一个链条调用（比如Filter、拦截器、控制器等）
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(HEADER_TOKEN);

        if (token == null) { // 如果没有Token
            return JsonVos.raise(CodeMsg.NO_TOKEN);
        }
        System.out.println("xxxtoken:" + token);
        if (Caches.getToken(token) == null) { // 如果Token过期了
            System.out.println("yyytoken:" + Caches.getToken(token));
            return JsonVos.raise(CodeMsg.TOKEN_EXPIRED);
        }
        // 鉴权（进入Realm）
        // 这里调用login，并不是“登录”的意思，是为了触发Realm的相应方法去加载用户的角色、权限信息，以便鉴权
        SecurityUtils.getSubject().login(new Token("token"));
        return true;
    }
}
