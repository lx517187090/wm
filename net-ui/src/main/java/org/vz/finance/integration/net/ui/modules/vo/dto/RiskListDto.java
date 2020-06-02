package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/05/18
 */
public class RiskListDto extends BaseCustomerDto {

    @ApiModelProperty("审批额度（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double realCreditAmount;

    @ApiModelProperty("申请时间")
    private Date applicationTime;

    //@ApiModelProperty("客户id")
    private String customerId;

    @ApiModelProperty("风险等级")
    private String businessRating;

    @ApiModelProperty("风险等级说明")
    private String ratingDescription;

    @ApiModelProperty("监控月份")
    private String monitorMonth;

    @ApiModelProperty("支行名称")
    private String orgName;

    public Double getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(Double realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBusinessRating() {
        return businessRating;
    }

    public void setBusinessRating(String businessRating) {
        this.businessRating = businessRating;
    }

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }

    public String getMonitorMonth() {
        return monitorMonth;
    }

    public void setMonitorMonth(String monitorMonth) {
        this.monitorMonth = monitorMonth;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
