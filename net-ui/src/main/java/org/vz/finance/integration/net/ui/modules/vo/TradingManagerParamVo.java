package org.vz.finance.integration.net.ui.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

/**
 * <p>
 * 订单查询条件
 * </p>
 *
 * @author Jasmine
 * @since 2018-05-08
 */
@ApiModel("订单表单Vo")
public class TradingManagerParamVo {

    @ApiModelProperty("公司名称")
    private String enterpriseName;

    @ApiModelProperty("纳税人识别号")
    private String tdCode;

    @ApiModelProperty("身份证号")
    private String cdCode;

    @ApiModelProperty("合同号")
    private String contractCode;

    @ApiModelProperty("订单状态：0未开始 1审批中 2结束 3挂起")
    private Integer transactionStatus;

    @ApiModelProperty("合同状态：0.未签约 1：未生效 2：生效 3：冻结4：解冻 5：终止 6：已覆盖 7：已失效 8：已作废")
    private String contractStatus;

    @ApiModelProperty("审批流程状态 1：等待准入模型 2：等待个人征信授权 3：等待授信规则核定 4:额度审批 5:等待签署借款合同 6:等待支用 7:等待还款 8:流程结束")
    private Integer approvalResult;

    @ApiModelProperty("订单号")
    private String transactionCode;
    
    @ApiModelProperty("审批状态：000 待审批   997 审批通过  998  审批不通过  999 审批异常")
    private String approvalStatus;

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

    public Integer getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
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

    @Override
    public String toString() {
        return "TradingPageVo [enterpriseName=" + enterpriseName + ", tdCode=" + tdCode + ", cdCode=" + cdCode
                + ", contractCode=" + contractCode + ", transactionStatus=" + transactionStatus + ", contractStatus="
                + contractStatus + ", approvalResult=" + approvalResult + ", transactionCode=" + transactionCode
                + ", approvalStatus=" + approvalStatus + ", currentPage=" + currentPage + ", pageSize=" + pageSize
                + "]";
    }

   

}
