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
@ApiModel("存量客户异常汇总报表")
public class ReportExceptionOrderPageDto implements Serializable {


    private String thirdCode;

    @ApiModelProperty("订单号")
    @CellFormat(firstTitle = "订单号")
    private String orderNo;

    @ApiModelProperty("机构名称")
    @CellFormat(firstTitle = "机构名称")
    private String thirdName;

    @ApiModelProperty("纳税识别号")
    @CellFormat(firstTitle = "纳税识别号")
    private String taxNum;

    @ApiModelProperty("公司名称")
    @CellFormat(firstTitle = "公司名称")
    private String companyName;

    @ApiModelProperty("法定代表人姓名")
    @CellFormat(firstTitle = "法定代表人姓名")
    private String corporateName;

    @ApiModelProperty("法定代表人证件号码")
    @CellFormat(firstTitle = "法定代表人证件号码")
    private String corporateIdNumber;

    @ApiModelProperty("申请额度（万元）")
    @CellFormat(firstTitle = "申请额度（万元）", filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double financeAmount;

    @ApiModelProperty("申请时间")
    @CellFormat(firstTitle = "申请时间")
    private String createTime;

    @ApiModelProperty("异常原因")
    @CellFormat(firstTitle = "异常原因")
    private String remark;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCorporateIdNumber() {
        return corporateIdNumber;
    }

    public void setCorporateIdNumber(String corporateIdNumber) {
        this.corporateIdNumber = corporateIdNumber;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
