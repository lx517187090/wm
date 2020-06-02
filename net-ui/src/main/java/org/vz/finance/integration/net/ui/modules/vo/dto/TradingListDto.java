package org.vz.finance.integration.net.ui.modules.vo.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单列表
 * </p>
 *
 * @author Jasmine
 * @since 2018-05-08
 */
@ApiModel("交易列表vo")
public class TradingListDto implements Serializable {

    private String id;

    @ApiModelProperty("订单编号")
    private String transactionCode;

    @ApiModelProperty("合同编号")
    private String contractCode;

    @ApiModelProperty("办理网点")
    private String procBank;

    @ApiModelProperty("公司名称")
    private String enterpriseName;

    @ApiModelProperty("纳税识别号")
    private String tdCode;

    @ApiModelProperty("申请人名称")
    private String legalPerson;

    @ApiModelProperty("身份证号码")
    private String cdCode;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty(value = "审批状态", notes = "001:待审批   002:审批通过  003:审批拒绝  004:订单关闭")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterApproveStatus")
    private String approvalStatus;

    @ApiModelProperty(value = "审批流程状态", notes = "审批状态：1准入节点 2额度审批节点 3个人征信和反欺诈节点 4结束节点")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterApprovalResult")
    private String approvalResult;
    
    @ApiModelProperty(value = "合同状态", notes = "1:未生效，2:生效 ，3:注销，4:注销 5:已完成")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterContractStatus")
    private String contractStatus;
    
    @ApiModelProperty("合同生效日期")
    private String contractStartTime;
    
    @ApiModelProperty("合同到期日期")
    private String contractEndTime;
    
    @ApiModelProperty("申请金额")
    private Double realCreditAmount;

    @ApiModelProperty("浮动百分比")
    private String interestRate;
    
    @ApiModelProperty("申请时间")
    @CellFormat(datePatten = DatePattern.NORM_DATETIME_PATTERN)
    private Date applicationTime;

    @ApiModelProperty("模型过程")
    private String personalCreditRecord;
    
    @ApiModelProperty("合同金额")
    private Double contractAmount;
    
    @ApiModelProperty("营销人员编号")
    private String ygbh;
    
    @ApiModelProperty(value = "申请渠道", notes = "bank:银行   vzoom:微众")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterApplyChanel")
    private String applyChannel;
    
    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getYgbh() {
        return ygbh;
    }

    public void setYgbh(String ygbh) {
        this.ygbh = ygbh;
    }

    public String getApplyChannel() {
        return applyChannel;
    }

    public void setApplyChannel(String applyChannel) {
        this.applyChannel = applyChannel;
    }

    public String getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(String contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public String getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getProcBank() {
        return procBank;
    }

    public void setProcBank(String procBank) {
        this.procBank = procBank;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getTdCode() {
        return tdCode;
    }

    public void setTdCode(String tdCode) {
        this.tdCode = tdCode;
    }

    public String getCdCode() {
        return cdCode;
    }

    public void setCdCode(String cdCode) {
        this.cdCode = cdCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getPersonalCreditRecord() {
        return personalCreditRecord;
    }

    public void setPersonalCreditRecord(String personalCreditRecord) {
        this.personalCreditRecord = personalCreditRecord;
    }

    public Double getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(Double realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
    }

    @Override
    public String toString() {
        return "TradingListDto [id=" + id + ", transactionCode=" + transactionCode + ", contractCode=" + contractCode
            + ", enterpriseName=" + enterpriseName + ", tdCode=" + tdCode
            + ", legalPerson=" + legalPerson + ", cdCode=" + cdCode + ", mobile=" + mobile + ", approvalStatus="
            + approvalStatus + ", approvalResult=" + approvalResult + ", contractStatus=" + contractStatus
            + ", contractStartTime=" + contractStartTime + ", contractEndTime=" + contractEndTime
            + ", realCreditAmount=" + realCreditAmount + ", interestRate=" + interestRate + ", applicationTime=" + applicationTime
            + ", personalCreditRecord=" + personalCreditRecord + "]";
    }

}
