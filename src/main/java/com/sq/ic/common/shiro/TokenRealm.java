package com.sq.ic.common.shiro;

import com.sq.ic.common.cache.Caches;
import com.sq.ic.pojo.dto.SysUserDto;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.po.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class TokenRealm extends AuthorizingRealm {

    public TokenRealm(TokenMatcher matcher) {
        super(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();

        SysUserDto user = Caches.getToken(token);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles))
            return info;

        // 添加角色
        for (SysRole role : roles) {
            info.addRole(role.getName());
        }

        List<SysResource> resources = user.getResources();
        if (CollectionUtils.isEmpty(resources))
            return info;

        // 添加权限
        for (SysResource resource : resources) {
            info.addStringPermission(resource.getPermission());
        }

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tk = ((Token) token).getToken();
        return new SimpleAuthenticationInfo(tk, tk, getName());
    }
}
