package org.vz.finance.integration.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author jaden
 * @since 2018-05-03
 */
@TableName("SYS_MANAGER_LOG")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志表id
     */
    @TableId("ID_")
	private String id;
    /**
     * 用户名
     */
	@TableField("USERNAME")
	private String username;

    /**
     * 请求参数
     */
	@TableField("PARAMS")
	private String params;
    /**
     * ip地址
     */
	@TableField("IP")
	private String ip;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;
    /**
	 * 备注
	 */
	@TableField("REMARK_")
	private String remark;

	/**
	 * 操作类型
	 */
	@TableField("OPERATE_TYPE")
	private String operateType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
}
