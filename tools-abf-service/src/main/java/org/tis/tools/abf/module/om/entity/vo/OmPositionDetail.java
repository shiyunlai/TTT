package org.tis.tools.abf.module.om.entity.vo;

import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.enums.OmPositionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/6/5 21:27
 */
public class OmPositionDetail implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String guid;

    private String guidOrg;

    private String positionCode;

    private String positionName;

    private OmPositionType positionType;

    private OmPositionStatus positionStatus;

    private YON isleaf;

    private BigDecimal subCount;

    private BigDecimal positionLevel;

    private String positionSeq;

    private String guidParents;

    private Date startDate;

    private Date endDate;

    private List<OmPosition> children = new ArrayList<OmPosition>();


    public  OmPositionDetail(){}

    public OmPositionDetail(String guid, String guidOrg,String positionCode, String positionName,OmPositionType positionType,OmPositionStatus positionStatus,YON isleaf,BigDecimal subCount,BigDecimal positionLevel,String positionSeq,String guidParents,Date startDate,Date endDate,List<OmPosition> children){
            this.guid = guid;
            this.guidOrg = guidOrg;
            this.positionCode = positionCode;
            this.positionName = positionName;
            this.positionType = positionType;
            this.positionStatus = positionStatus;
            this.isleaf = isleaf;
            this.subCount = subCount;
            this.positionLevel = positionLevel;
            this.positionSeq = positionSeq;
            this.guidParents = guidParents;
            this.startDate = startDate;
            this.endDate = endDate;
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

    public String getGuidOrg() {
        return guidOrg;
    }

    public void setGuidOrg(String guidOrg) {
        this.guidOrg = guidOrg;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public OmPositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(OmPositionType positionType) {
        this.positionType = positionType;
    }

    public OmPositionStatus getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(OmPositionStatus positionStatus) {
        this.positionStatus = positionStatus;
    }

    public YON getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(YON isleaf) {
        this.isleaf = isleaf;
    }

    public BigDecimal getSubCount() {
        return subCount;
    }

    public void setSubCount(BigDecimal subCount) {
        this.subCount = subCount;
    }

    public BigDecimal getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(BigDecimal positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getPositionSeq() {
        return positionSeq;
    }

    public void setPositionSeq(String positionSeq) {
        this.positionSeq = positionSeq;
    }

    public String getGuidParents() {
        return guidParents;
    }

    public void setGuidParents(String guidParents) {
        this.guidParents = guidParents;
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

    public List<OmPosition> getChildren() {
        return children;
    }

    public void setChildren(List<OmPosition> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
