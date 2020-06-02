package org.vz.finance.integration.net.ui.modules.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.vz.finance.integration.net.ui.modules.vo.dto.RateConfigureDto;
import org.vz.finance.integration.net.ui.modules.vo.dto.SysRateConfigureDto;

/**
 * <p>
 * 融资交易表 服务类
 * </p>
 *
 * @author jaden
 * @since 2018-05-02
 */
public interface ISysRateConfigureManagerService {
    
    Page<RateConfigureDto> queryPage(Integer currentPage, Integer pageSize);
    
    /**
     * 删除配置信息
     */
    void deleteBatch(String[] ids);

    SysRateConfigureDto selectById(String id);
    
    void save(SysRateConfigureDto sysRateConfigureDto);
    
    SysRateConfigureDto update(SysRateConfigureDto sysRateConfigureDto);
}
