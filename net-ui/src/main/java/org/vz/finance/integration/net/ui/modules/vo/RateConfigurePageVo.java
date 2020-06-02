package org.vz.finance.integration.net.ui.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

/**
 * <p>
 * 利率查询条件
 * </p>
 *
 * @author liuzy803
 * @since 2018-06-08
 */
@ApiModel("利率配置表Vo")
public class RateConfigurePageVo {

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

    

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "tradingPageVo [ currentPage=" + currentPage + ", pageSize="
                + pageSize + "]";
    }

}
