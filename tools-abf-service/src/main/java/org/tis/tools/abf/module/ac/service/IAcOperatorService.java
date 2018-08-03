package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorStatusRequest;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;

import java.util.List;

/**
 * acOperator的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorService extends IService<AcOperator>  {

    boolean addAcOperator(AcOperatorAddRequest acOperatorAddRequest) throws AcOperatorManagementException;

    boolean updateAcOperatorByCondition(AcOperator acOperator) throws AcOperatorManagementException;

    /**
     * 改变操作员状态
     * @param acOperatorStatusRequest
     * @return
     * @throws AcOperatorManagementException
     */
    AcOperator changeOperatorStatus(AcOperatorStatusRequest acOperatorStatusRequest) throws AcOperatorManagementException;

    /**
     * 查询所有操作员
     * @return
     * @throws AcOperatorManagementException
     */
    List<AcOperator> queryAllOperator() throws AcOperatorManagementException;

    /**
     * 删除操作员
     * @param id
     * @throws AcOperatorManagementException
     */
    void moveOperator(String id) throws AcOperatorManagementException;

    /**
     * 根据条件查询角色下的操作员
     * @param roleId
     * @return
     * @throws AcOperatorManagementException
     */
    Page<AcOperator> queryByRole (String roleId, Page<AcOperator> page, EntityWrapper<AcOperator> wrapper) throws
            AcOperatorManagementException;



}

