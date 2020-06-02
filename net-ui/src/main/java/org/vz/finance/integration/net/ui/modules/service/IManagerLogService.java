package org.vz.finance.integration.net.ui.modules.service;

import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogPageDto;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogParamDto;

import java.util.List;

public interface IManagerLogService {

    PageUtils queryPage(SysLogParamDto sysLogParamDto);

    List<SysLogPageDto> queryPageList(SysLogParamDto sysLogParamDto);
}