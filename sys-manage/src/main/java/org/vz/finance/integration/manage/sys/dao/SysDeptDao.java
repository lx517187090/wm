package org.vz.finance.integration.manage.sys.dao;

import java.util.List;

import org.vz.finance.integration.model.SysDept;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 部门管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
public interface SysDeptDao extends BaseMapper<SysDept> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<String> queryDetpIdList(String parentId);

}
