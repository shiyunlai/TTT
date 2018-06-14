package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmpGroupService;
import org.tis.tools.abf.module.om.dao.OmEmpGroupMapper;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;
import org.springframework.transaction.annotation.Transactional;

/**
 * omEmpGroup的Service接口实现类
 * 
 * @author ljhuan
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpGroupServiceImpl extends ServiceImpl<OmEmpGroupMapper, OmEmpGroup> implements IOmEmpGroupService {

    @Override
    public void insertEmpGroup(String groupGuid, String empGuid) throws OrgManagementException {
        OmEmpGroup oeg = new OmEmpGroup();
        oeg.setGuidEmp(empGuid);
        oeg.setGuidGroup(groupGuid);
        this.baseMapper.insert(oeg);
    }

    @Override
    public void deleteEmpGroup(String guid) {

        EntityWrapper<OmEmpGroup> omEmpGroupEntityWrapper = new EntityWrapper<>();
        omEmpGroupEntityWrapper.eq(OmEmpGroup.COLUMN_GUID,guid);
        this.baseMapper.delete(omEmpGroupEntityWrapper);
    }
}

