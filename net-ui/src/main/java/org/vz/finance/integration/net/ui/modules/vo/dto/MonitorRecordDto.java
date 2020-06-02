package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author yezhaoxing
 * @since 2018/06/22
 */
public class MonitorRecordDto extends BaseCustomerDto {

    @ApiModelProperty("监控时间")
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
