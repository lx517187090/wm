package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;


/**
 * @author lzy
 * @since 2018/08/21
 */
@ApiModel("订单统计报表列表vo")
public class ReportPreCreditPageDto implements Serializable {


    private String thirdCode;

    @ApiModelProperty("渠道号")
    @CellFormat(firstTitle="渠道号")
    private String sourceName;

    @ApiModelProperty("已申请订单")
    @CellFormat(firstTitle="已申请订单")
    private Integer applyNum;

    @ApiModelProperty("校验未通过订单")
    @CellFormat(firstTitle="校验未通过订单")
    private Integer checkFailNum;

    @ApiModelProperty("有效订单")
    @CellFormat(firstTitle="有效订单")
    private Integer checkSuccessNum;

    @ApiModelProperty("规则模型未通过订单")
    @CellFormat(firstTitle="规则模型未通过订单")
    private Integer failNum;

    @ApiModelProperty("最终已审批通过订单")
    @CellFormat(firstTitle="最终已审批通过订单")
    private Integer approvedNum;

    @ApiModelProperty("最终已放款订单")
    @CellFormat(firstTitle="最终已放款订单")
    private Integer passNum;

    @ApiModelProperty("最终已终止订单")
    @CellFormat(firstTitle="最终已终止订单")
    private Integer breakNum;

    @ApiModelProperty("最终审批拒绝订单")
    @CellFormat(firstTitle="最终审批拒绝订单")
    private Integer refuseNum;

    private String secondName;

    private String secondCode;

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getCheckFailNum() {
        return checkFailNum;
    }

    public void setCheckFailNum(Integer checkFailNum) {
        this.checkFailNum = checkFailNum;
    }

    public Integer getCheckSuccessNum() {
        return checkSuccessNum;
    }

    public void setCheckSuccessNum(Integer checkSuccessNum) {
        this.checkSuccessNum = checkSuccessNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public Integer getApprovedNum() {
        return approvedNum;
    }

    public void setApprovedNum(Integer approvedNum) {
        this.approvedNum = approvedNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getBreakNum() {
        return breakNum;
    }

    public void setBreakNum(Integer breakNum) {
        this.breakNum = breakNum;
    }

    public Integer getRefuseNum() {
        return refuseNum;
    }

    public void setRefuseNum(Integer refuseNum) {
        this.refuseNum = refuseNum;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }
}
