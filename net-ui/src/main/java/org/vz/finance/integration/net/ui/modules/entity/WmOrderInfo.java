package org.vz.finance.integration.net.ui.modules.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.vz.finance.integration.net.ui.core.utils.BaseModel;

import java.io.Serializable;
import java.util.Date;

@TableName("wm_order_info")
public class WmOrderInfo extends BaseModel<WmOrderInfo> implements Serializable {
    @TableId("ID")
    private String id;

    @TableField("happen_Date")
    private Date happenDate;
    @TableField("machine_Model")
    private String machineModel;
    @TableField("part")
    private String part;
    @TableField("num")
    private Integer num;
    @TableField("name")
    private String name;
    @TableField("fixed_Telephone")
    private String fixedTelephone;
    @TableField("mobile_Phone")
    private String mobilePhone;
    @TableField("address")
    private String address;
    @TableField("express")
    private String express;
    @TableField("courier_Number")
    private String courierNumber;
    @TableField("sn")
    private String sn;
    @TableField("bad_Problems")
    private String badProblems;
    @TableField("parts")
    private String parts;
    @TableField("create_By")
    private String createBy;
    @TableField("create_Date")
    private Date createDate;
    @TableField("update_By")
    private String updateBy;
    @TableField("update_Date")
    private Date updateDate;
    @TableField("color")
    private String color;
    @TableField("appearance")
    private String appearance;
    @TableField("delivery_date")
    private Date deliveryDate;
    @TableField("delivery_Sign")
    private String deliverySign;
    @TableField("enable_")
    private String enable;
    @TableField("RMK")
    private String rmk;
    @TableField(exist = false)
    private Integer currentPage = 1;
    @TableField(exist = false)
    private Integer pageSize = 10;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel == null ? null : machineModel.trim();
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part == null ? null : part.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFixedTelephone() {
        return fixedTelephone;
    }

    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone == null ? null : fixedTelephone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getBadProblems() {
        return badProblems;
    }

    public void setBadProblems(String badProblems) {
        this.badProblems = badProblems == null ? null : badProblems.trim();
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts == null ? null : parts.trim();
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliverySign() {
        return deliverySign;
    }

    public void setDeliverySign(String deliverySign) {
        this.deliverySign = deliverySign;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}