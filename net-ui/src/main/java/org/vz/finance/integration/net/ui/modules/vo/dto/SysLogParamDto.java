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
@ApiModel("操作日志数据")
public class SysLogParamDto implements Serializable {

    @QueryParam(name = "支行")
    private String thirdCode;

    @QueryParam(name = "用户名")
    private String username;

    private String thirdName;

    @QueryParam(name = "操作类型")
    private String operateType;

    @QueryParam(name = "查询开始时间")
    private String startTime;

    @QueryParam(name = "查询结束时间")
    private String endTime;

    @ApiModelProperty("当前页")
    private Integer current = SysConstants.DEFAULT_CURRENT_PAGE;

    @ApiModelProperty("每页大小")
    private Integer size = SysConstants.DEFAULT_PAGE_SIZE;


    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
