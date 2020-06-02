package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/05/18
 */
public class NoRiskListDto extends BaseCustomerDto {

    //@ApiModelProperty("客户ID")
    private String customerId;

    @ApiModelProperty("申请时间")
    private Date applicationTime;

    @ApiModelProperty("审批额度（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double realCreditAmount;

    @ApiModelProperty("监控月份")
    private String monitorMonth;

    @ApiModelProperty("支行名称")
    private String orgName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Double getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(Double realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
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
