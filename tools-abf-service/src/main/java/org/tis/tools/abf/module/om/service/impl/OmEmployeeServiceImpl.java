package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.dao.OmEmployeeMapper;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.abf.module.om.service.IOmOrgService;
import org.tis.tools.abf.module.om.service.IOmPositionService;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;


/**
 * omEmployee的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmployeeServiceImpl extends ServiceImpl<OmEmployeeMapper, OmEmployee> implements IOmEmployeeService {

    @Autowired
    private IOmOrgService omOrgService;

    @Autowired
    private IOmPositionService omPositionService;

    @Autowired
    private IAcOperatorService operatorService;

    @Override
    public boolean add(OmEmployeeAddRequest omEmployeeAddRequest) throws OrgManagementException {

        //判断机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmployeeAddRequest.getGuidOrg());
        if (omOrg == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("组织GUID对应的组织不存在",omEmployeeAddRequest.getGuidOrg()));
        }

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omEmployeeAddRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_POSITION,wrap("岗位GUID对应的岗位不存在",omEmployeeAddRequest.getGuidPosition()));
        }


        //如果操作员ID存在,判断操作员是否存在
        if (!"".equals(omEmployeeAddRequest.getGuidOperator())){
            AcOperator acOperator = operatorService.selectById(omEmployeeAddRequest.getGuidOperator());
            if (acOperator == null){
                throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("应用GUID对应的应用不存在",omEmployeeAddRequest.getGuidOperator()));
            }
        }



        boolean isexist = new Boolean(false);

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        List<OmEmployee> list = selectList(wrapper);

        for (OmEmployee omEmployee : list){
            if (omEmployee.getEmpCode().equals(omEmployeeAddRequest.getEmpCode())){
                return isexist;
            }
        }

        isexist = true;

         OmEmployee omEmployee = new OmEmployee();

         //新建人员后停留在在招状态
        omEmployee.setEmpstatus(OmEmployeeStatus.OFFER);


        //收集参数
        omEmployee.setEmpCode(omEmployeeAddRequest.getEmpCode());
        omEmployee.setEmpName(omEmployeeAddRequest.getEmpName());
        omEmployee.setEmpRealname(omEmployeeAddRequest.getEmpRealname());
        omEmployee.setGender(omEmployeeAddRequest.getGender());
        omEmployee.setGuidOrg(omEmployeeAddRequest.getGuidOrg());
        omEmployee.setGuidOperator(omEmployeeAddRequest.getGuidOperator());
        omEmployee.setGuidPosition(omEmployeeAddRequest.getGuidPosition());
        omEmployee.setGuidEmpMajor(omEmployeeAddRequest.getGuidEmpMajor());
        omEmployee.setIndate(omEmployeeAddRequest.getIndate());
        omEmployee.setOutdate(omEmployeeAddRequest.getOutdate());
        omEmployee.setMobileno(omEmployeeAddRequest.getMobileno());
        omEmployee.setPaperType(omEmployeeAddRequest.getPaperType());
        omEmployee.setPaperNo(omEmployeeAddRequest.getPaperNo());
        omEmployee.setBirthdate(omEmployeeAddRequest.getBirthdate());
        omEmployee.setHtel(omEmployeeAddRequest.getHtel());
        omEmployee.setHaddress(omEmployeeAddRequest.getHaddress());
        omEmployee.setHzipcode(omEmployeeAddRequest.getHzipcode());
        omEmployee.setUserId(omEmployeeAddRequest.getUserId());
        omEmployee.setRemark(omEmployeeAddRequest.getRemark());

        insert(omEmployee);

        return isexist;
    }

    @Override
    public boolean change(OmEmployeeUpdateRequest omEmployeeUpdateRequest) throws OrgManagementException {

        //判断机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmployeeUpdateRequest.getGuidOrg());
        if (omOrg == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("组织GUID对应的组织不存在",omEmployeeUpdateRequest
                    .getGuidOrg()));
        }

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omEmployeeUpdateRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_POSITION,wrap("岗位GUID对应的岗位不存在",omEmployeeUpdateRequest
                    .getGuidPosition()));
        }


        //如果操作员ID存在,判断操作员是否存在
        if (!"".equals(omEmployeeUpdateRequest.getGuidOperator())){
            AcOperator acOperator = operatorService.selectById(omEmployeeUpdateRequest.getGuidOperator());
            if (acOperator == null){
                throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("应用GUID对应的应用不存在",omEmployeeUpdateRequest.getGuidOperator()));
            }
        }

        boolean isexist = new Boolean(false);

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        List<OmEmployee> list = selectList(wrapper);

        for (OmEmployee omEmployee : list){
            if (omEmployee.getEmpCode().equals(omEmployeeUpdateRequest.getEmpCode()) && !(omEmployee.getGuid().equals(omEmployeeUpdateRequest.getGuid()))){
                return isexist;
            }
        }

        isexist = true;

        OmEmployee omEmployee = new OmEmployee();

        //收集参数
        omEmployee.setGuid(omEmployeeUpdateRequest.getGuid());
        omEmployee.setEmpCode(omEmployeeUpdateRequest.getEmpCode());
        omEmployee.setEmpName(omEmployeeUpdateRequest.getEmpName());
        omEmployee.setEmpRealname(omEmployeeUpdateRequest.getEmpRealname());
        omEmployee.setGender(omEmployeeUpdateRequest.getGender());
        omEmployee.setEmpstatus(omEmployeeUpdateRequest.getEmpstatus());
        omEmployee.setGuidOrg(omEmployeeUpdateRequest.getGuidOrg());
        omEmployee.setGuidOperator(omEmployeeUpdateRequest.getGuidOperator());
        omEmployee.setGuidPosition(omEmployeeUpdateRequest.getGuidPosition());
        omEmployee.setGuidEmpMajor(omEmployeeUpdateRequest.getGuidEmpMajor());
        omEmployee.setIndate(omEmployeeUpdateRequest.getIndate());
        omEmployee.setOutdate(omEmployeeUpdateRequest.getOutdate());
        omEmployee.setMobileno(omEmployeeUpdateRequest.getMobileno());
        omEmployee.setPaperType(omEmployeeUpdateRequest.getPaperType());
        omEmployee.setPaperNo(omEmployeeUpdateRequest.getPaperNo());
        omEmployee.setBirthdate(omEmployeeUpdateRequest.getBirthdate());
        omEmployee.setHtel(omEmployeeUpdateRequest.getHtel());
        omEmployee.setHaddress(omEmployeeUpdateRequest.getHaddress());
        omEmployee.setHzipcode(omEmployeeUpdateRequest.getHzipcode());
        omEmployee.setUserId(omEmployeeUpdateRequest.getUserId());
        omEmployee.setRemark(omEmployeeUpdateRequest.getRemark());

        updateById(omEmployee);
        return isexist;
    }


    @Override
    public Page<OmEmployee> queryEmpByOrg(Page<OmEmployee> page, Wrapper<OmEmployee> wrapper, String id) throws OrgManagementException {

        if (wrapper == null){
            wrapper = new EntityWrapper<OmEmployee>();
        }

        //分页查询机构ID为"id"的员工
        wrapper.eq(OmEmployee.COLUMN_GUID_ORG,id);
        Page<OmEmployee> pageQuery = selectPage(page,wrapper);

        return pageQuery;
    }


    @Override
    public List<OmEmployee> queryEmpListByOrg(String id) throws OrgManagementException {

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        wrapper.eq(OmEmployee.COLUMN_GUID_ORG,id);

        List<OmEmployee> list = selectList(wrapper);
        return list;
    }
}

