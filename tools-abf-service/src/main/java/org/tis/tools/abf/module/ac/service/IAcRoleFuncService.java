package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueConditionRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueRequest;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;

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
     * @param acRoleFunc
     *
     * @return 查询某个角色数据
     * @throws AcRoleFuncManagementException
     */
    AcRoleFunc queryRoleFunByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException;

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
     * @param acRoleFuncAddRequest
     * @return 返回增结果
     * @throws AcRoleFuncManagementException
     */
    boolean addRoleFunc(AcRoleFuncAddRequest acRoleFuncAddRequest) throws AcRoleFuncManagementException;

    /**
     * <pre>
     *修改某个角色的功能
     * </pre>
     *
     *
     * @param acRoleFuncAddRequest
     * @return 返回增结果
     * @throws AcRoleFuncManagementException
     */
    AcRoleFunc update(AcRoleFuncAddRequest acRoleFuncAddRequest) throws AcRoleFuncManagementException;



    /**
     * <pre>
     *删除某个角色的某些功能
     * </pre>
     *
     * @param acRoleFunc
     * @return 返回增加结果
     * @throws AcRoleFuncManagementException
     */
    boolean deleteAcRoleFuncByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException;

    /**
     * 根据角色和应用id查询已有功能
     * @param appId
     * @param roleId
     * @return
     * @throws AcRoleFuncManagementException
     */
    List<AcFunc> queryFuncByRoleApp(String appId , String roleId,String funcId) throws AcRoleFuncManagementException;

    /**
     * 根据角色和应用id查询已有行为
     * @param appId
     * @param roleId
     * @return
     * @throws AcRoleFuncManagementException
     */
    List<AcFunc> queryBehaveByRoleApp(String appId , String roleId,String funcId) throws AcRoleFuncManagementException;


    /**
     * 批量新增和删除角色与应用关系
     * @param batchAddRequest
     * @throws AcManagementException
     */
    void batchAdd(AcRoleFuncBatchAddRequest batchAddRequest)throws AcManagementException;

    /**
     * 查询所有应用和角色下已有应用
     * @param roleId
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcApp> queryAppByRole(String roleId)throws AcManagementException;

    /**
     * 查询应用下的所有功能 和 角色和应用下的所有功能
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcFunc> queryFuncByRole(AcRoleFuncQueConditionRequest conditionRequest)throws
            AcManagementException;

    /**
     * 查询应用下的所有行为 和 角色和应用下的所有行为
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcFunc> queryBehaveByRole(AcRoleFuncQueConditionRequest conditionRequest) throws
            AcManagementException;

}

