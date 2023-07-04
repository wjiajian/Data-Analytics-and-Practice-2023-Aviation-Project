package com.qrsoft.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qrsoft.entity.SysAuth;
import com.qrsoft.entity.SysUser;
import com.qrsoft.mapper.SysAuthMapper;
import com.qrsoft.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysAuthMapper authMapper;

    /**
     * 身份认证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {//③
        if (authentication.getPrincipal() == null || authentication.getCredentials() == null)
            throw new BadCredentialsException("用户名或密码错误");
        // 获取认证的用户名 & 密码
        String account = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        SysUser sysUser = userMapper.getByAccount(account);
        if (sysUser != null) {
            // 密码不匹配直接抛出异常
            if (!passwordEncoder.matches(password, sysUser.getPassword()))
                throw new BadCredentialsException("用户名或密码错误");
            // 获取用户权限
            List<GrantedAuthority> authorities = getGrantedAuthorities(sysUser);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(sysUser.getAccount(), sysUser.getPassword(), authorities);
            token.setDetails(sysUser);
            return token;
        } else {
            throw new UsernameNotFoundException("用户不存在或已删除");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {//②
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * 获取用户权限
     */
    private List<GrantedAuthority> getGrantedAuthorities(SysUser sysUser) {
        List<SysAuth> authList = authMapper.getAuthByUserId(sysUser.getId());
        //先过滤，在转换
        Set<String> perms = authList.stream().map(SysAuth::getAuthCode).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        return AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));
    }
}