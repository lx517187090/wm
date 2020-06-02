package org.vz.finance.integration.manage.sys.service;

import java.util.List;
import java.util.Map;

import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysConfig;

import com.baomidou.mybatisplus.service.IService;


/**
 * 系统配置信息
 * 
 * @author jaden
 * @date 2016年12月4日 下午6:49:01
 */
public interface SysConfigService extends IService<SysConfig>  {
	List<SysConfig> queryListForFlowSetting();

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 保存配置信息
	 */
	public void save(SysConfig config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfig config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 */
	public String getValue(String key);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
	public List<SysConfig> selectByKey(String key);
	
	/**
	 * 通过参数key查询参数数据
	 * @param key 参数key
	 * @return
	 */
	public SysConfig selectOneByKey(String key);
}
