package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.vo.OperatorEmp;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeByOrgAndPositionRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.vo.OmEmployeeForPositionDetail;
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

    /**
     * 删除员工
     * @param id
     * @throws OrgManagementException
     */
    void moveEmp(String id) throws OrgManagementException;

    /**
     * 根据机构ID分页查员工
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws OrgManagementException
     */
    Page<OmEmployeeForPositionDetail> queryEmpByOrg(Page<OmEmployee> page, Wrapper<OmEmployee> wrapper, String id)throws
            OrgManagementException;

    /**
     * 根据机构id查询员工列表
     * @param id
     * @return
     * @throws OrgManagementException
     */
    List<OmEmployee> queryEmpListByOrg(String id) throws OrgManagementException;

    /**
     * 员工入职
     * @param omEmployee
     * @return
     * @throws OrgManagementException
     */
    OmEmployee onJob(OmEmployee omEmployee) throws OrgManagementException;

    /**
     * 修改员工入职
     * @param omEmployee
     * @return
     * @throws OrgManagementException
     */
    OmEmployee changeOnJob(OmEmployee omEmployee) throws OrgManagementException;

    /**
     * 员工离职
     * @param omEmployee
     * @return
     * @throws OrgManagementException
     */
    OmEmployee outJob(OmEmployee omEmployee) throws OrgManagementException;

    /**
     * 根据机构和岗位ID分页查询员工
     * @return
     * @throws OrgManagementException
     */
    Page<OmEmployee> queryByOrgPosition(Page<OmEmployee> page,String orgId, String positionId)throws OrgManagementException;

    /**
     * 查询除了该机构该岗位下的其他员工
     * @param om
     * @return
     * @throws OrgManagementException
     */
    List<OmEmployee> getOtherEmp(OmEmployeeByOrgAndPositionRequest om) throws OrgManagementException;

    /**
     * 为机构和员工添加员工
     * @param omEmployee
     * @return
     * @throws OrgManagementException
     */
    void addInOrgAndPosition(OmEmployee omEmployee)throws  OrgManagementException;


    /**
     * 根据姓名查询员工
     * @return
     * @throws OrgManagementException
     */
    Page<OperatorEmp> queryEmpByName(Page<OmEmployee> page, Wrapper<OmEmployee> wrapper ) throws
            OrgManagementException;

    /**
     * 根据机构查询已入职的员工
     * @param orgId
     * @return
     * @throws OrgManagementException
     */
    List<OmEmployee> queryEmp(String orgId)throws OrgManagementException;

}

