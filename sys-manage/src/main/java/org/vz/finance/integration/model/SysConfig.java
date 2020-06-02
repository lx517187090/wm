package org.vz.finance.integration.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author jaden
 * @since 2018-05-03
 */
@TableName("SYS_CONFIG")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置信息表id
     */
    @TableId("ID_")
    private String id;
    /**
     * key
     */
    @TableField("KEY")
    private String key;
    /**
     * value
     */
    @TableField("VALUE")
    private String value;
    /**
     * 状态 0：隐藏 1：显示
     */
    @TableField("STATUS")
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("UPDATE_BY")
    private String updateBy;
    /**
     * 备注
     */
    @TableField("REMARK_")
    private String remark;

    /**
     * 配置名
     */
    @TableField("NAME")
    private String name;

    /**
     * 是否有效：0 无效 1有效
     */
    @TableField("ENABLED_")
    private String enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Config{" + "id=" + id + ", key=" + key + ", value=" + value + ", status=" + status + ", createTime="
                + createTime + ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy
                + ", remark=" + remark + ", name=" + name + ", enabled=" + enabled + "}";
    }
}
