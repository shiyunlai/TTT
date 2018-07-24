package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityresRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorIdentityresMapper;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentityres;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.ac.entity.enums.AcResourceType;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityService;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityresService;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.abf.module.om.entity.OmGroup;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.service.IOmGroupService;
import org.tis.tools.abf.module.om.service.IOmOrgService;
import org.tis.tools.abf.module.om.service.IOmPositionService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorIdentityres的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorIdentityresServiceImpl extends ServiceImpl<AcOperatorIdentityresMapper, AcOperatorIdentityres> implements IAcOperatorIdentityresService {

    @Autowired
    private IAcOperatorIdentityService acOperatorIdentityService;

    @Autowired
    private IAcFuncService acFuncService;

    @Autowired
    private IAcRoleService acRoleService;

    @Autowired
    private IOmPositionService omPositionService;

    @Autowired
    private IOmOrgService omOrgService;

    @Autowired
    private IOmGroupService omGroupService;


    @Override
    public void add(AcOperatorIdentityresRequest acOperatorIdentityres) throws AcManagementException {

        //判断需要新增的对象是否已经存在
        Wrapper<AcOperatorIdentityres> wrapper = new EntityWrapper<AcOperatorIdentityres>();
        wrapper.eq(AcOperatorIdentityres.COLUMN_GUID_IDENTITY,acOperatorIdentityres.getGuidIdentity())
                .eq(AcOperatorIdentityres.COLUMN_AC_RESOURCETYPE,acOperatorIdentityres.getAcResourcetype())
                .eq(AcOperatorIdentityres.COLUMN_GUID_AC_RESOURCE,acOperatorIdentityres.getGuidAcResource());
        if (null != selectOne(wrapper)){
            throw new AcManagementException(ExceptionCodes.OBJECT_IS_ALREADY_EXIST,wrap("该对象已存在,不需要新增"));
        }

        //判断身份guid对应的身份是否存在
        String guidIdentity = acOperatorIdentityres.getGuidIdentity();
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(guidIdentity);
        if (null == acOperatorIdentity){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR_IDENTITYRES,wrap("查询操作员身份权限失败"),guidIdentity);
        }

        AcResourceType acResourceType = acOperatorIdentityres.getAcResourcetype();
        String guidAcResource = acOperatorIdentityres.getGuidAcResource();

        //判断资源guid对应的资源对象是否存在
        judgeIdentity(acResourceType,guidAcResource);

        AcOperatorIdentityres acOperatorIdentityresAdd = new AcOperatorIdentityres();

        acOperatorIdentityresAdd.setAcResourcetype(acResourceType);

        acOperatorIdentityresAdd.setGuidAcResource(guidAcResource);
        acOperatorIdentityresAdd.setGuidIdentity(guidIdentity);

        insert(acOperatorIdentityresAdd);
    }


    @Override
    public AcOperatorIdentityres change(AcOperatorIdentityresRequest acOperatorIdentityres) throws AcManagementException {

        //判断身份guid对应的身份是否存在
        String guidIdentity = acOperatorIdentityres.getGuidIdentity();
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(guidIdentity);
        if (null == acOperatorIdentity){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR_IDENTITYRES,wrap("查询操作员身份权限失败"),guidIdentity);
        }

        AcResourceType acResourceType = acOperatorIdentityres.getAcResourcetype();
        String guidAcResource = acOperatorIdentityres.getGuidAcResource();

        //判断资源guid对应的资源对象是否存在
        judgeIdentity(acResourceType,guidAcResource);

        AcOperatorIdentityres acOperatorIdentityresUpdate = new AcOperatorIdentityres();

        acOperatorIdentityresUpdate.setGuid(acOperatorIdentityres.getGuid());
        acOperatorIdentityresUpdate.setAcResourcetype(acResourceType);
        acOperatorIdentityresUpdate.setGuidAcResource(guidAcResource);
        acOperatorIdentityresUpdate.setGuidIdentity(guidIdentity);

        updateById(acOperatorIdentityresUpdate);
        return null;
    }


    @Override
    public Page<AcOperatorIdentityres> queryByIdentity(Page<AcOperatorIdentityres> page, Wrapper<AcOperatorIdentityres> wrapper, String id) throws AcManagementException {

        if (null == wrapper){
            wrapper = new EntityWrapper<AcOperatorIdentityres>();
        }

        wrapper.eq(AcOperatorIdentityres.COLUMN_GUID_IDENTITY,id);

        Page<AcOperatorIdentityres> pageQuery = selectPage(page,wrapper);

        return pageQuery;
    }

    /**
     * 判断资源guid对应的资源对象是否存在
     */
    private void judgeIdentity(AcResourceType acResourceType,String guidAcResource)throws AcManagementException{
        switch (acResourceType){
            case FUNCTION:
                AcFunc acFunc = acFuncService.selectById(guidAcResource);
                if (null == acFunc){
                    throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("资源GUID对应的功能查询失败",guidAcResource));
                }
                break;
            case ROLE:
                AcRole acRole = acRoleService.selectById(guidAcResource);
                if (null == acRole){
                    throw new AcManagementException(AcExceptionCodes.AC_ROLE_IS_NOT_FOUND,wrap("资源GUID对应的角色查询失败",guidAcResource));
                }
                break;
            case POSITION:
                OmPosition omPosition = omPositionService.selectById(guidAcResource);
                if (null == omPosition){
                    throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_POSITION_BY_ACOPERATORIDENTITYRES,wrap("资源GUID对应的岗位查询失败",guidAcResource));
                }
                break;
            case WORKGROUP:
                OmGroup omGroup = omGroupService.selectById(guidAcResource);
                if (null == omGroup){
                    throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_GROUP_BY_ACOPERATORIDENTITYRES,wrap("资源GUID对应的工作组查询失败",guidAcResource));
                }
                break;
            case ORGANIZATION:
                OmOrg omOrg = omOrgService.selectById(guidAcResource);
                if (null == omOrg){
                    throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_ORG_BY_ACOPERATORIDENTITYRES,wrap("资源GUID对应的机构查询失败"),guidAcResource);
                }
                break;
            default:
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_NULL,wrap("资源类型有误,请核实",acResourceType));
        }
    }

}

