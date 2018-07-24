package org.tis.tools.abf.module.om.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.abf.module.om.entity.enums.OmGender;
import org.tis.tools.abf.module.om.entity.enums.OmPaperType;
import org.tis.tools.abf.module.om.entity.enums.OmZipCode;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/13 15:28
 */
public class OmEmployeeForPositionDetail implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String guid;

    private String empCode;

    private String empName;

    private String empRealname;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmGender gender;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmEmployeeStatus empstatus;

    private String guidOrg;

    private String guidPosition;

    private String guidEmpMajor;

    private Date indate;

    private Date outdate;

    private String mobileno;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPaperType paperType;

    private String paperNo;

    private Date birthdate;

    private String htel;

    private String haddress;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmZipCode hzipcode;

    private String guidOperator;

    private String userId;

    private String remark;

    private String PositionName;

    public OmEmployeeForPositionDetail(){}

    public OmEmployeeForPositionDetail(String guid, String empCode, String empName, String empRealname, OmGender gender, OmEmployeeStatus empstatus, String guidOrg, String guidPosition, String guidEmpMajor, Date indate, Date outdate, String mobileno, OmPaperType paperType, String paperNo, Date birthdate, String htel, String haddress, OmZipCode hzipcode, String guidOperator, String userId, String remark, String PositionName){
        this.guid = guid;
        this.empCode = empCode;
        this.empName = empName;
        this.empRealname = empRealname;
        this.gender = gender;
        this.empstatus = empstatus;
        this.guidOrg = guidOrg;
        this.guidPosition = guidPosition;
        this.guidEmpMajor = guidEmpMajor;
        this.indate = indate;
        this.outdate = outdate;
        this.mobileno = mobileno;
        this.paperType = paperType;
        this.paperNo = paperNo;
        this.birthdate = birthdate;
        this.htel = htel;
        this.haddress = haddress;
        this.hzipcode = hzipcode;
        this.guidOperator = guidOperator;
        this.userId = userId;
        this.remark = remark;
        this.PositionName = PositionName;
    }

        public OmEmployeeForPositionDetail(OmEmployee omEmployee , String PositionName){
        this.guid = omEmployee.getGuid();
        this.empCode = omEmployee.getEmpCode();
        this.empName = omEmployee.getEmpName();
        this.empRealname = omEmployee.getEmpRealname();
        this.gender = omEmployee.getGender();
        this.empstatus = omEmployee.getEmpstatus();
        this.guidOrg = omEmployee.getGuidOrg();
        this.guidPosition = omEmployee.getGuidPosition();
        this.guidEmpMajor = omEmployee.getGuidEmpMajor();
        this.indate = omEmployee.getIndate();
        this.outdate = omEmployee.getOutdate();
        this.mobileno = omEmployee.getMobileno();
        this.paperType = omEmployee.getPaperType();
        this.paperNo = omEmployee.getPaperNo();
        this.birthdate = omEmployee.getBirthdate();
        this.htel = omEmployee.getHtel();
        this.haddress = omEmployee.getHaddress();
        this.hzipcode = omEmployee.getHzipcode();
        this.guidOperator = omEmployee.getGuidOperator();
        this.userId = omEmployee.getUserId();
        this.remark = omEmployee.getRemark();
        this.PositionName = PositionName;
    }

    //get和set方法
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpRealname() {
        return empRealname;
    }

    public void setEmpRealname(String empRealname) {
        this.empRealname = empRealname;
    }

    public OmGender getGender() {
        return gender;
    }

    public void setGender(OmGender gender) {
        this.gender = gender;
    }

    public OmEmployeeStatus getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(OmEmployeeStatus empstatus) {
        this.empstatus = empstatus;
    }

    public String getGuidOrg() {
        return guidOrg;
    }

    public void setGuidOrg(String guidOrg) {
        this.guidOrg = guidOrg;
    }

    public String getGuidPosition() {
        return guidPosition;
    }

    public void setGuidPosition(String guidPosition) {
        this.guidPosition = guidPosition;
    }

    public String getGuidEmpMajor() {
        return guidEmpMajor;
    }

    public void setGuidEmpMajor(String guidEmpMajor) {
        this.guidEmpMajor = guidEmpMajor;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public OmPaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(OmPaperType paperType) {
        this.paperType = paperType;
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getHtel() {
        return htel;
    }

    public void setHtel(String htel) {
        this.htel = htel;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public OmZipCode getHzipcode() {
        return hzipcode;
    }

    public void setHzipcode(OmZipCode hzipcode) {
        this.hzipcode = hzipcode;
    }

    public String getGuidOperator() {
        return guidOperator;
    }

    public void setGuidOperator(String guidOperator) {
        this.guidOperator = guidOperator;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
