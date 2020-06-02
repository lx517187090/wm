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
@ApiModel("后台管理支行信息vo")
public class OrgManagerPageDto {

    private String id;
    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String secondName;

    /**
     * 城市编号
     */
    @ApiModelProperty("城市编号")
    private String secondCode;

    /**
     * 网点编号
     */
    @ApiModelProperty("网点编号")
    private String thirdCode;

    /**
     * 网点名称
     */
    @ApiModelProperty("网点名称")
    private String thirdName;

    /**
     * 地址
     */
    @ApiModelProperty("网点地址")
    private String address;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String tel;


    private String status;

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
