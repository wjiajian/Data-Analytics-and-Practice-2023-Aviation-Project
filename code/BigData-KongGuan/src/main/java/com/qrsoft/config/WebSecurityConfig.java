package com.qrsoft.config;

import com.google.gson.Gson;
import com.qrsoft.common.WrappedResult;
import com.qrsoft.filter.TokenAuthenticationFilter;
import com.qrsoft.filter.TokenAuthenticationProvider;
import com.qrsoft.filter.TokenLoginFilter;
import com.qrsoft.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 使用自定义身份验证组件
        auth.authenticationProvider(tokenAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置 CSRF 关闭,允许跨域访问
        http.csrf().disable();
        // 开启Spring Security cors支持,允许跨域访问
        http.cors();
        // 关闭 Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 允许 登录接口 的无授权访问，其他需要授权访问
        http.authorizeRequests().antMatchers("/*").permitAll().anyRequest().authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // 添加拦截器
        http.addFilterBefore(
                new TokenLoginFilter("/api/login", authenticationManager(), redisTemplate),
                UsernamePasswordAuthenticationFilter.class).addFilterBefore(
                        new TokenAuthenticationFilter(userMapper, redisTemplate), UsernamePasswordAuthenticationFilter.class);
        // 指定错误未授权访问的处理类
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().print(new Gson().toJson(WrappedResult.failedWrappedResult(accessDeniedException.getMessage(), "403")));
        });
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/doc.html/**", "/swagger-ui.html/**", "/v2/**", "/swagger-resources/**", "/webjars/**", "/minio/**");
    }
}
