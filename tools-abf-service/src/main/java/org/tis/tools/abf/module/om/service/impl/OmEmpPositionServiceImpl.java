package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmEmpPositionRequest;
import org.tis.tools.abf.module.om.dao.OmEmpPositionMapper;
import org.tis.tools.abf.module.om.entity.OmEmpPosition;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmpPositionService;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * omEmpPosition的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpPositionServiceImpl extends ServiceImpl<OmEmpPositionMapper, OmEmpPosition> implements IOmEmpPositionService {

    @Override
    public void add(OmEmpPositionRequest omEmpPositionRequest) throws OrgManagementException {

        String empGuid = omEmpPositionRequest.getGuidEmp();
        String positionGuid = omEmpPositionRequest.getGuidPosition();

        Wrapper<OmEmpPosition> wrapper = new EntityWrapper<OmEmpPosition>();
        wrapper.eq(OmEmpPosition.COLUMN_GUID_EMP,empGuid);
        wrapper.eq(OmEmpPosition.COLUMN_GUID_POSITION,positionGuid);

        OmEmpPosition omEmpPosition = selectOne(wrapper);
        if (omEmpPosition != null){
            throw new OrgManagementException(OMExceptionCodes.IS_EXIST_BY_CREAT_EMPPOSITION,wrap("该员工已存在于该岗位"));
        }else {
            //收集参数
            OmEmpPosition omEmpPositionAdd = new OmEmpPosition();
            omEmpPositionAdd.setGuidPosition(positionGuid);
            omEmpPositionAdd.setGuidEmp(empGuid);
            if ("".equals(omEmpPositionRequest.getIsmain())){
                omEmpPositionAdd.setIsmain(YON.NO);
            }else {
                omEmpPositionAdd.setIsmain(omEmpPositionRequest.getIsmain());
            }

            insert(omEmpPositionAdd);
        }
    }


    @Override
    public void deleteByEmpPositionId(String guidEmp, String guidPosition) throws OrgManagementException {

        Wrapper<OmEmpPosition> wrapper = new EntityWrapper<OmEmpPosition>();
        wrapper.eq(OmEmpPosition.COLUMN_GUID_EMP,guidEmp);
        wrapper.eq(OmEmpPosition.COLUMN_GUID_POSITION,guidPosition);

        OmEmpPosition omEmpPosition = selectOne(wrapper);
        if (null == omEmpPosition){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_OM_EMP_POSITION,wrap("找不到对应记录或已经被删除"));
        }

        delete(wrapper);
    }
}

