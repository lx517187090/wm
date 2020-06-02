package org.vz.finance.integration.manage.sys.dao;


import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.model.SysConfig;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 系统配置信息
 * 
 * @author jaden
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseMapper<SysConfig> {

	/**
	 * 根据key，查询value
	 */
	SysConfig queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
