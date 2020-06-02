package org.vz.finance.integration.manage.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vz.finance.integration.manage.sys.dao.SysDeptDao;
import org.vz.finance.integration.manage.sys.service.SysDeptService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.model.SysDept;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

    @Override
    // @DataFilter(subDept = true, user = false)
    public List<SysDept> queryList(Map<String, Object> params) {
        List<SysDept> deptList = this.selectList(new EntityWrapper<SysDept>()
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                .orderBy("CREATE_TIME", false));

        for (SysDept sysDeptEntity : deptList) {
            SysDept parentDeptEntity = this.selectById(sysDeptEntity.getParentId());
            if (parentDeptEntity != null) {
                sysDeptEntity.setParentName(parentDeptEntity.getName());
            }
        }
        return deptList;
    }

    @Override
    public List<String> queryDetpIdList(String parentId) {
        return baseMapper.queryDetpIdList(parentId);
    }

    @Override
    public List<String> getSubDeptIdList(String deptId) {
        // 部门及子部门ID列表
        List<String> deptIdList = new ArrayList<>();

        // 获取子部门ID
        List<String> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        return deptIdList;
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<String> subIdList, List<String> deptIdList) {
        for (String deptId : subIdList) {
            List<String> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
