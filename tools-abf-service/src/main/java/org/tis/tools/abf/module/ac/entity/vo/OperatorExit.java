package org.tis.tools.abf.module.ac.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.enums.AuthMode;
import org.tis.tools.abf.module.ac.entity.enums.OperatorStatus;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/8/2 11:12
 */
@Data
public class OperatorExit {

    private String guid;

    private String userId;

    private String password;

    private String operatorName;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OperatorStatus operatorStatus;

    private Date invalDate;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private AuthMode authMode;

    private BigDecimal lockLimit;

    private BigDecimal errCount;

    private Date lockTime;

    private Date unlockTime;

    private Date lastLogin;

    private Date startDate;

    private Date endDate;

    private String validTime;

    private String macCode;

    private String ipAddress;

    private Date createtime;

    private Date lastupdate;

    private String updator;

    private String dataStatus;

    private String exit;

    public OperatorExit(){}

    public OperatorExit(AcOperator acOperator , String exit){
        this.guid = acOperator.getGuid() ;
        this.userId = acOperator.getUserId() ;
        this.password = acOperator.getPassword() ;
        this.operatorName = acOperator.getOperatorName() ;
        this.operatorStatus = acOperator.getOperatorStatus() ;
        this.invalDate = acOperator.getInvalDate() ;
        this.authMode = acOperator.getAuthMode() ;
        this.lockLimit = acOperator.getLockLimit() ;
        this.errCount = acOperator.getErrCount() ;
        this.lockTime = acOperator.getLockTime() ;
        this.unlockTime = acOperator.getUnlockTime() ;
        this.lastLogin = acOperator.getLastLogin() ;
        this.startDate = acOperator.getStartDate() ;
        this.endDate = acOperator.getEndDate() ;
        this.validTime = acOperator.getValidTime() ;
        this.macCode = acOperator.getMacCode() ;
        this.ipAddress = acOperator.getIpAddress() ;
        this.createtime = acOperator.getCreatetime() ;
        this.lastupdate = acOperator.getLastupdate() ;
        this.updator = acOperator.getUpdator() ;
        this.dataStatus = acOperator.getDataStatus() ;
        this.exit = exit ;
    }

}
