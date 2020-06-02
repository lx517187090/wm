package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.annotation.FormatField;
import org.vz.finance.integration.net.ui.core.annotation.LogField;
import org.vz.finance.integration.net.ui.core.annotation.QueryParam;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lixi
 * @since 2019/11/07
 */
@ApiModel("订单管理查询表单对象")
public class OrderManagerParamDto implements Serializable {

    private String id;

    @ApiModelProperty("办理网点")
    private String thirdName;

    @ApiModelProperty("身份证号")
    @QueryParam(name = "身份证")
    private String corporateIdNumber;

    @ApiModelProperty("公司名称")
    @QueryParam(name = "公司名称")
    private String companyName;

    @ApiModelProperty("纳税人识别号")
    @QueryParam(name = "纳税识别号")
    private String taxNum;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "客户经理编号")
    @QueryParam(name = "客户经理编号")
    @LogField(columnName = "customer_manager_no", content = "客户经理")
    private String customerManagerNo;

    @ApiModelProperty("登记开始时间")
    @QueryParam(name = "登记开始时间")
    private String registerTimeStart;

    @ApiModelProperty("登记结束时间")
    @QueryParam(name = "登记结束时间")
    private String registerTimeEnd;

    @LogField(columnName = "order_remark", content = "订单状态")
    @QueryParam(name = "审批状态")
    @FormatField(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "orderRemarkResult")
    private String orderRemark;

    private Boolean firstQuery;

    private BigDecimal realCreditAmount;

    private Double interestRate;

    private String realLimitTime;

    @LogField(columnName = "remark_", content = "备注")
    private String remark;

    private String hasCustomerManagerNo;

    @LogField(key = true, columnName = "order_no", content = "订单号")
    private String orderNo;

    @LogField(key = true, columnName = "third_code", content = "支行")
    @QueryParam(name = "办理网点")
    private String thirdCode;

    private String secondCode;

    @ApiModelProperty("当前页")
    private Integer currentPage = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer pageSize = SysConstants.DEFAULT_PAGE_SIZE;

    /**
     * 查询条件状态
     */
    private List<String> statusLimit;

    public OrderManagerParamDto() {
    }

    private OrderManagerParamDto(Builder builder) {
        setId(builder.id);
        setThirdName(builder.thirdName);
        setCorporateIdNumber(builder.corporateIdNumber);
        setCompanyName(builder.companyName);
        setTaxNum(builder.taxNum);
        setStatus(builder.status);
        setCustomerManagerNo(builder.customerManagerNo);
        setRegisterTimeStart(builder.registerTimeStart);
        setRegisterTimeEnd(builder.registerTimeEnd);
        setOrderRemark(builder.orderRemark);
        setFirstQuery(builder.firstQuery);
        setRealCreditAmount(builder.realCreditAmount);
        setInterestRate(builder.interestRate);
        setRealLimitTime(builder.realLimitTime);
        setRemark(builder.remark);
        setHasCustomerManagerNo(builder.hasCustomerManagerNo);
        setOrderNo(builder.orderNo);
        setThirdCode(builder.thirdCode);
        setSecondCode(builder.secondCode);
        setCurrentPage(builder.currentPage);
        setPageSize(builder.pageSize);
        setStatusLimit(builder.statusLimit);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OrderManagerParamDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.thirdName = copy.getThirdName();
        builder.corporateIdNumber = copy.getCorporateIdNumber();
        builder.companyName = copy.getCompanyName();
        builder.taxNum = copy.getTaxNum();
        builder.status = copy.getStatus();
        builder.customerManagerNo = copy.getCustomerManagerNo();
        builder.registerTimeStart = copy.getRegisterTimeStart();
        builder.registerTimeEnd = copy.getRegisterTimeEnd();
        builder.orderRemark = copy.getOrderRemark();
        builder.firstQuery = copy.getFirstQuery();
        builder.realCreditAmount = copy.getRealCreditAmount();
        builder.interestRate = copy.getInterestRate();
        builder.realLimitTime = copy.getRealLimitTime();
        builder.remark = copy.getRemark();
        builder.hasCustomerManagerNo = copy.getHasCustomerManagerNo();
        builder.orderNo = copy.getOrderNo();
        builder.thirdCode = copy.getThirdCode();
        builder.secondCode = copy.getSecondCode();
        builder.currentPage = copy.getCurrentPage();
        builder.pageSize = copy.getPageSize();
        builder.statusLimit = copy.getStatusLimit();
        return builder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getCorporateIdNumber() {
        return corporateIdNumber;
    }

    public void setCorporateIdNumber(String corporateIdNumber) {
        this.corporateIdNumber = corporateIdNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerManagerNo() {
        return customerManagerNo;
    }

    public void setCustomerManagerNo(String customerManagerNo) {
        this.customerManagerNo = customerManagerNo;
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

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public Boolean getFirstQuery() {
        return firstQuery;
    }

    public void setFirstQuery(Boolean firstQuery) {
        this.firstQuery = firstQuery;
    }

    public BigDecimal getRealCreditAmount() {
        return realCreditAmount;
    }

    public void setRealCreditAmount(BigDecimal realCreditAmount) {
        this.realCreditAmount = realCreditAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getRealLimitTime() {
        return realLimitTime;
    }

    public void setRealLimitTime(String realLimitTime) {
        this.realLimitTime = realLimitTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHasCustomerManagerNo() {
        return hasCustomerManagerNo;
    }

    public void setHasCustomerManagerNo(String hasCustomerManagerNo) {
        this.hasCustomerManagerNo = hasCustomerManagerNo;
    }

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

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
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

    public List<String> getStatusLimit() {
        return statusLimit;
    }

    public void setStatusLimit(List<String> statusLimit) {
        this.statusLimit = statusLimit;
    }

    public static final class Builder {
        private String id;
        private String thirdName;
        private String corporateIdNumber;
        private String companyName;
        private String taxNum;
        private String status;
        private String customerManagerNo;
        private String registerTimeStart;
        private String registerTimeEnd;
        private String orderRemark;
        private Boolean firstQuery;
        private BigDecimal realCreditAmount;
        private Double interestRate;
        private String realLimitTime;
        private String remark;
        private String hasCustomerManagerNo;
        private String orderNo;
        private String thirdCode;
        private String secondCode;
        private Integer currentPage;
        private Integer pageSize;
        private List<String> statusLimit;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder thirdName(String val) {
            thirdName = val;
            return this;
        }

        public Builder corporateIdNumber(String val) {
            corporateIdNumber = val;
            return this;
        }

        public Builder companyName(String val) {
            companyName = val;
            return this;
        }

        public Builder taxNum(String val) {
            taxNum = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder customerManagerNo(String val) {
            customerManagerNo = val;
            return this;
        }

        public Builder registerTimeStart(String val) {
            registerTimeStart = val;
            return this;
        }

        public Builder registerTimeEnd(String val) {
            registerTimeEnd = val;
            return this;
        }

        public Builder orderRemark(String val) {
            orderRemark = val;
            return this;
        }

        public Builder firstQuery(Boolean val) {
            firstQuery = val;
            return this;
        }

        public Builder realCreditAmount(BigDecimal val) {
            realCreditAmount = val;
            return this;
        }

        public Builder interestRate(Double val) {
            interestRate = val;
            return this;
        }

        public Builder realLimitTime(String val) {
            realLimitTime = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder hasCustomerManagerNo(String val) {
            hasCustomerManagerNo = val;
            return this;
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder thirdCode(String val) {
            thirdCode = val;
            return this;
        }

        public Builder secondCode(String val) {
            secondCode = val;
            return this;
        }

        public Builder currentPage(Integer val) {
            currentPage = val;
            return this;
        }

        public Builder pageSize(Integer val) {
            pageSize = val;
            return this;
        }

        public Builder statusLimit(List<String> val) {
            statusLimit = val;
            return this;
        }

        public OrderManagerParamDto build() {
            return new OrderManagerParamDto(this);
        }
    }
}
