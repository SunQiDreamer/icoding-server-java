package com.sq.ic.common.shiro;

import com.sq.ic.common.prop.ICProperties;
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
     * ShiroFilterFactoryBean用来告诉Shiro如何进行拦截
     * 1.拦截哪些URL
     * 2.每个URL需要进行哪些filter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(Realm realm, ICProperties properties) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(new DefaultWebSecurityManager(realm));

        Map<String, Filter> filters = new HashMap<>();
        filters.put("token", (Filter) new TokenFilter());

        filterFactoryBean.setFilters(filters);

        Map<String, String> urlMap = new LinkedHashMap<>();

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
