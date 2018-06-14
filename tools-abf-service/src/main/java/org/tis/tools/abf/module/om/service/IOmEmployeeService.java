package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

import java.util.List;

/**
 * omEmployee的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmEmployeeService extends IService<OmEmployee>  {


    public List<OmEmployee> queryEmployeeByGuid(String orgGuid);
    /**
     * 新增员工
     * @param omEmployeeAddRequest
     * @throws OrgManagementException
     */
    boolean add(OmEmployeeAddRequest omEmployeeAddRequest) throws OrgManagementException;

    /**
     * 修改员工
     * @param omEmployeeUpdateRequest
     * @return
     * @throws OrgManagementException
     */
    boolean change(OmEmployeeUpdateRequest omEmployeeUpdateRequest) throws OrgManagementException;

}

