package com.xumou.ssh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // 查询用户具有的角色
                Collection<GrantedAuthority> auths = new ArrayList<>();
                auths.add(new SimpleGrantedAuthority("业务员"));
                return new User(username, "123", auths);
            }
        }).passwordEncoder(new PasswordEncoder() {
            public String encode(CharSequence rawPassword) {
                return null;
            }
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 定义忽略拦截的路径
        web.ignoring().antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(new MyFilterInvocationSecurityMetadataSource());
                        object.setAccessDecisionManager(new MyAccessDecisionManager());
                        return object;
                    }
                })
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
    }

    public static class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
        @Override
        public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
            // 查询资源所需的角色
            return SecurityConfig.createList("业务员", "社保办理专员");
        }

        @Override
        public Collection<ConfigAttribute> getAllConfigAttributes() {
            return null;
        }

        @Override
        public boolean supports(Class<?> clazz) {
            return false;
        }
    }

    public static class MyAccessDecisionManager implements AccessDecisionManager{
        @Override
        public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
            // 处理角色与权限关系
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                if(authentication.getAuthorities().contains(grantedAuthority))
                    return;
            }
            throw new AccessDeniedException("权限不足!");
        }

        @Override
        public boolean supports(ConfigAttribute attribute) {
            return true;
        }

        @Override
        public boolean supports(Class<?> clazz) {
            return true;
        }
    }

    // 权限校验发生异常时调用
    public static class MyAccessDeniedHandler implements AccessDeniedHandler{
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.getWriter().write(accessDeniedException.getMessage());
        }
    }

}