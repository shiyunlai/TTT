package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * acRoleFunc的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcRoleFuncService extends IService<AcRoleFunc>  {

    /**
     * <pre>
     *通过角色ID查询某个角色的数据有没有
     * </pre>
     *
     * @param roelGuid
     *
     * @return 查询某个角色数据
     * @throws AcRoleFuncManagementException
     */
    AcRoleFunc queryRoleFunByRoleGuid(String roelGuid) throws AcRoleFuncManagementException;

    /**
     * <pre>
     *通过角色ID查询某个角色的所有功能
     * </pre>
     *
     * @param roelGuid
     *
     * @return 查询某个角色的所有功能
     * @throws AcRoleFuncManagementException
     */
    List<AcRoleFunc> queryAllRoleFunByRoleGuid(String roelGuid) throws AcRoleFuncManagementException;


    /**
     * <pre>
     *增加某个角色的功能
     * </pre>
     *
     *
     * @param acRoleFunc
     * @return 返回增结果
     * @throws AcRoleFuncManagementException
     */
    boolean addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException;

    /**
     * <pre>
     *删除某个角色的所有相关记录
     * </pre>
     *
     *
     * @param guidRole
     * @return 返回增结果
     * @throws AcRoleFuncManagementException
     */
    boolean deleteAcRole(String guidRole) throws AcRoleFuncManagementException;


    /**
     * <pre>
     *删除某个角色的某些功能
     * </pre>
     *
     * @param acRoleFunc
     * @return 返回增加结果
     * @throws AcRoleFuncManagementException
     */
    boolean deleteAcRoleByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException;




}

