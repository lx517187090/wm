package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

import java.io.Serializable;

/**
 * <p>
 * 白名单页面条件Dto
 * </p>
 *
 * @author zhanlingxia
 * @since 2018-07-19
 */

@ApiModel("白名单页面条件Dto")
public class WhiteListParamDto implements Serializable {

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("证件号码")
    private String globalId;

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

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
        return "WhiteListParamDto [companyName=" + companyName + ", globalId=" + globalId + ", currentPage="
                + currentPage + ", pageSize=" + pageSize + "]";
    }

}
