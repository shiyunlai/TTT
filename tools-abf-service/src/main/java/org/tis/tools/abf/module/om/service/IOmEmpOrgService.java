package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.controller.request.OmEmpOrgRequest;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

/**
 * omEmpOrg的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmEmpOrgService extends IService<OmEmpOrg>  {

    /***
     * 新增员工隶属机构关系表
     * @param omEmpOrgRequest
     * @throws OrgManagementException
     */
    void add(OmEmpOrgRequest omEmpOrgRequest) throws OrgManagementException;


    /**
     * 修改员工隶属机构关系表
     * @param omEmpOrgRequest
     * @return
     * @throws OrgManagementException
     */
    OmEmpOrg change(OmEmpOrgRequest omEmpOrgRequest) throws OrgManagementException;

}

