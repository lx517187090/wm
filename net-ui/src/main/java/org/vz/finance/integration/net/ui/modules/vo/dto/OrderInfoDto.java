package org.vz.finance.integration.net.ui.modules.vo.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class OrderInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tdCode;// 纳税人识别号

    private String companyName;// 公司名称

    private String orderNo;// 订单编号

    private String approvalStatus;// 审批状态

    private Double preCreditAmount;// 预授信额度

    private Double interestRate;// 利率

    private String contractStartTime;// 合同生效日

    private String contractEndTime;// 合同到期日

    public String getTdCode() {
        return tdCode;
    }

    public void setTdCode(String tdCode) {
        this.tdCode = tdCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Double getPreCreditAmount() {
        return preCreditAmount;
    }

    public void setPreCreditAmount(Double preCreditAmount) {
        this.preCreditAmount = preCreditAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
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

    public String getApprovalStatusDisPlay() {
        String rtn = "";
        if(StringUtils.isNotEmpty(this.approvalStatus)){
            switch (this.approvalStatus) {
                case "997":
                    rtn = "通过";
                    break;
                case "000":
                    rtn = "待审批";
                    break;
                case "998":
                    rtn = "未通过";
                    break;
                case "999":
                    rtn = "审批异常";
                    break;
            }
        }
        return rtn;
    }
}
