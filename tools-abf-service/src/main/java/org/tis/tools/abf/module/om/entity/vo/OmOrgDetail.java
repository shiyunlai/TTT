package org.tis.tools.abf.module.om.entity.vo;

import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.enums.OmOrgArea;
import org.tis.tools.abf.module.om.entity.enums.OmOrgDegree;
import org.tis.tools.abf.module.om.entity.enums.OmOrgStatus;
import org.tis.tools.abf.module.om.entity.enums.OmOrgType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/6/5 15:27
 */
public class OmOrgDetail implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String guid;

    private String orgCode;

    private String orgName;

    private OmOrgType orgType;

    private OmOrgDegree orgDegree;

    private OmOrgStatus orgStatus ;

    private String guidParents;

    private String orgSeq;

    private String orgAddr;

    private String linkMan;

    private String linkTel;

    private Date startDate;

    private Date endDate;

    private OmOrgArea area;

    private BigDecimal sortNo;

    private YON isleaf ;

    private String remark;

    private List<OmOrg> children = new ArrayList<OmOrg>();

    public OmOrgDetail(){}

    public OmOrgDetail(String guid,String orgCode,String orgName,OmOrgType orgType,OmOrgDegree orgDegree,OmOrgStatus orgStatus,String guidParents,String orgSeq,String orgAddr,String linkMan,String linkTel,Date startDate,Date endDate,OmOrgArea area,BigDecimal sortNo,YON isleaf,String remark,List<OmOrg> children){
            this.guid = guid;
            this.orgCode = orgCode;
            this.orgName = orgName;
            this.orgType = orgType;
            this.orgDegree = orgDegree;
            this.orgStatus = orgStatus;
            this.guidParents = guidParents;
            this.orgSeq = orgSeq;
            this.orgAddr = orgAddr;
            this.linkMan = linkMan;
            this.linkTel = linkTel;
            this.startDate = startDate;
            this.endDate = endDate;
            this.area = area;
            this.sortNo = sortNo;
            this.isleaf = isleaf;
            this.remark = remark;
            this.children = children;
    }

    //get和set方法

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGuidParents() {
        return guidParents;
    }

    public void setGuidParents(String guidParents) {
        this.guidParents = guidParents;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OmOrg> getChildren() {
        return children;
    }

    public void setChildren(List<OmOrg> children) {
        this.children = children;
    }

    public OmOrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OmOrgType orgType) {
        this.orgType = orgType;
    }

    public OmOrgDegree getOrgDegree() {
        return orgDegree;
    }

    public void setOrgDegree(OmOrgDegree orgDegree) {
        this.orgDegree = orgDegree;
    }

    public OmOrgStatus getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(OmOrgStatus orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public OmOrgArea getArea() {
        return area;
    }

    public void setArea(OmOrgArea area) {
        this.area = area;
    }

    public YON getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(YON isleaf) {
        this.isleaf = isleaf;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
