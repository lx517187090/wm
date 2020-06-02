package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lixi
 * @since 2019/11/07
 */
@ApiModel("后台管理订单列表vo")
public class OrderManagerPageDto {

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    private Date createTime;

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /**
     * 网点名称
     */
    @ApiModelProperty("办理网点")
    private String thirdName;

    /**
     * 网点名称
     */
    private String thirdCode;
    /**
     * 网点名称
     */
    private String secondCode;
    /**
     * 客户经理编号
     */
    @ApiModelProperty("客户经理编号")
    private String customerManagerNo;

    /**
     * 客户经理姓名
     */
    @ApiModelProperty("客户经理姓名")
    private String customerManagerName;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 纳税识别号
     */
    @ApiModelProperty("纳税识别号")
    private String taxNum;

    /**
     * 法定代表人姓名
     */
    @ApiModelProperty("法人姓名")
    private String corporateName;

    /**
     * 客户证件号码
     */
    @ApiModelProperty("法人身份证号码")
    private String corporateIdNumber;

    /**
     * 法定代表人联系电话
     */
    @ApiModelProperty("法人联系电话")
    private String corporatePhone;

    /**
     * 联系人电话号码
     */
    @ApiModelProperty("联系人电话号码")
    private String contactPhone;

    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    private String contactName;

    /**
     * 实际办公地址
     */
    @ApiModelProperty("实际办公地址")
    private String officeAddress;

    /**
     * 订单状态备注字段
     */
    @ApiModelProperty(value = "审批状态", notes = "0：待分配 1:已分配 2:已联系 3：已上门 4：已反馈 5：已尽调 6：已审批 7：已放款 8：已终止 9：审批拒绝")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "orderRemarkResult")
    private String orderRemark;

    /**
     * 状态
     */
    @ApiModelProperty(value ="订单状态" , notes = "1000：初始化 2000:审批中 9999:拒绝 0000：通过 ")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "orderStatus")
    private String status;

    /**
     * 准入状态
     */
    @ApiModelProperty(value ="预授信结果" , notes = "00000000：通过 99999999:不通过 ")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "filterAccessStatusResult")
    private String accessStatus;

    /**
     * 申请渠道
     */
    private String sourceTyp;

    /**
     * 申请渠道
     */
    @ApiModelProperty("申请渠道")
    private String sourceName;

    /**
     * 预授信不通过原因
     */
    @ApiModelProperty("不通过原因")
    private String accessDetail;

    /**
     * 申请授信额度
     */
    @ApiModelProperty("申请额度（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double financeAmount;


    @ApiModelProperty("预授信额度（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double  preCreditAmount;

    /**
     * 真实额度
     */
    @ApiModelProperty("最终审批额度（万元）")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatNumber")
    private Double realCreditAmount;

    /**
     * 真实期限
     */
    @ApiModelProperty("最终申请期限")
    private String realLimitTime;


    /**
     * 利率
     */
    @ApiModelProperty("最终利率")
    private Double interestRate;


    /**
     * AHP评分
     */
    @ApiModelProperty("AHP评分")
    private String businessScore;

    /**
     * 信用模型评级
     */
    @ApiModelProperty("信用模型评级")
    private String businessRating;

     /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 是否愿意提供抵押物来扩大授信额度
     * 0-是，1-否
     */
    @ApiModelProperty("是/否愿意提供抵押物来扩大授信额度")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "formatCollateralizeFlg")
    private String collateralizeFlg;

    /**
     * 审批单
     */
    private String pdfPath;

    /**
     * 征信报告
     */
    private String pdfUrl;

    /**
     * 意向融资期限
     */
    private String limitTime;

    /**
     * 备注
     */
    private String remark;



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getCustomerManagerNo() {
        return customerManagerNo;
    }

    public void setCustomerManagerNo(String customerManagerNo) {
        this.customerManagerNo = customerManagerNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
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

    public String getCorporatePhone() {
        return corporatePhone;
    }

    public void setCorporatePhone(String corporatePhone) {
        this.corporatePhone = corporatePhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getSourceTyp() {
        return sourceTyp;
    }

    public void setSourceTyp(String sourceTyp) {
        this.sourceTyp = sourceTyp;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public Double getPreCreditAmount() {
        return preCreditAmount;
    }

    public void setPreCreditAmount(Double preCreditAmount) {
        this.preCreditAmount = preCreditAmount;
    }

    public Double getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(Double realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
    }

    public String getRealLimitTime() {
        return realLimitTime;
    }

    public void setRealLimitTime(String realLimitTime) {
        this.realLimitTime = realLimitTime;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(String businessScore) {
        this.businessScore = businessScore;
    }

    public String getBusinessRating() {
        return businessRating;
    }

    public void setBusinessRating(String businessRating) {
        this.businessRating = businessRating;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }

    public String getAccessDetail() {
        return accessDetail;
    }

    public void setAccessDetail(String accessDetail) {
        this.accessDetail = accessDetail;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getCollateralizeFlg() {
        return collateralizeFlg;
    }

    public void setCollateralizeFlg(String collateralizeFlg) {
        this.collateralizeFlg = collateralizeFlg;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }
}
