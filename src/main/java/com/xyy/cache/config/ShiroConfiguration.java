package com.xyy.cache.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro核心配置类
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 自定义shirofilter，校验规则
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

       /* //登录url
        bean.setLoginUrl("/login");
        //登录成功url
        bean.setSuccessUrl("/index");
        //未成功url
        bean.setUnauthorizedUrl("/unauthorized");
        //权限配置
        //请求拦截规则，key为请求，value为拦截器
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        //只允许admin请求访问，角色带admin的才能访问admin路径
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        //具有edit角色的权限的才能访问
        filterChainDefinitionMap.put("/edit", "perms[edit]");
        //数据库请求都不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        //只要登陆过，就不再登录
        filterChainDefinitionMap.put("/**", "user");
        filterChainDefinitionMap.put("/pers", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);*/
        return bean;
    }

    /**
     * 安全管理器
     * @param authRealm，Qualifier从上下文取bean
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     * 自定义realm
     * @param
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm() {
        AuthRealm authRealm = new AuthRealm();
        //缓存
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        return authRealm;
    }

    /**
     *shiro与spring的配置类，这样spring使用的就是我们自己写的安全管理器
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 使用代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
