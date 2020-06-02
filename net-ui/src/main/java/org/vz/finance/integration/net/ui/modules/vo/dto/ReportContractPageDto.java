package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;


/**
 * @author lzy
 * @since 2018-08-23
 */
@ApiModel("客户统计报表列表vo")
public class ReportContractPageDto implements Serializable {

    @ApiModelProperty("网点名称")
    @CellFormat(firstTitle="网点名称")
    private String thirdName;

    private String thirdCode;

    private String taxNum;


    private String secondCode;

    private String secondName;

    @ApiModelProperty("已申请客户数")
    @CellFormat(firstTitle="已申请客户数")
    private String applyNum;

    @ApiModelProperty("有效客户数")
    @CellFormat(firstTitle="有效客户数")
    private String effectiveNum;

    @ApiModelProperty("最终已审批通过客户数")
    @CellFormat(firstTitle="最终已审批通过客户数")
    private String approved;


    @ApiModelProperty("已发放款客户数")
    @CellFormat(firstTitle="已发放款客户数")
    private String loanNum;

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getEffectiveNum() {
        return effectiveNum;
    }

    public void setEffectiveNum(String effectiveNum) {
        this.effectiveNum = effectiveNum;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }
}
