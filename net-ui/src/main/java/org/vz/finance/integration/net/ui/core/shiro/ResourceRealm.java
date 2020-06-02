package org.vz.finance.integration.net.ui.core.shiro;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vz.finance.integration.manage.sys.dao.SysMenuDao;
import org.vz.finance.integration.manage.sys.dao.SysUserDao;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.model.SysMenu;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.utils.WebUtil;

import java.util.*;

@Component
public class ResourceRealm extends AuthorizingRealm {

	protected final Logger logger = LogManager.getLogger(ResourceRealm.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		String userId = user.getId();

		List<String> permsList;
		// 系统管理员，拥有最高权限
		if (Constant.SUPER_ADMIN.equals(userId)) {
			List<SysMenu> menuList = sysMenuDao.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			for (SysMenu menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserService.queryAllPerms(userId);
		}

		// 用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		// 查询用户信息
		SysUser user = new SysUser();
		user.setUsername(token.getUsername());
		user = sysUserDao.selectOne(user);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		if(user.getStatus() == 0){
		    throw new LockedAccountException("用户无效,用户账号已被禁用");
        }
		SysUser sysUser = new SysUser();

		BeanUtil.copyProperties(user, sysUser);
		WebUtil.saveCurrentUser(sysUser);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPasswd(),
				ByteSource.Util.bytes("SALT"), getName());
		return info;
		
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}

}
