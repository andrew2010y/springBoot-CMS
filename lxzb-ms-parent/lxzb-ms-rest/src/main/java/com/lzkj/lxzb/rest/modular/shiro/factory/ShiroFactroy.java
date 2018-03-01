package com.lzkj.lxzb.rest.modular.shiro.factory;

import com.lzkj.lxzb.core.util.SpringContextHolder;
import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.CmsuserMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Cmsuser;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.CmsuserExample;
import com.lzkj.lxzb.rest.modular.shiro.ShiroUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(rollbackFor = Exception.class,readOnly = true)
/**
 * @author: jiangzh
 */
public class ShiroFactroy implements IShiro {

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Autowired
    private CmsuserMapper cmsUserMapper;

    @Override
    public Cmsuser user(String account) {
        CmsuserExample example = new CmsuserExample();

        example.createCriteria().andVc2usernameEqualTo(account);

        List<Cmsuser> list = cmsUserMapper.selectByExample(example);

        if (list.size()==1) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public ShiroUser shiroUser(Cmsuser cmsuser) {
        // 套用原有框架，并没有任何实际意义
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(cmsuser.getNumcmsuserid().intValue());
        shiroUser.setAccount(cmsuser.getVc2username());

        return shiroUser;
    }


    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, Cmsuser cmsuser, String realmName) {
        String credentials = cmsuser.getVc2password();
        // 密码加盐处理
        String source = cmsuser.getVc2secret();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
