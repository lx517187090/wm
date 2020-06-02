package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;


/**
 * @author lzy
 * @since 2018/08/21
 */
@ApiModel("机构统计报表列表vo")
public class ReportOrgPageDto implements Serializable {

    @ApiModelProperty("机构名称")
    @CellFormat(firstTitle="机构名称")
    private String thirdName;

    @ApiModelProperty("渠道")
    @CellFormat(firstTitle="渠道")
    private String sourceName;

    @ApiModelProperty("有效订单数")
    @CellFormat(firstTitle="有效订单数")
    private String applyNum;

    @ApiModelProperty("最终已审批通过订单数")
    @CellFormat(firstTitle="最终已审批通过订单数")
    private Double approvedNum;

    @ApiModelProperty("最终已放款订单数")
    @CellFormat(firstTitle="最终已放款订单数")
    private String passNum;

    @ApiModelProperty("最终审批拒绝订单数")
    @CellFormat(firstTitle="最终审批拒绝订单数")
    private String breakNum;

    @ApiModelProperty("已放款金额（万元）")
    @CellFormat(firstTitle="已放款金额（万元）", filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double loanAmount;

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public Double getApprovedNum() {
        return approvedNum;
    }

    public void setApprovedNum(Double approvedNum) {
        this.approvedNum = approvedNum;
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public String getBreakNum() {
        return breakNum;
    }

    public void setBreakNum(String breakNum) {
        this.breakNum = breakNum;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
}