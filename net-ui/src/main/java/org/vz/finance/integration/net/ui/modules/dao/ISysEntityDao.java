package org.vz.finance.integration.net.ui.modules.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author jaden
 * @since 2018-05-02
 */
public interface ISysEntityDao {

    Map<String, String> findByFiled(@Param("tableName") String tableName, @Param("exa") String exa);

}