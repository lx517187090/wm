package org.vz.finance.integration.net.ui.modules.service;

import org.vz.finance.integration.net.ui.core.utils.IBaseService;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;

public interface IInfoService extends IBaseService<WmOrderInfo> {
    void updateDelevery(String id);
}
