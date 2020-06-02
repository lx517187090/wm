package org.vz.finance.integration.manage.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.vz.finance.integration.manage.sys.dao.SysUserDao;
import org.vz.finance.integration.manage.sys.service.SysDeptService;
import org.vz.finance.integration.manage.sys.service.SysUserRoleService;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.manage.sys.util.Query;
import org.vz.finance.integration.model.SysDept;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.model.util.ToolForID;

import java.io.Serializable;
import java.util.*;

/**
 * 系统用户
 *
 * @author jaden
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";
    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;


    @Override
    @Cacheable(value = "users", key = "#userId + 'queryAllMenuId'")
    public List<String> queryAllMenuId(String userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    @Cacheable(value = "users", key = "#userId + 'queryAllPerms'")
    public List<String> queryAllPerms(String userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    // @DataFilter(subDept = true, user = false)
    @Cacheable(value = "users", key = "#params + 'queryPage'")
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        String thirdCode = (String) params.get("thirdCode");
        Wrapper<SysUser> sysUserWrapper = new EntityWrapper<SysUser>()
                .ne("id_", "1")
                .like(StringUtils.isNotBlank(username), "username", username)
                .eq(StringUtils.isNotBlank(thirdCode), "third_code", thirdCode)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                .orderBy("CREATE_TIME", false);


        Page<SysUser> page = this.selectPage(new Query<SysUser>(params).getPage(), sysUserWrapper);

        for (SysUser sysUserEntity : page.getRecords()) {
            /*SysDept sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
            sysUserEntity.setDeptName(sysDeptEntity.getName());*/
            List<String> roles = sysUserRoleService.queryRoleNameList(sysUserEntity.getId());
            sysUserEntity.setRoleIdList(roles);
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "users", allEntries = true)
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        // sha256加密
        // String salt = RandomStringUtils.randomAlphanumeric(20);

        user.setId(ToolForID.getSysUserID());
        user.setPasswd(sha256(user.getPasswd(), "SALT"));
        this.insert(user);

        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "users", allEntries = true)
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPasswd())) {
            user.setPasswd(null);
        } else {
            user.setPasswd(sha256(user.getPasswd(), "SALT"));
        }
        this.updateById(user);

        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public boolean deleteBatchIds(List<? extends Serializable> idList) {
        return super.deleteBatchIds(idList);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public boolean updatePassword(String userId, String password, String newPassword) {
        SysUser userEntity = new SysUser();
        userEntity.setPasswd(newPassword);
        return this.update(userEntity, new EntityWrapper<SysUser>().eq("id_", userId).eq("passwd", password));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public boolean resetBatchIds(List<String> idList) {
        List<SysUser> userList = new ArrayList<>();
        idList.forEach(id -> {
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setPasswd(sha256("888888", "SALT"));
            userList.add(sysUser);
        });
        return super.updateBatchById(userList);
    }

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    @Override
    public SysUser getUserListByUserName(String userName) {
        return baseMapper.queryUserByUserName(userName);
    }

    @Override
    public Map<String, SysUser> getUserMapByUserNames(List<String> userNames) {
        Map<String, SysUser> userMap = new HashMap<>();
        if (CollectionUtils.isEmpty(userNames)) {
            return userMap;
        }
        List<SysUser> sysUsers = baseMapper.queryUserListByUserNames(userNames);
        sysUsers.forEach(sysUser -> userMap.put(sysUser.getUsername(), sysUser));
        return userMap;
    }

    @Override
    public List<SysUser> getAllUser() {
        return baseMapper.getAllUser();
    }

}
