package com.qrsoft.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.qrsoft.common.R;
import com.qrsoft.common.WrappedResult;
import com.qrsoft.entity.SysUser;
import com.qrsoft.mapper.SysUserMapper;
import com.qrsoft.util.TokenUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 身份认证过滤器
 */
public class TokenAuthenticationFilter extends GenericFilterBean {
    private final SysUserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    public TokenAuthenticationFilter(SysUserMapper sysUserDao, StringRedisTemplate redisTemplate) {
        this.userMapper = sysUserDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("权限信息打印");
        // 从Http头中取token
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (token != null) {
            // 校验token，并获取token中存储的用户名
            R r = TokenUtil.valid(token);
            // 验证redis是否存在token
            Boolean tokenBoo = redisTemplate.hasKey(token);
            if (tokenBoo != null && tokenBoo && r.isSuccess()) {
                // 从token检验结果获取用户名
                String account = MapUtils.getString(r.getPayloadMap(), "account", StringUtils.EMPTY);
                // 根据token获取redis中权限set
                Set<String> authoritySet = redisTemplate.boundSetOps(token).members();
                SysUser sysUser = userMapper.getByAccount(account);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sysUser.getAccount(), sysUser.getPassword(), AuthorityUtils.createAuthorityList(authoritySet.toArray(new String[]{})));
                authentication.setDetails(sysUser);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                //token验证不通过
                writeResponse((HttpServletResponse) response, "Token失效");
            }
        } else {
            // token不存在放回401（未登录）
            writeResponse((HttpServletResponse) response, "未登录");
        }
    }

    private void writeResponse(HttpServletResponse response, String str) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(new Gson().toJson(WrappedResult.failedWrappedResult(str, String.valueOf(HttpServletResponse.SC_UNAUTHORIZED))));
    }
}
