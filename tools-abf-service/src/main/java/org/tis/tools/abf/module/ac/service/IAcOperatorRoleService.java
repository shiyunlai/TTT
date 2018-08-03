package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorRoleBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleOperatorBatchAddRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorRole;
import org.tis.tools.abf.module.ac.entity.vo.OperatorExit;
import org.tis.tools.abf.module.ac.entity.vo.RoleExit;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

import java.util.List;

/**
 * acOperatorRole的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorRoleService extends IService<AcOperatorRole>  {

    /**
     * 为角色分配操作员
     * @param batchAddRequest
     * @throws AcManagementException
     */
    void batchAdd(AcOperatorRoleBatchAddRequest batchAddRequest)throws AcManagementException;

    /**
     * 为操作员分配角色
     * @throws AcManagementException
     */
    void batchAddRole(AcRoleOperatorBatchAddRequest batchAddRequest)throws AcManagementException;

    /**
     * 查询未分配角色和已绑定角色
     */
    List<RoleExit> queryRoleByOperator(String userId)throws AcManagementException;

    /**
     * 查询未分配操作员和已绑定操作员
     */
    List<OperatorExit> queryOperatorByRole(String roleId)throws AcManagementException;

}

