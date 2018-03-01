package com.lzkj.lxzb.rest.modular.shiro;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Cmsuser;
import com.lzkj.lxzb.rest.modular.shiro.factory.IShiro;
import com.lzkj.lxzb.rest.modular.shiro.factory.ShiroFactroy;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author：jiangzh
 * @Descriptor：
 * @Date： Create in 15:08 2018/1/30
 * @Modified by：
 */
public class ShiroDBReam  extends AuthorizingRealm {

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        IShiro shiroFactory = ShiroFactroy.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Cmsuser cmsuser = shiroFactory.user(token.getUsername());

        if (cmsuser==null) {
        	return null;
        }
        
        // 密码加盐处理
        String salt = cmsuser.getVc2secret();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);

        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(cmsuser.getVc2username(), cmsuser.getVc2password(), credentialsSalt, this.getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 暂时无任何角色与权限信息,所以两个集合都为空
        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }


}
