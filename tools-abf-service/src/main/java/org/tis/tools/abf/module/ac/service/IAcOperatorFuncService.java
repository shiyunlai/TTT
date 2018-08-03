package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncQueConditionRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueRequest;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorFunc的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorFuncService extends IService<AcOperatorFunc>  {

    /**
     * 新增操作员特殊权限配置
     * @param acOperatorFuncRequestc
     * @throws AcManagementException
     */
    void add(AcOperatorFuncRequest acOperatorFuncRequestc) throws AcManagementException;

    /**
     *修改操作员特殊权限配置
     * @param acOperatorFuncRequestc
     * @return
     * @throws AcManagementException
     */
    AcOperatorFunc change(AcOperatorFuncRequest acOperatorFuncRequestc)throws AcManagementException;

    /**
     * 查询所有应用和操作员下已有应用
     * @param operatorId
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcApp> queryAppByOperator(String operatorId)throws AcManagementException;

    /**
     * 查询应用下的所有功能 和 操作员和应用下的所有功能
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcFunc> queryFuncByOperator(AcOperatorFuncQueConditionRequest conditionRequest)throws
            AcManagementException;

    /**
     * 查询应用下的所有行为 和 操作员和应用下的所有行为
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    AcRoleFuncQueRequest<AcFunc> queryBehaveByOperator(AcOperatorFuncQueConditionRequest conditionRequest) throws
            AcManagementException;

    /**
     * 批量新增操作员_应用数据
     * @throws AcManagementException
     */
    void batchAdd(AcOperatorBatchAddRequest batchAddRequest)throws AcManagementException;
}

