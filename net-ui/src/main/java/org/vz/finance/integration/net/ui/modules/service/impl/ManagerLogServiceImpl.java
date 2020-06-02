package org.vz.finance.integration.net.ui.modules.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.vz.finance.integration.manage.sys.service.SysLogService;
import org.vz.finance.integration.manage.sys.service.SysUserRoleService;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysLog;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.modules.service.IManagerLogService;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogPageDto;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogParamDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ManagerLogServiceImpl implements IManagerLogService {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(SysLogParamDto sysLogParamDto) {
        Map<String, Object> params = BeanUtil.beanToMap(sysLogParamDto);
        PageUtils page = sysLogService.queryPage(params);
        page.setList(buildParam(page.getList()));
        return page;
    }

    @Override
    public List<SysLogPageDto> queryPageList(SysLogParamDto sysLogParamDto) {
        Map<String, Object> params = BeanUtil.beanToMap(sysLogParamDto);
        return buildParam(sysLogService.queryList(params));
    }

    private List<SysLogPageDto> buildParam(List<?> list) {
        List<SysLogPageDto> pageDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return pageDtoList;
        }
        for (Object o : list) {
            if (!(o instanceof SysLog)) {
                continue;
            }
            SysLog sysLog = (SysLog) o;
            SysLogPageDto sysLogPageDto = new SysLogPageDto();
            BeanUtil.copyProperties(sysLog, sysLogPageDto);

            SysUser userByUserName = sysUserService.getUserListByUserName(sysLog.getUsername());
            if (userByUserName != null) {
                sysLogPageDto.setUsername(StringUtils.isNotBlank(userByUserName.getLoginName()) ?
                        userByUserName.getLoginName() : userByUserName.getUsername());
            }
            pageDtoList.add(sysLogPageDto);
        }
        return pageDtoList;
    }

}
