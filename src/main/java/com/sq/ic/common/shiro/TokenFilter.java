package com.sq.ic.common.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
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
        
        return false;
    }
}
