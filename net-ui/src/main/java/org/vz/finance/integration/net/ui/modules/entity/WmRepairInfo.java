package org.vz.finance.integration.net.ui.modules.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("wm_order_info")
public class WmRepairInfo {
    @TableId("ID")
    private String id;

    @TableField("info_id")
    private String infoId;
    @TableField("faultProblem")
    private String faultProblem;
    @TableField("repairType")
    private String repairType;
    @TableField("rmk")
    private String rmk2;
    @TableField("maintainer")
    private String maintainer;
    @TableField("create_By")
    private String createBy;
    @TableField("create_Date")
    private Date createDate;
    @TableField("update_By")
    private String updateBy;
    @TableField("update_Date")
    private Date updateDate;
    @TableField("enable_")
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    public String getFaultProblem() {
        return faultProblem;
    }

    public void setFaultProblem(String faultProblem) {
        this.faultProblem = faultProblem == null ? null : faultProblem.trim();
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType == null ? null : repairType.trim();
    }

    public String getRmk2() {
        return rmk2;
    }

    public void setRmk2(String rmk2) {
        this.rmk2 = rmk2;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }
}