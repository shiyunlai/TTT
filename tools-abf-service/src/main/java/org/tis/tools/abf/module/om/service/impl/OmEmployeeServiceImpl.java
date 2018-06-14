package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmpOrgService;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.abf.module.om.dao.OmEmployeeMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private IOmEmpOrgService empOrgService;

    @Override
    public List<OmEmployee> queryEmployeeByGuid(String orgGuid) {
        EntityWrapper<OmEmpOrg> omEmpOrgEntityWrapper = new EntityWrapper<>();
        omEmpOrgEntityWrapper.eq(OmEmpOrg.COLUMN_GUID_ORG, orgGuid);
        List<OmEmpOrg> list = empOrgService.selectList(omEmpOrgEntityWrapper);
        if(list.isEmpty()){
            return null;
        }else{
            List<String> list2 = new ArrayList<>();
            for (OmEmpOrg oeo : list) {
                list2.add(oeo.getGuidEmp());
            }
            EntityWrapper<OmEmployee> omEmployeeEntityWrapper = new EntityWrapper<>();
            omEmployeeEntityWrapper.in(OmEmployee.COLUMN_GUID, list2);
            List<OmEmployee> omEmployeeList = this.baseMapper.selectList(omEmployeeEntityWrapper);
            return omEmployeeList;
        }
    }

    @Override
    public boolean add(OmEmployeeAddRequest omEmployeeAddRequest) throws OrgManagementException {

        boolean isexist = new Boolean(false);

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        List<OmEmployee> list = selectList(wrapper);

        for (OmEmployee omEmployee : list) {
            if (omEmployee.getEmpCode().equals(omEmployeeAddRequest.getEmpCode())) {
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
}

