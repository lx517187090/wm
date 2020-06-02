package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 利率配置
 * </p>
 *
 * @author lizuy803
 * @since 2018-06-08
 */
@ApiModel("利率配置vo")
public class RateConfigureDto implements Serializable {

    @ApiModelProperty("等级")
    private String grade;
    @ApiModelProperty("利率")
    private Double rate;
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("省份编码")
    private String provinceCode;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
