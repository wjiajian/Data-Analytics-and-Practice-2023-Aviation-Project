package com.qrsoft.filter;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.qrsoft.common.TokenVO;
import com.qrsoft.common.WrappedResult;
import com.qrsoft.entity.SysUser;
import com.qrsoft.util.TokenUtil;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final StringRedisTemplate redisTemplate;

    public TokenLoginFilter(String url, AuthenticationManager authManager, StringRedisTemplate redisTemplate) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 尝试认证（从request中取用户名和密码，生成认证实体）
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException { //①
        String account = request.getParameter("account");
        String password = request.getParameter("password");

//		System.out.println("登录进来了");
        if (StringUtils.isBlank(account) && StringUtils.isBlank(password)) {
            InputStreamReader streamReader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder builder = new StringBuilder();
            String inputStr;
            while ((inputStr = reader.readLine()) != null)
                builder.append(inputStr);
            JSONObject jsonObject = JSONObject.parseObject(builder.toString());
            account = jsonObject.getString("account");
            password = jsonObject.getString("password");
            streamReader.close();
            reader.close();
        }
        if (account.contains(" ")) throw new BadCredentialsException("用户名或密码错误");
        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(account, password));
    }

    /**
     * 认证成功返回统一格式的JSON
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException {//④
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        String token;
        //生成token
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("account", auth.getName());
            token = TokenUtil.genToken(payload);
        } catch (Exception e) {
            response.getWriter().print(new Gson().toJson("Token生成失败"));
            return;
        }
        if (auth.getDetails() instanceof SysUser) {
            SysUser sysUser = (SysUser) auth.getDetails();
            TokenVO tokenVO = new TokenVO();
            tokenVO.setAccount(auth.getName());
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            List<String> authorityList = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            tokenVO.setName(sysUser.getName());
            tokenVO.setId(sysUser.getId().toString());
            tokenVO.setType(sysUser.getType());
            Boolean userboo = redisTemplate.hasKey(sysUser.getId().toString());
            if (userboo != null && userboo) {
                String tok = redisTemplate.boundValueOps(sysUser.getId().toString()).get();
                if (tok != null) {
                    Boolean tokboo = redisTemplate.hasKey(tok);
                    if (tokboo != null && tokboo) {
                        redisTemplate.delete(tok);
                    }
                }
                redisTemplate.delete(sysUser.getId().toString());
            }
            //存储用户对应token
            redisTemplate.boundValueOps(sysUser.getId().toString()).set(token);
            //存储token对应权限
            BoundSetOperations<String, String> setOperations = redisTemplate.boundSetOps(token);
            setOperations.add(authorityList.toArray(new String[]{}));
            response.setHeader("Authorization", token);
            response.setHeader("userAuth",authorityList.toString());
            tokenVO.setAuthorization(token);
            tokenVO.setUserAuth(authorityList.toString());
            response.getWriter().print(new Gson().toJson(WrappedResult.successWrapedResult(tokenVO)));
        } else {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(WrappedResult.failedWrappedResult("登录失败")));
        }
    }
    /**
     * 认证失败返回统一格式的JSON
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {//④
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(new Gson().toJson(WrappedResult.failedWrappedResult(failed.getMessage())));
    }
}
