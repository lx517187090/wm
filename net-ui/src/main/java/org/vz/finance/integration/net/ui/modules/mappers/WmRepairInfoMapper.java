package org.vz.finance.integration.net.ui.modules.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.net.ui.modules.entity.WmRepairInfo;
import org.vz.finance.integration.net.ui.modules.entity.WmRepairInfoExample;

public interface WmRepairInfoMapper {
        int countByExample(WmRepairInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WmRepairInfo record);

    int insertSelective(WmRepairInfo record);

    List<WmRepairInfo> selectByExample(WmRepairInfoExample example);

    WmRepairInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WmRepairInfo record, @Param("example") WmRepairInfoExample example);

    int updateByExample(@Param("record") WmRepairInfo record, @Param("example") WmRepairInfoExample example);

    int updateByPrimaryKeySelective(WmRepairInfo record);

    int updateByPrimaryKey(WmRepairInfo record);
    int updateByPrimaryKey2(WmRepairInfo record);
}