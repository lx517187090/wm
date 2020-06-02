package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;


/**
 * @author lzy
 * @since 2018/08/21
 */
@ApiModel("预授信统计报表列表vo")
public class ReportApplyChannelPageDto implements Serializable {

    @ApiModelProperty("机构名称")
    @CellFormat(firstTitle="机构名称")
    private String orgName;
    
    @ApiModelProperty("银行端")
    @CellFormat(firstTitle="预授信申请入口")
    private String bank;

    @ApiModelProperty("微众端")
    @CellFormat(firstTitle="预授信申请入口")
    private String vzoom;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getVzoom() {
        return vzoom;
    }

    public void setVzoom(String vzoom) {
        this.vzoom = vzoom;
    }


   
    
    

    

}
