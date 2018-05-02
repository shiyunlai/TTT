package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.ac.dao.AcRoleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.exception.AcRoleManagementException;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.ArrayList;
import java.util.List;

/**
 * acRole的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleServiceImpl extends ServiceImpl<AcRoleMapper, AcRole> implements IAcRoleService {
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
        AcRoleList = selectList(null);
        return AcRoleList;
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
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
        AcRole acRole1 = selectOne(acRoleEntityWrapper);
        return acRole1;
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
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_NAME, roleName);
        AcRole acRole1 = selectOne(acRoleEntityWrapper);
        return acRole1;
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
        AcRole acRole1 = selectOne(acRoleEntityWrapper);
        return acRole1;
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
        AcRole acRole = new AcRole();
        acRole.setRoleCode(roleCode);
        acRole.setRoleName(roleName);
        acRole.setEnabled(YON.YES);
        acRole.setRoleDesc(roleDesc);
        insert(acRole);
        return acRole;
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
        updateById(acRole);
        return acRole;
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
        EntityWrapper<AcRole> acRoleEntityWrapper = new EntityWrapper<>();
        acRoleEntityWrapper.eq(AcRole.COLUMN_ROLE_CODE, roleCode);
        boolean bo = delete(acRoleEntityWrapper);
        return bo;
    }
}

