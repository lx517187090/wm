package org.vz.finance.integration.net.ui.modules.dao;

import org.vz.finance.integration.net.ui.core.utils.BaseMapper;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;

/**
 * 订单录入
 */
public interface WeimaiInfoDao extends BaseMapper<WmOrderInfo> {


    void updateDelevery(String id);
}
