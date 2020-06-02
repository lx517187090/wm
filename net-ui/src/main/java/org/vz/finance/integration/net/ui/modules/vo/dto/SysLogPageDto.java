package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.excel.annotation.CellFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 贷后模块表单数据
 * </p>
 *
 * @author lzy
 * @since 2018-08-22
 */
@ApiModel("操作日志数据")
public class SysLogPageDto implements Serializable {

    @ApiModelProperty("操作时间")
    private Date createTime;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("支行编码")
    private String thirdName;

    @ApiModelProperty("操作类型")
    @CellFormat(filterClass = SysConstants.CELL_FORMAT_FILTER, filterMethod = "operateTypeTransfer")
    private String operateType;

    @ApiModelProperty("操作内容")
    private String params;

    @ApiModelProperty("ip地址")
    private String ip;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
