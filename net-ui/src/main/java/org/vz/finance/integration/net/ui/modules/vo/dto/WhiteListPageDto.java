package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 白名单显示页面Dto
 * </p>
 * 
 * @author zhanlingxia
 * @since 2018-07-19
 */
@ApiModel("白名单页面显示dto")
public class WhiteListPageDto implements Serializable{

    private String id;

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("证件号码")
    private String globalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "WhiteListPageDto [id=" + id + ", companyName=" + companyName + ", globalId="
                + globalId + "]";
    }

}
