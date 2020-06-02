package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yezhaoxing
 * @since 2018/06/05
 */
@ApiModel("审核预授信dto")
public class CustomerApprovalParamDto {

    @ApiModelProperty("订单号")
    private String preCreditId;

    @ApiModelProperty("通过或者拒绝:997为通过,998为拒绝")
    private String approveStatus;

    public String getPreCreditId() {
        return preCreditId;
    }

    public void setPreCreditId(String preCreditId) {
        this.preCreditId = preCreditId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
}
