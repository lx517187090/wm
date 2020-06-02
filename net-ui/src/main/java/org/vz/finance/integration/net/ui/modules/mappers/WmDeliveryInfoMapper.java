package org.vz.finance.integration.net.ui.modules.mappers;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.vz.finance.integration.net.ui.modules.entity.WmDeliveryInfo;
import org.vz.finance.integration.net.ui.modules.entity.WmDeliveryInfoExample;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;
import org.vz.finance.integration.net.ui.modules.vo.WmOrderInfoVO;

public interface WmDeliveryInfoMapper {
    int countByExample(WmDeliveryInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WmDeliveryInfo record);

    int insertSelective(WmDeliveryInfo record);

    List<WmDeliveryInfo> selectByExample(WmDeliveryInfoExample example);

    WmDeliveryInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WmDeliveryInfo record, @Param("example") WmDeliveryInfoExample example);

    int updateByExample(@Param("record") WmDeliveryInfo record, @Param("example") WmDeliveryInfoExample example);

    int updateByPrimaryKeySelective(WmDeliveryInfo record);

    int updateByPrimaryKey(WmDeliveryInfo record);

    int updateByPrimaryKey2(WmDeliveryInfo record);

    List<WmOrderInfoVO> selectInfo(Pagination page, WmOrderInfoVO info);

    List<WmOrderInfoVO> selectDelivery(Pagination page, WmOrderInfoVO info);

    List<WmOrderInfoVO> selectRepair(Pagination page, WmOrderInfoVO info);
}