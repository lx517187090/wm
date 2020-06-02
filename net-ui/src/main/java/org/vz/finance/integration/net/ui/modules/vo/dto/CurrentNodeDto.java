package org.vz.finance.integration.net.ui.modules.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 当前节点记录
 * </p>
 *
 * @author Jasmine
 * @since 2018-05-08
 */

@ApiModel("当前节点Dto")
public class CurrentNodeDto implements Serializable {
    
    @ApiModelProperty("当前节点")
    private String currentNodeCode;
    
    @ApiModelProperty("流程")
    private String flowId;
    
    @ApiModelProperty("下一节点")
    private String nextNodeCode;

    public String getCurrentNodeCode() {
        return currentNodeCode;
    }

    public void setCurrentNodeCode(String currentNodeCode) {
        this.currentNodeCode = currentNodeCode;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getNextNodeCode() {
        return nextNodeCode;
    }

    public void setNextNodeCode(String nextNodeCode) {
        this.nextNodeCode = nextNodeCode;
    }

    @Override
    public String toString() {
        return "CurrentNodeVo [currentNodeCode=" + currentNodeCode + ", flowId=" + flowId + ", nextNodeCode="
                + nextNodeCode + "]";
    }

}
