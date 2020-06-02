package org.vz.finance.integration.net.ui.modules.vo.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/05/07
 */
@ApiModel("后台管理客户列表vo")
public class CustomerManagerPageDto extends BaseCustomerDto {

    @ApiModelProperty("客户ID")
    private String customerId;

    @ApiModelProperty("信用等级")
    private String creditRating;

    @ApiModelProperty("证件类型")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterCdCodeType")
    private String cdCodeType;

    @ApiModelProperty("预授信金额（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double preCreditAmount;

    @ApiModelProperty("实际预授信（万元）")
    private Double realCreditAmount;

    @ApiModelProperty("登记日期")
    @CellFormat(datePatten = DatePattern.NORM_DATETIME_PATTERN)
    private Date registerTime;

    @ApiModelProperty("利率")
    private Double interestRate;

    @ApiModelProperty("订单号")
    private String preCreditId;

    @ApiModelProperty(value = "申请渠道")
    private String applyChanel;

    @ApiModelProperty(value = "审批状态", notes = "00000000:审批通过 88888888:审批中 99999999：审批拒绝")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterAccessResult")
    private String approveStatus;

    @ApiModelProperty("模型过程")
    private String creditModelProcess;

    @ApiModelProperty("企业评分")
    private String businessScore;

    @ApiModelProperty("准入触发规则")
    private String accessDetail;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getCdCodeType() {
        return cdCodeType;
    }

    public void setCdCodeType(String cdCodeType) {
        this.cdCodeType = cdCodeType;
    }

    public Double getPreCreditAmount() {
        return preCreditAmount;
    }

    public void setPreCreditAmount(Double preCreditAmount) {
        this.preCreditAmount = preCreditAmount;
    }

    public Double getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(Double realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPreCreditId() {
        return preCreditId;
    }

    public void setPreCreditId(String preCreditId) {
        this.preCreditId = preCreditId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getCreditModelProcess() {
        return creditModelProcess;
    }

    public void setCreditModelProcess(String creditModelProcess) {
        this.creditModelProcess = creditModelProcess;
    }

    public String getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(String businessScore) {
        this.businessScore = businessScore;
    }

    public String getAccessDetail() {
        return accessDetail;
    }

    public void setAccessDetail(String accessDetail) {
        this.accessDetail = accessDetail;
    }

    public String getApplyChanel() {
        return applyChanel;
    }

    public void setApplyChanel(String applyChanel) {
        this.applyChanel = applyChanel;
    }

    @ApiModelProperty("是否准入通过")
    public Boolean getIsAccess() {
        return preCreditAmount != null && preCreditAmount > 0;
    }
}
