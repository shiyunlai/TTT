package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.ac.dao.AcRoleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcRoleManagementException;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acRole的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleServiceImpl extends ServiceImpl<AcRoleMapper, AcRole> implements IAcRoleService {

    @Autowired
    IAcRoleFuncService acRoleFuncService;
    @Autowired
    IAcRoleService acRoleService;

    /**
     * <pre>
     * 查询所有角色
     * </pre>
     *
     * @param
     *
     * @return 匹配的角色
     *
     * @throws AcRoleManagementException
     */
    @Override
    public List<AcRole> queryAllRole() throws AcRoleManagementException {
        List<AcRole> AcRoleList = new ArrayList<AcRole>() ;
        try {
            AcRoleList = selectList(null);
            return AcRoleList;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLE",e));
        }


    }
    /**
     * <pre>
     * 根据角色代码查询角色
     * </pre>
     *
     * @param roleCode
     *
     * @return 匹配的角色
     *
     * @throws AcRoleManagementException
     */
    @Override
    public AcRole queryByCode(String roleCode) throws AcRoleManagementException{
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
        try {
            AcRole acRole1 = selectOne(acRoleEntityWrapper);
            return acRole1;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 根据角色名字查询角色
     * </pre>
     *
     * @param roleName
     *
     * @return 匹配的角色
     *
     * @throws AcRoleManagementException
     */
    @Override
    public AcRole queryByName(String roleName) throws AcRoleManagementException{
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_NAME, roleName);
        try{
            AcRole acRole1 = selectOne(acRoleEntityWrapper);
            return acRole1;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 根据ID查询角色
     * </pre>
     *
     * @param guid
     *
     * @return 匹配的角色
     *
     * @throws AcRoleManagementException
     */
    @Override
    public AcRole queryByGuid(String guid) throws AcRoleManagementException {
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_GUID, guid);
        try {
            AcRole acRole1 = selectOne(acRoleEntityWrapper);
            return acRole1;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 新建一个角色
     *
     *
     * </pre>
     *
     * @param roleCode
     *            角色代码
     * @param roleName
     *            角色名称
     * @param enabled
     *            是否启用
     * @param roleDesc
     *            角色描述
     * @return 新建角色信息结果
     * @throws AcRoleManagementException
     */
    @Override
    public boolean createAcRole(String roleCode, String roleName, YON enabled, String roleDesc) throws AcRoleManagementException {
        AcRole acRole = new AcRole();
        acRole.setRoleCode(roleCode);
        acRole.setRoleName(roleName);
        acRole.setEnabled(YON.YES);
        acRole.setRoleDesc(roleDesc);
        try {
            boolean bole = insert(acRole);
            return bole;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 根据Guid修改一个角色
     *
     *
     * </pre>
     *
     * @param acRole
     *
     * @return 修改角色信息结果
     * @throws AcRoleManagementException
     */
    @Override
    public boolean updateAcRole(AcRole acRole) throws AcRoleManagementException {
        try {
            boolean bolen = updateById(acRole);
            return bolen;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_UPDATE,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 根据roleCode删除一个角色
     *
     *
     * </pre>
     *
     * @param roleCode
     *
     * @return 删除结果
     * @throws AcRoleManagementException
     */
    @Override
    public boolean deleteByRoleCode(String roleCode) throws AcRoleManagementException {
        AcRole acRole = acRoleService.queryByCode(roleCode);
        if(acRole ==  null){
            throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY, wrap("ACROLE", "AC_ROLE"));
        }
        try{
            //删掉对应功能表格数据
            acRoleFuncService.deleteAcRole(roleCode);
            EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
            acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
            boolean bolen = delete(acRoleEntityWrapper);
            return bolen;
        }catch (Exception e ){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACROLE",e));
        }

    }


    /**
     * <pre>
     * 增加某个角色的功能
     *
     *
     * </pre>
     * @param acRoleFunc
     * @return 返回增加结果
     * @throws AcRoleManagementException
     */
    @Override
    public boolean addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleManagementException {
            try {
                boolean bolen = acRoleFuncService.addRoleFunc(acRoleFunc);
                return  bolen;
            }catch (Exception e){
                throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
            }

    }

    /**
     * <pre>
     * 移除某个角色的特定的功能
     *
     * </pre>
     * @param roleGuid
     * @param funcGuid
     * @return 返回删除结果
     * @throws AcRoleManagementException
     */
    @Override
    public boolean removeRoleFunc(String roleGuid, String funcGuid) throws AcRoleManagementException {
        try {
            AcRoleFunc acRoleFunc = acRoleFuncService.selectById(roleGuid);
            if(acRoleFunc == null){
                throw  new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
            }
            acRoleFunc.setGuidRole(roleGuid);
            acRoleFunc.setGuidFunc(funcGuid);
            acRoleFunc.setGuidApp("");
            boolean bolen = acRoleFuncService.deleteAcRoleByCondition(acRoleFunc);
            return bolen;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
        }
    }


    /**
     * <p>角色移除某应用的全部功能权限</p>
     * <p>
     * <pre>
     *     业务逻辑
     *     传入角色的GUID和功能GUID移除角色功能权限
     *     1.验证传入的对象不能为空
     *
     * </pre>
     * @param roleGuid
     * @param appGuid
     * @return 返回删除结果
     *
     * @throws AcRoleManagementException
     */
    @Override
    public boolean removeRoleFuncWithApp(String roleGuid, String appGuid) throws AcRoleManagementException {
        try {
            AcRoleFunc acRoleFunc = acRoleFuncService.selectById(roleGuid);
            if(acRoleFunc == null){
                throw  new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
            }
            acRoleFunc.setGuidRole(roleGuid);
            acRoleFunc.setGuidApp(appGuid);
            acRoleFunc.setGuidFunc("");
            boolean bolen = acRoleFuncService.deleteAcRoleByCondition(acRoleFunc);
            return bolen;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
        }
    }
}

