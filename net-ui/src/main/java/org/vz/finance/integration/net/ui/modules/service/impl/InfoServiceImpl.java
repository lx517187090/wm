package org.vz.finance.integration.net.ui.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vz.finance.integration.net.ui.core.utils.BaseService;
import org.vz.finance.integration.net.ui.modules.dao.WeimaiInfoDao;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;
import org.vz.finance.integration.net.ui.modules.service.IInfoService;
@Service
public class InfoServiceImpl extends BaseService<WeimaiInfoDao, WmOrderInfo> implements IInfoService {

    @Autowired
    private  WeimaiInfoDao weimaiInfoDao;

    @Override
    public void updateDelevery(String id) {
        weimaiInfoDao.updateDelevery(id);
    }
}
