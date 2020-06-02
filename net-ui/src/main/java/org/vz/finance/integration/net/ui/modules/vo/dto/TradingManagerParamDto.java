package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

import java.io.Serializable;

/**
 * <p>
 * 订单查询条件
 * </p>
 *
 * @author Jasmine
 * @since 2018-05-08
 */
@ApiModel("订单表单Dto")
public class TradingManagerParamDto implements Serializable {

    @ApiModelProperty("公司名称")
    private String enterpriseName;

    @ApiModelProperty("纳税人识别号")
    private String tdCode;

    @ApiModelProperty("身份证号")
    private String cdCode;

    @ApiModelProperty("合同号")
    private String contractCode;

    @ApiModelProperty("订单状态：0未开始  1审批中   2结束    3挂起")
    private Integer transactionStatus;

    @ApiModelProperty("审批状态：1准入节点 2额度审批节点 3个人征信和反欺诈节点 4结束节点")
    private Integer approvalResult;

    @ApiModelProperty("合同状态：1:未生效，2:生效 ，3:注销，4:注销 5:已完成")
    private String contractStatus;
    
    @ApiModelProperty("审批状态：001 审批中   002 审批通过  003 审批拒绝  004 订单关闭")
    private String approvalStatus;

    @ApiModelProperty("订单号")
    private String transactionCode;

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

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

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
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

    public Integer getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    @Override
    public String toString() {
        return "TradingPageDto [enterpriseName=" + enterpriseName + ", tdCode=" + tdCode + ", cdCode=" + cdCode
                + ", contractCode=" + contractCode + ", transactionStatus=" + transactionStatus + ", approvalResult="
                + approvalResult + ", contractStatus=" + contractStatus + ", approvalStatus=" + approvalStatus
                + ", transactionCode=" + transactionCode + ", currentPage=" + currentPage + ", pageSize=" + pageSize
                + "]";
    }
    
    
}
