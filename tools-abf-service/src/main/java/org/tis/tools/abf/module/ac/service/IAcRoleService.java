package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcRoleManagementException;
import org.tis.tools.abf.module.common.entity.enums.YON;


/**
 * acRole的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcRoleService extends IService<AcRole>{


    /**
     * <pre>
     * 根据条件查询角色
     * </pre>
     *
     * @param acRole
     *
     * @return 匹配的角色
     *
     * @throws AcRoleManagementException
     */
    AcRole queryByCondition(AcRole acRole)throws AcRoleManagementException;




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
    boolean createAcRole(String roleCode, String roleName, YON enabled, String roleDesc,String roleGroup) throws
            AcRoleManagementException;

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
     * @param id
     *
     * @return 删除角色信息
     * @throws AcRoleManagementException
     */
    boolean deleteByRoleCode(String id) throws AcRoleManagementException;


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



}

