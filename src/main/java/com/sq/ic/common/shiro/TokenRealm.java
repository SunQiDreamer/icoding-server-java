package com.sq.ic.common.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tk = ((Token) token).getToken();
        return new SimpleAuthenticationInfo(tk, tk, getName());
    }
}
