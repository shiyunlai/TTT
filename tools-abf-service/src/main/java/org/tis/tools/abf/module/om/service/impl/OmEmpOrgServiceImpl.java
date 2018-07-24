package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmEmpOrgRequest;
import org.tis.tools.abf.module.om.dao.OmEmpOrgMapper;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmpOrgService;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.abf.module.om.service.IOmOrgService;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * omEmpOrg的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpOrgServiceImpl extends ServiceImpl<OmEmpOrgMapper, OmEmpOrg> implements IOmEmpOrgService {

    @Autowired
    private IOmEmployeeService omEmployeeService;

    @Autowired
    private IOmOrgService omOrgService;


    @Override
    public void add(OmEmpOrgRequest omEmpOrgRequest) throws OrgManagementException {

        //判断员工ID对应的员工是否存在
        OmEmployee omEmployee = omEmployeeService.selectById(omEmpOrgRequest.getGuidEmp());
        if (null == omEmployee){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_EMPLOYEE,wrap("员工ID对应的员工不存在", omEmpOrgRequest.getGuidEmp()));
        }

        //判断机构ID对应的机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmpOrgRequest.getGuidOrg());
        if (null == omOrg){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("员工ID对应的员工不存在", omEmpOrgRequest.getGuidOrg()));
        }

        OmEmpOrg omEmpOrg = new OmEmpOrg();

        //判断主机构编号是否为该机构来判断是否主机构字段
        if (omEmpOrgRequest.getGuidOrg().equals(omEmployee.getGuidOrg())){
            omEmpOrg.setIsmain(YON.YES);
        }else {
            omEmpOrg.setIsmain(YON.NO);
        }

        omEmpOrg.setGuidEmp(omEmpOrgRequest.getGuidEmp());
        omEmpOrg.setGuidOrg(omEmpOrgRequest.getGuidOrg());

        insert(omEmpOrg);

    }


    @Override
    public OmEmpOrg change(OmEmpOrgRequest omEmpOrgRequest) throws OrgManagementException {

        //判断员工ID对应的员工是否存在
        OmEmployee omEmployee = omEmployeeService.selectById(omEmpOrgRequest.getGuidEmp());
        if (null == omEmployee){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_EMPLOYEE,wrap("员工ID对应的员工不存在", omEmpOrgRequest.getGuidEmp()));
        }

        //判断机构ID对应的机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmpOrgRequest.getGuidOrg());
        if (null == omOrg){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("员工ID对应的员工不存在", omEmpOrgRequest.getGuidOrg()));
        }

        OmEmpOrg omEmpOrg = new OmEmpOrg();
        if (YON.NO.equals(omEmpOrgRequest.getIsmain())){
            omEmpOrg.setIsmain(omEmpOrgRequest.getIsmain());
        }else {
            omEmpOrg.setIsmain(omEmpOrgRequest.getIsmain());

            //将其他同员工同机构下的设置为非默认机构
            Wrapper<OmEmpOrg> wrapper = new EntityWrapper<OmEmpOrg>();
            wrapper.eq(omEmpOrg.getGuidEmp(),omEmpOrgRequest.getGuidEmp());
            wrapper.eq(omEmpOrg.getGuidOrg(),omEmpOrgRequest.getGuidOrg());

            List<OmEmpOrg> list = selectList(wrapper);
            for (OmEmpOrg omEmpOrg1 :list){
                omEmpOrg1.setIsmain(YON.NO);
                updateById(omEmpOrg1);
            }
        }

        omEmpOrg.setGuidEmp(omEmpOrgRequest.getGuidEmp());
        omEmpOrg.setGuidOrg(omEmpOrgRequest.getGuidOrg());

        updateById(omEmpOrg);
        return omEmpOrg;
    }
}

