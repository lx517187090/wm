package org.vz.finance.integration.net.ui.modules.vo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 融资交易表
 * </p>
 *
 * @author jaden
 * @since 2018-05-17
 */
public class FinanceTransactionDto implements Serializable {

    private String id;

    /**
     * 客户经理姓名
     */
    private String accountManagerName;
    /**
     * 申请时间
     */
    private Date applicationTime;
    /**
     * 合同状态：0 未生效 1 生效 2注销（合同到期）3撤销（手工干预撤销）、作废（线下尽调否决）
     */
    private Integer contractStatus;
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 审批状态：1 等待准入模型 2等待个人征信授权 3 等待授信规则核定 4 等待签署借款合同 5 等待支用 6 等待还款 7流程结束
     */
    private Integer approvalStatus;
    /**
     * 申请金额
     */
    private Double applicationAmount;
    /**
     * 1:有 0：没有
     */
    private Integer isSign;
    /**
     * 审批类型：1 自动审批，2人工审批
     */
    private Integer approvalType;
    /**
     * 客户经理编号
     */
    private String accountManagerCode;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单状态：0 未通过 1审批中2 已通过
     */
    private Integer transactionStatus;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 交易编码
     */
    private String transactionCode;
    /**
     * 全国联行号
     */
    private String cInterbankId;
    /**
     * 开户行名
     */
    private String openAcctBranName;
    /**
     * 开户机构
     */
    private String openAcctBranchId;
    /**
     * 户名
     */
    private String acctName;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 借款用途
     */
    private String drUsage;
    /**
     * 还款方式
     */
    private String repayType;
    /**
     * 执行利率
     */
    private Double actIntRate;
    /**
     * 结算账号
     */
    private String settleAcctNo;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 合同生效日
     */
    private String contractStartTime;
    /**
     * 合同到期日
     */
    private String contractEndTime;
    /**
     * 期限类型
     */
    private String termType;
    /**
     * 期限
     */
    private String term;
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAccountManagerName() {
        return accountManagerName;
    }

    public void setAccountManagerName(String accountManagerName) {
        this.accountManagerName = accountManagerName;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Double getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(Double applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }

    public String getAccountManagerCode() {
        return accountManagerCode;
    }

    public void setAccountManagerCode(String accountManagerCode) {
        this.accountManagerCode = accountManagerCode;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getcInterbankId() {
        return cInterbankId;
    }

    public void setcInterbankId(String cInterbankId) {
        this.cInterbankId = cInterbankId;
    }

    public String getOpenAcctBranName() {
        return openAcctBranName;
    }

    public void setOpenAcctBranName(String openAcctBranName) {
        this.openAcctBranName = openAcctBranName;
    }

    public String getOpenAcctBranchId() {
        return openAcctBranchId;
    }

    public void setOpenAcctBranchId(String openAcctBranchId) {
        this.openAcctBranchId = openAcctBranchId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getDrUsage() {
        return drUsage;
    }

    public void setDrUsage(String drUsage) {
        this.drUsage = drUsage;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public Double getActIntRate() {
        return actIntRate;
    }

    public void setActIntRate(Double actIntRate) {
        this.actIntRate = actIntRate;
    }

    public String getSettleAcctNo() {
        return settleAcctNo;
    }

    public void setSettleAcctNo(String settleAcctNo) {
        this.settleAcctNo = settleAcctNo;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
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

    @Override
    public String toString() {
        return "FinanceTransaction{" + "accountManagerName=" + accountManagerName + ", applicationTime="
                + applicationTime + ", contractStatus=" + contractStatus + ", contractCode=" + contractCode
                + ", approvalStatus=" + approvalStatus + ", applicationAmount=" + applicationAmount + ", isSign="
                + isSign + ", approvalType=" + approvalType + ", accountManagerCode=" + accountManagerCode + ", deptId="
                + deptId + ", userId=" + userId + ", transactionStatus=" + transactionStatus + ", customerId="
                + customerId + ", transactionCode=" + transactionCode + ", cInterbankId=" + cInterbankId
                + ", openAcctBranName=" + openAcctBranName + ", openAcctBranchId=" + openAcctBranchId + ", acctName="
                + acctName + ", productNo=" + productNo + ", drUsage=" + drUsage + ", repayType=" + repayType
                + ", actIntRate=" + actIntRate + ", settleAcctNo=" + settleAcctNo + ", ccy=" + ccy
                + ", contractStartTime=" + contractStartTime + ", contractEndTime=" + contractEndTime + "}";
    }
}
