package org.lmmarise.rbac.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lmmarise.rbac.security.service.SysSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:29 下午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler myAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**")
                .formLogin()
                .usernameParameter("uname").passwordParameter("pwd")
                .loginPage("/admin/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                //  .antMatchers("/admin/**").permitAll()//.hasRole("ADMIN")
                .antMatchers("/admin/rbac")
                .access("@rbacService.hasPermission(request, authentication)")
                .antMatchers("/redis/**").permitAll();
        http.logout().logoutUrl("/admin/logout").permitAll();
        http.rememberMe().rememberMeParameter("rememberMe");    // 记住我功能
        http.csrf().ignoringAntMatchers("/admin/upload");
        http.headers().frameOptions().sameOrigin();     // 解决X-Frame-Options deny 造成的页面空白,不然后台不能用frame
    }

    @Bean
    UserDetailsService service() {
        return new SysSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置当前过滤器链的用户 Provider
        auth.userDetailsService(service());     // 默认从IoC容器获取PasswordEncoder，无需刻意指定（默认的加密器支持多种加密）
    }
}
