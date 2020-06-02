package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yezhaoxing
 * @since 2018/06/05
 */
@ApiModel("修改用户风险等级dt0")
public class AfterloanUpdateRickDto {

    @ApiModelProperty("纳税人识别号")
    private String tdCode;

    @ApiModelProperty(value = "风险等级", notes = "1正常,2关注,3次级,4可疑,5损失")
    private String businessRate;

    @ApiModelProperty("风险说明")
    private String ratingDescription;

    public String getTdCode() {
        return tdCode;
    }

    public void setTdCode(String tdCode) {
        this.tdCode = tdCode;
    }

    public String getBusinessRate() {
        return businessRate;
    }

    public void setBusinessRate(String businessRate) {
        this.businessRate = businessRate;
    }

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }
}
