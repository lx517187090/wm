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
 * @author Jasmine
 * @since 2018-05-09
 */
@ApiModel("贷后模块表单数据")
public class AfterLoaningPageDto implements Serializable {

    //@ApiModelProperty("机构")
    private String orgName;

    @ApiModelProperty("支行")
    @QueryParam(name = "支行")
    private String thirdCode;

    @ApiModelProperty("纳税识别号")
    @QueryParam(name = "纳税识别号")
    private String tdCode;

    @ApiModelProperty("身份证号码")
    @QueryParam(name = "身份证号码")
    private String cdCode;

    @ApiModelProperty("合同编号")
    private String contractCode;

    @ApiModelProperty("手机号")
    @QueryParam(name = "手机号")
    private String mobile;

    @ApiModelProperty("法人")
    @QueryParam(name = "法人")
    private String legalPerson;

    @ApiModelProperty("监控日期")
    @QueryParam(name = "监控日期")
    private String monitoringTime;

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

    private String customerManagerNo;

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
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

    public String getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(String monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getCustomerManagerNo() {
        return customerManagerNo;
    }

    public void setCustomerManagerNo(String customerManagerNo) {
        this.customerManagerNo = customerManagerNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
