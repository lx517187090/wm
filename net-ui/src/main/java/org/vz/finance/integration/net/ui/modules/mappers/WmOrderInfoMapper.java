package org.vz.finance.integration.net.ui.modules.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfoExample;
import org.vz.finance.integration.net.ui.modules.vo.WmOrderInfoVO;

public interface WmOrderInfoMapper {
    int countByExample(WmOrderInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WmOrderInfo record);

    int insertSelective(WmOrderInfo record);

    List<WmOrderInfo> selectByExample(WmOrderInfoExample example);

    WmOrderInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WmOrderInfo record, @Param("example") WmOrderInfoExample example);

    int updateByExample(@Param("record") WmOrderInfo record, @Param("example") WmOrderInfoExample example);

    int updateByPrimaryKeySelective(WmOrderInfo record);

    int updateByPrimaryKey(WmOrderInfo record);

    int updateByPrimaryKey2(WmOrderInfo record);

    List<WmOrderInfoVO> selectDeliver(WmOrderInfoVO record);

    List<WmOrderInfoVO> selectRepair(WmOrderInfoVO record);

    void updateDelevery(@Param("id")String id);
}