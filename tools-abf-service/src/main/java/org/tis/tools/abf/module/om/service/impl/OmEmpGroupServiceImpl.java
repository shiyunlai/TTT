package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.om.entity.OmGroup;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmpGroupService;
import org.tis.tools.abf.module.om.dao.OmEmpGroupMapper;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.om.service.IOmGroupService;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * omEmpGroup的Service接口实现类
 * 
 * @author ljhuan
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpGroupServiceImpl extends ServiceImpl<OmEmpGroupMapper, OmEmpGroup> implements IOmEmpGroupService {

    @Autowired
    IOmGroupService omGroupService;

    @Override
    public void insertEmpGroup(String groupCode, String empGuid) throws OrgManagementException {
        OmGroup omGroup = omGroupService.selectGroupByCode(groupCode);
        OmEmpGroup oeg = new OmEmpGroup();
        oeg.setGuidEmp(empGuid);
        oeg.setGuidGroup(omGroup.getGuid());
        this.baseMapper.insert(oeg);
    }

    @Override
    public void deleteEmpGroup(String guid) {

        EntityWrapper<OmEmpGroup> omEmpGroupEntityWrapper = new EntityWrapper<>();
        omEmpGroupEntityWrapper.eq(OmEmpGroup.COLUMN_GUID,guid);
        this.baseMapper.delete(omEmpGroupEntityWrapper);
    }
}

