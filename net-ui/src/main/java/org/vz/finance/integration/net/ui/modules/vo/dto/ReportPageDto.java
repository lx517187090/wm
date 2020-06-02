package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.annotation.QueryParam;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

import java.io.Serializable;

/**
 * <p>
 * 贷后模块表单数据
 * </p>
 *
 * @author lzy
 * @since 2018-08-22
 */
@ApiModel("报表模块表单数据")
public class ReportPageDto implements Serializable {

    @ApiModelProperty("法定代表人证件号码")
    @QueryParam(name = "法人证件号码")
    private String corporateIdNumber;

    @ApiModelProperty("法定代表人姓名")
    @QueryParam(name = "法人姓名")
    private String corporateName;

    @ApiModelProperty("机构号")
    private String contactName;

    @ApiModelProperty("纳税识别号")
    @QueryParam(name = "纳税识别号")
    private String taxNum;

    @ApiModelProperty("公司名称")
    @QueryParam(name = "公司名称")
    private String companyName;

    @ApiModelProperty("机构号")
    @QueryParam(name = "支行")
    private String thirdCode;

    @ApiModelProperty("渠道号")
    @QueryParam(name = "渠道")
    private String sourceTyp;

    @ApiModelProperty("登记开始时间")
    @QueryParam(name = "起始时间")
    private String registerTimeStart;

    @ApiModelProperty("登记结束时间")
    @QueryParam(name = "结束时间")
    private String registerTimeEnd;

    /**
     * 客户经理编号
     */
    private String customerManagerNo;

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

    private Boolean firstQuery;


    public Integer getCurrentPage() {
        return currentPage;
    }


    public String getRegisterTimeStart() {
        return registerTimeStart;
    }


    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }


    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }


    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
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

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getSourceTyp() {
        return sourceTyp;
    }

    public void setSourceTyp(String sourceTyp) {
        this.sourceTyp = sourceTyp;
    }

    public String getCorporateIdNumber() {
        return corporateIdNumber;
    }

    public void setCorporateIdNumber(String corporateIdNumber) {
        this.corporateIdNumber = corporateIdNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getFirstQuery() {
        return firstQuery;
    }

    public void setFirstQuery(Boolean firstQuery) {
        this.firstQuery = firstQuery;
    }

    public String getCustomerManagerNo() {
        return customerManagerNo;
    }

    public void setCustomerManagerNo(String customerManagerNo) {
        this.customerManagerNo = customerManagerNo;
    }
}
