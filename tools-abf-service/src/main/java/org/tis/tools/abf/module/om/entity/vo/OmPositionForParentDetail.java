package org.tis.tools.abf.module.om.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.enums.OmPositionType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/12 20:35
 */
public class OmPositionForParentDetail implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String guid;

    private String guidOrg;

    private String positionCode;

    private String positionName;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionType positionType;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionStatus positionStatus;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON isleaf;

    private BigDecimal subCount;

    private BigDecimal positionLevel;

    private String positionSeq;

    private String guidParents;

    private Date startDate;

    private Date endDate;

    private String parentName;

    private int employeeCount;

    public OmPositionForParentDetail(){}

    public OmPositionForParentDetail(OmPosition omPosition,String parentName ,int employeeCount){
        this.guid= omPosition.getGuid();
        this.guidOrg= omPosition.getGuidOrg();
        this.positionCode= omPosition.getPositionCode();
        this.positionName= omPosition.getPositionName();
        this.positionType= omPosition.getPositionType();
        this.positionStatus= omPosition.getPositionStatus();
        this.isleaf= omPosition.getIsleaf();
        this.subCount= omPosition.getSubCount();
        this.positionLevel= omPosition.getPositionLevel();
        this.positionSeq= omPosition.getPositionSeq();
        this.guidParents= omPosition.getGuidParents();
        this.startDate= omPosition.getStartDate();
        this.endDate= omPosition.getEndDate();
        this.parentName = parentName;
        this.employeeCount = employeeCount;
    }

    //set和get方法


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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
