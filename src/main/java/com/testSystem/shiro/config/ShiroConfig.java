package com.testSystem.shiro.config;

import com.testSystem.shiro.filter.ShiroPermissionsFilter;
import com.testSystem.shiro.realm.CustomAuthRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    shiro 配置类
 */
@Configuration
public class ShiroConfig
{

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
            shiro内置过滤器
            常用过滤器：anon:无须认证
                        authc:必须认证才可以通过
                        user:必须使用rememberMe才能访问
                        perms:必须有权限才能访问
                        role:必须得到权限集合才能访问
    */
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/submitLogin", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/layer/**", "anon");
        filterChainDefinitionMap.put("/zTree/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/logout","logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters=new LinkedHashMap<>();
        filters.put("authc", new ShiroPermissionsFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }


    @Bean(name="defaultWebSecurityManager")
    public  DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customAuthRealm") CustomAuthRealm customAuthRealm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customAuthRealm);
        return defaultWebSecurityManager;
    }
    @Bean(name="customAuthRealm")
    public CustomAuthRealm getRealm()
    {
        return new CustomAuthRealm();
    }

    @Bean(name="shiroPermissionsFilter")
    public ShiroPermissionsFilter getShiroPermissionsFilter(){
        return new ShiroPermissionsFilter();
    }
}
