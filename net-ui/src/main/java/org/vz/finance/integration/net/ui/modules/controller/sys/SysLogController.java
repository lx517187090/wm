package org.vz.finance.integration.net.ui.modules.controller.sys;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.ExcelReportHelper;
import org.vz.finance.integration.net.ui.modules.service.IManagerLogService;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogPageDto;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysLogParamDto;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 系统日志
 * 
 * @author jaden
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

	@Autowired
	private IManagerLogService iManagerLogService;

	@RequiresPermissions("sys:log:list")
	@RequestMapping("")
	public ModelAndView index(){
		return new ModelAndView("sysManage/sysLogs");
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public Object list(@RequestBody SysLogParamDto sysLogParamDto){
		PageUtils page = iManagerLogService.queryPage(sysLogParamDto);
		return setSuccessModelMap(page);
	}


	@GetMapping("/exportExcel")
	@ApiOperation("导出操作日志")
	@ManagerLogAp(value = "导出操作日志,参数为 ", type = 5)
	public void exportExcelContract(SysLogParamDto sysLogParamDto, HttpServletResponse response) {
		logger.info("用户{}导出操作日志excel", ShiroUtils.getUserId());
		List<SysLogPageDto> list = iManagerLogService.queryPageList(sysLogParamDto);
		logger.info("用户{}导出操作日志,返回结果:{}", ShiroUtils.getUserId(), JSON.toJSON(list));
		ExcelReportHelper.exportExcel(response, "操作日志", SysLogPageDto.class, list);
	}
}
