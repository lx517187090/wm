package org.vz.finance.integration.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jaden
 * @since 2018-05-03
 */
@TableName("SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆名称
     */
	@TableField("LOGIN_NAME")
	private String loginName;
    /**
     * 最近登录时间
     */
	@TableField("LAST_LOGIN_TIME")
	private Date lastLoginTime;
    /**
     * 最近登陆ip
     */
	@TableField("LAST_LOGIN_IP")
	private String lastLoginIp;
    /**
     * 用户id
     */
    @TableId("ID_")
	private String id;
    /**
     * 部门id
     */
	@TableField("DEPT_ID")
	private String deptId;
	
	@TableField(exist=false)
	private String deptName;
	
    /**
     * 用户名称
     */
	@TableField("USERNAME")
	@NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "长度在 {min} 到 {max} 个字符")
    private String username;
    /**
     * 用户密码
     */
	@TableField("PASSWD")
    @NotBlank(message = "用户密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度必须在{min}和{max}之间")
	private String passwd;
    /**
     * 用户手机号
     */
	@TableField("MOBILE")
    @NotBlank(message = "用户手机号码不能为空")
    @Pattern(regexp = "1[3|4|5|7|8]\\d{9}", message = "用户手机号格式不正确！")
	private String mobile;
    /**
     * 用户邮箱
     */
	@TableField("EMAIL")
    @Email(message = "用户邮箱格式不正确")
    @NotBlank(message = "用户邮箱不能为空")
	private String email;
    /**
     * 状态 0：禁用  1：正常
     */
	@TableField("STATUS")
    @NotNull(message = "状态不能为空")
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
	
	@TableField(exist=false)
    @NotEmpty(message = "请至少选择一个角色")
	private List<String> roleIdList;
	
	@TableField(exist=false)
	private String secretKey;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
