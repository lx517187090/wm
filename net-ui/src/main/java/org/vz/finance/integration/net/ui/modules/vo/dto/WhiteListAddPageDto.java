package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 白名单增加页面Dto
 * </p>
 * 
 * @author zhanlingxia
 * @since 2018-06-22
 */
@ApiModel("白名单增加页面dto")
public class WhiteListAddPageDto implements Serializable{

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("证件号码")
    private String globalId;

    @ApiModelProperty("纳税人识别号")
    private String tdCode;

    @ApiModelProperty("证件类型")
    private String globalType;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getTdCode() {
        return tdCode;
    }

    public void setTdCode(String tdCode) {
        this.tdCode = tdCode;
    }

    public String getGlobalType() {
        return globalType;
    }

    public void setGlobalType(String globalType) {
        this.globalType = globalType;
    }

    @Override
    public String toString() {
        return "WhiteListAddPageDto [companyName=" + companyName + ", globalId=" + globalId + ", tdCode=" + tdCode
                + ", globalType=" + globalType + "]";
    }

}
