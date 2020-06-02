package org.vz.finance.integration.manage.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.model.SysUser;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 系统用户
 *
 * @author jaden
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);


    SysUser queryUserByUserName(@Param("userName") String userName);


    List<SysUser> queryUserListByUserNames(@Param("userNames") List<String> userNames);

    List<SysUser> getAllUser();

}
