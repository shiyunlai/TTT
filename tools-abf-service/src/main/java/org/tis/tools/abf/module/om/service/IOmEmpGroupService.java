package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

/**
 * omEmpGroup的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmEmpGroupService extends IService<OmEmpGroup>  {


    /**
     * 添加人员-工作组关系表数据
     */
    void insertGroupEmp(String groupGuid,String empGuid) throws OrgManagementException;

    /**
     * 删除人员-工作组关系表数据
     */
    void deleteGroupEmp(String guid);
}

