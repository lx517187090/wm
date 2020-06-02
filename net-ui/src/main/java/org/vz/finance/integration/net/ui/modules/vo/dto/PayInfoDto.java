package org.vz.finance.integration.net.ui.modules.vo.dto;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/08/13
 */
public class PayInfoDto {

    private String tdCode;// 纳税人识别号

    private String companyName;// 公司名称

    private String contNo;// 合同号

    private Double disburseAmount;// 支用金额

    private String tranType; // 交易类型

    private String curType;// 币种

    private String oriSourceType;// 渠道类型

    private String applyType;// 申请方式

    private Date disburseDate;// 支用款日期

    private String cusCardCode;// 结算卡号

    private String billNo;// 借据号

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

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public Double getDisburseAmount() {
        return disburseAmount;
    }

    public void setDisburseAmount(Double disburseAmount) {
        this.disburseAmount = disburseAmount;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getCurType() {
        return curType;
    }

    public void setCurType(String curType) {
        this.curType = curType;
    }

    public String getOriSourceType() {
        return oriSourceType;
    }

    public void setOriSourceType(String oriSourceType) {
        this.oriSourceType = oriSourceType;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Date getDisburseDate() {
        return disburseDate;
    }

    public void setDisburseDate(Date disburseDate) {
        this.disburseDate = disburseDate;
    }

    public String getCusCardCode() {
        return cusCardCode;
    }

    public void setCusCardCode(String cusCardCode) {
        this.cusCardCode = cusCardCode;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
