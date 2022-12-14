package com.sq.ic.common.shiro;

import com.sq.ic.common.prop.ICProperties;
import com.sq.ic.filter.ErrorFilter;
import io.swagger.models.auth.UrlMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroCfg {
    @Bean
    public Realm realm() {
        return new TokenRealm(new TokenMatcher());
    }

    /**
     * ShiroFilterFactory Bean用来告诉Shiro如何进行拦截
     * 1.拦截哪些URL
     * 2.每个URL需要进行哪些filter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(Realm realm, ICProperties properties) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(new DefaultWebSecurityManager(realm));

        // 添加一些自定义Filter
        Map<String, Filter> filters = new HashMap<>();
        filters.put("token", (Filter) new TokenFilter());
        filterFactoryBean.setFilters(filters);
        // 设置URL如何拦截
        Map<String, String> urlMap = new LinkedHashMap<>();
        // 验证码 anon 匿名访问，不需要登录
        urlMap.put("/sysUsers/captcha", "anon");
        // 用户登录
        urlMap.put("/sysUsers/login", "anon");
        // swagger
        urlMap.put("/swagger*/**", "anon");
        urlMap.put("/v2/api-docs/**", "anon");
        // 全局Filter的异常处理
        urlMap.put(ErrorFilter.ERROR_URI, "anon");
        // 其他
        urlMap.put("/**", "token");
        filterFactoryBean.setFilterChainDefinitionMap(urlMap);

        return filterFactoryBean;
    }

    /**
     * 解决：@RequiresPermissions导致控制器接口404
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setUsePrefix(true);
        return proxyCreator;
    }

}
