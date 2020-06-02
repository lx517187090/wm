package org.vz.finance.integration.net.ui.modules.vo.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: lixi
 * @Despriction:
 * @Date: Created in : 2019/11/12 0012
 */
public class BankBranchOrgPageDto implements Serializable {

    private String id;

    /**
     * 一级目录名称
     */
    private String firstName;

    /**
     * 一级目录编码
     */
    private String firstCode;

    /**
     * 二级目录名称
     */
    private String secondName;

    /**
     * 二级目录编码
     */
    private String secondCode;

    /**
     * 三级目录名称
     */
    private String thirdName;

    /**
     * 三级目录编码
     */
    private String thirdCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 产品id
     */
    private String productId;

    private List<BankBranchOrgPageDto> bankBranchOrgPageDtoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<BankBranchOrgPageDto> getBankBranchOrgPageDtoList() {
        return bankBranchOrgPageDtoList;
    }

    public void setBankBranchOrgPageDtoList(List<BankBranchOrgPageDto> bankBranchOrgPageDtoList) {
        this.bankBranchOrgPageDtoList = bankBranchOrgPageDtoList;
    }
}
