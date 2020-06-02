package org.vz.finance.integration.manage.sys.service;

import java.util.List;
import java.util.Map;

import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysLog;

import com.baomidou.mybatisplus.service.IService;


/**
 * 系统日志
 * 
 * @author jaden
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLog> {
	
	PageUtils queryPage(Map<String, Object> params);

	List<SysLog> queryList(Map<String, Object> params);

}
