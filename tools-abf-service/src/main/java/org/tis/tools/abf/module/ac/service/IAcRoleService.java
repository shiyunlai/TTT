package org.tis.tools.abf.module.ac.service;

import org.tis.tools.abf.module.ac.entity.AcRole;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcRoleManagementException;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * acRole的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcRoleService extends IService<AcRole>{
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
    List<AcRole> queryAllRole() throws AcRoleManagementException;

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
    AcRole queryByCode(String roleCode)throws AcRoleManagementException;


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
    AcRole queryByName(String roleName)throws AcRoleManagementException;

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
    AcRole queryByGuid(String guid)throws AcRoleManagementException;

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
    boolean createAcRole(String roleCode, String roleName, YON enabled, String roleDesc) throws AcRoleManagementException;

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
    boolean updateAcRole(AcRole acRole) throws AcRoleManagementException;

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
    boolean deleteByRoleCode(String roleCode) throws AcRoleManagementException;


    /**
     * <pre>
     * 增加某个角色的功能
     *
     * </pre>
     * @param acRoleFunc
     * @return 返回增加结果
     * @throws AcRoleManagementException
     */
    boolean addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleManagementException;


    /**
     * <pre>
     * 移除某个角色的功能
     * 传入角色ID 功能ID
     * </pre>
     * @param roleGuid
     * @param funcGuid
     * @return 返回删除结果
     * @throws AcRoleManagementException
     */
    boolean removeRoleFunc(String roleGuid,String funcGuid) throws AcRoleManagementException;

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
     * @throws AcRoleManagementException
     */

    boolean removeRoleFuncWithApp(String roleGuid,String appGuid) throws AcRoleManagementException;
}

