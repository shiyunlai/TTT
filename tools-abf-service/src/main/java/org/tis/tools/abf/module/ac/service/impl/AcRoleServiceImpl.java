package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
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
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * 根据Code查询角色
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
        try {
            EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
            acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
            AcRole acRole1 = selectOne(acRoleEntityWrapper);
            return acRole1;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 根据名字查询角色
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
        try{
            EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
            acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_NAME, roleName);
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
        try {
            EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
            acRoleEntityWrapper.eq(AcRole.COLUMN_GUID, guid);
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
     * @return 新建角色信息
     * @throws AcRoleManagementException
     */
    @Override
    public AcRole createAcRole(String roleCode, String roleName, String enabled, String roleDesc) throws AcRoleManagementException {
        try {
            AcRole acRole = new AcRole();
            acRole.setRoleCode(roleCode);
            acRole.setRoleName(roleName);
            acRole.setEnabled(YON.YES);
            acRole.setRoleDesc(roleDesc);
            if (null == acRole) {
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT, wrap("AC_ROLE", "AcRole"));
            }
            // 校验必要参数
            if (StringUtils.isBlank(acRole.getRoleCode())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_INSERT, wrap("ROLE_CODE", "AC_ROLE"));
            }
            if (StringUtils.isBlank(acRole.getRoleName())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_INSERT, wrap("ROLE_NAME", "AC_ROLE"));
            }
            if (StringUtils.isBlank(acRole.getEnabled())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_INSERT, wrap("Enabled", "AC_ROLE"));
            }
            insert(acRole);
            //这样返回对么 wfl
            return acRole;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACROLE",e));
        }

    }
    /**
     * <pre>
     * 修改一个角色
     *
     *
     * </pre>
     *
     * @param acRole
     *
     * @return 修改角色信息
     * @throws AcRoleManagementException
     */
    @Override
    public AcRole updateAcRole(AcRole acRole) throws AcRoleManagementException {
        try {
            if (null == acRole) {
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_UPDATE, wrap("AcRole", "AcRole"));
            }
            // 校验必要参数
            if (StringUtils.isBlank(acRole.getGuid())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_UPDATE, wrap("GUID", "AC_ROLE"));
            }
            if (StringUtils.isBlank(acRole.getRoleCode())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_UPDATE, wrap("ROLE_CODE", "AC_ROLE"));
            }
            if (StringUtils.isBlank(acRole.getRoleName())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_UPDATE, wrap("ROLE_NAME", "AC_ROLE"));
            }
            if (StringUtils.isBlank(acRole.getEnabled())) {
                throw new AcRoleManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_UPDATE, wrap("ENABLED", "AC_ROLE"));
            }
            updateById(acRole);
            return acRole;
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
     * @return 删除角色信息
     * @throws AcRoleManagementException
     */
    @Override
    public boolean deleteByRoleCode(String roleCode) throws AcRoleManagementException {

        try{
            boolean bol = false;
            if (StringUtils.isBlank(roleCode)) {
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE, wrap("ROLECODE", "AC_ROLE"));
            }
            AcRole acRole = acRoleService.queryByCode(roleCode);
            if(acRole ==  null){
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_QUERY, wrap("ACROLE", "AC_ROLE"));
            }
            TransactionTemplate transactionTemplate = new TransactionTemplate();
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    try {
                        //删掉对应功能表格数据
                        acRoleFuncService.deleteAcRole(roleCode);
                        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
                        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
                        delete(acRoleEntityWrapper);
                    }catch (Exception e){
                        status.setRollbackOnly();
                        e.printStackTrace();
                        throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACROLE",e));
                    }


                }
            });
            //这个返回不确定 wfl
            return bol;
        }catch (ToolsRuntimeException tre){
            throw tre;
        }catch (Exception e ){
            e.printStackTrace();
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
     *
     * @throws AcRoleManagementException
     */
    @Override
    public void addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleManagementException {
            if(null == acRoleFunc){
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
            }
            if (StringUtils.isBlank(acRoleFunc.getGuidFunc())){
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,wrap("GUID_FUNC","AC_ROLE_FUNC"));
            }
            if (StringUtils.isBlank(acRoleFunc.getGuidRole())){
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,wrap("GUID_ROLE","AC_ROLE_FUNC"));
            }
            if (StringUtils.isBlank(acRoleFunc.getGuidApp())){
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,wrap("GUID_APP","AC_ROLE_FUNC"));
            }
            TransactionTemplate transactionTemplate = new TransactionTemplate();
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    try {
                        acRoleFuncService.addRoleFunc(acRoleFunc);
                    }catch (Exception e){
                        status.setRollbackOnly();
                        e.printStackTrace();
                        throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
                    }
                }
            });

    }

    /**
     * <pre>
     * 移除某个角色的特定的功能
     *
     * </pre>
     * @param roleGuid
     * @param funcGuid
     *
     * @throws AcRoleManagementException
     */
    @Override
    public void removeRoleFunc(String roleGuid, String funcGuid) throws AcRoleManagementException {
        try {
            if (StringUtils.isBlank(roleGuid)) {
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE, wrap("GUID_ROLE", "AC_ROLE_FUNC"));
            }
            if (StringUtils.isBlank(funcGuid)) {
                throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE, wrap("GUID_FUNC", "AC_ROLE_FUNC"));
            }
            acRoleFuncService.deleteAcRoleByCondition(roleGuid,funcGuid);
        }catch (ToolsRuntimeException tre){
            throw tre;
        }catch (Exception e){
            throw new AcRoleManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("AC_ROLE_FUNC","AC_ROLE_FUNC"));
        }
    }
}

