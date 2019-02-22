package com.xumou.ssm.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Configuration
public class ShiroConfiguration {

    // 开启代理模式. 不然不能使用注解管理权限
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    // 凭证管理器, 用于散列前端传递过来的密码
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm myRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    // 安全管理器
    @Bean
    public SecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    // 详细配置
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);// 安全管理器
        shiroFilterFactoryBean.setLoginUrl("/login");//登录
        shiroFilterFactoryBean.setSuccessUrl("/html/index.html");//首页
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/error.html");//错误
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/logout","logout");//登出
        map.put("/html/login.html","anon");//静态资源放行
        map.put("/html/error.html","anon");//静态资源放行
        map.put("/**","authc");//对所有认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);// 过滤器
        return shiroFilterFactoryBean;
    }

    // 权限注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    // 自定义Realm
    private static class MyRealm extends AuthorizingRealm {

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermission("test");
            return info;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            Object principal = authenticationToken.getPrincipal();
            Md5Hash md5Hash = new Md5Hash("123", null, 2);
            return new SimpleAuthenticationInfo(principal,md5Hash,null, "");
        }
    }

}