package org.vz.finance.integration.net.ui.modules.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.net.ui.modules.entity.WmConfig;
import org.vz.finance.integration.net.ui.modules.entity.WmConfigExample;

public interface WmConfigMapper {
    int countByExample(WmConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(WmConfig record);

    int insertSelective(WmConfig record);

    List<WmConfig> selectByExample(WmConfigExample example);

    WmConfig selectByPrimaryKey(String id);

    WmConfig selectByConfName(@Param("confName")String confName);

    int updateByExampleSelective(@Param("record") WmConfig record, @Param("example") WmConfigExample example);

    int updateByExample(@Param("record") WmConfig record, @Param("example") WmConfigExample example);

    int updateByPrimaryKeySelective(WmConfig record);

    int updateByPrimaryKey(WmConfig record);
}