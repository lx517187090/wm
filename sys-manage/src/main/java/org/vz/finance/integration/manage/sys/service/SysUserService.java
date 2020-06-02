package org.vz.finance.integration.manage.sys.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysUser;

import com.baomidou.mybatisplus.service.IService;



/**
 * 系统用户
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(String userId);

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	
	/**
	 * 保存用户
	 */
	void save(SysUser user);
	
	/**
	 * 修改用户
	 */
	void update(SysUser user);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(String userId, String password, String newPassword);

    /**
     * 重置密码
     * @param idList        用户ID集合
     * @return
     */
    boolean resetBatchIds(List<String> idList);

	/**
	 * 通过用户名查询用户
	 */
	SysUser getUserListByUserName(String userName);


    Map<String, SysUser> getUserMapByUserNames(List<String> userNames);

	List<SysUser> getAllUser();
}
