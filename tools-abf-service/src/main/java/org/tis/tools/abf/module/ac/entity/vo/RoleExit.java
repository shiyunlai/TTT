package org.tis.tools.abf.module.ac.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/8/2 11:12
 */
@Data
public class RoleExit {

    private String guid;

    public String roleCode;

    private String roleName;

    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    public YON enabled;

    private String roleDesc;

    private String roleGroup;

    private Date createtime;

    private Date lastupdate;

    private String updator;

    private String dataStatus;

    private String exit;

    public RoleExit(){}

    public RoleExit(AcRole acRole ,String exit){
        this.guid = acRole.getGuid() ;
        this.roleCode = acRole.getRoleCode();
        this.roleName = acRole.getRoleName() ;
        this.enabled = acRole.getEnabled();
        this.roleDesc = acRole.getRoleDesc() ;
        this.roleGroup = acRole.getRoleGroup() ;
        this.createtime = acRole.getCreatetime() ;
        this.lastupdate = acRole.getLastupdate() ;
        this.updator = acRole.getUpdator() ;
        this.dataStatus = acRole.getDataStatus() ;
        this.exit = exit ;
    }
}
