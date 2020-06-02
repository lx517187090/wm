package org.vz.finance.integration.net.ui.modules.vo.dto;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/08/13
 */
public class RepayInfoDto {

    private String tdCode;// 纳税人识别号

    private String companyName;// 公司名称

    private String contNo;// 合同号

    private Double repainAmount;// 还款金额

    private String tranType;// 交易类型

    private String billNo;// 借据号

    private String curType;// 币种

    private String applyType;// 申请方式

    private Date repainDate;// 还款日期

    private String cusCardCode;// 结算账号

    private String oriSourceType;// 渠道类型

    private String id; // 流水号

    private Double repayInt;// 还款利息

    private Double repayCorpus;// 还款本金

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

    public Double getRepainAmount() {
        return repainAmount;
    }

    public void setRepainAmount(Double repainAmount) {
        this.repainAmount = repainAmount;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCurType() {
        return curType;
    }

    public void setCurType(String curType) {
        this.curType = curType;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Date getRepainDate() {
        return repainDate;
    }

    public void setRepainDate(Date repainDate) {
        this.repainDate = repainDate;
    }

    public String getCusCardCode() {
        return cusCardCode;
    }

    public void setCusCardCode(String cusCardCode) {
        this.cusCardCode = cusCardCode;
    }

    public String getOriSourceType() {
        return oriSourceType;
    }

    public void setOriSourceType(String oriSourceType) {
        this.oriSourceType = oriSourceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRepayInt() {
        return repayInt;
    }

    public void setRepayInt(Double repayInt) {
        this.repayInt = repayInt;
    }

    public Double getRepayCorpus() {
        return repayCorpus;
    }

    public void setRepayCorpus(Double repayCorpus) {
        this.repayCorpus = repayCorpus;
    }
}
