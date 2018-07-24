package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorIdentity的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorIdentityService extends IService<AcOperatorIdentity>  {

    /**
     * 新增操作员身份
     * @param acOperatorIdentityRequest
     * @throws AcManagementException
     */
    void add(AcOperatorIdentityRequest acOperatorIdentityRequest) throws AcManagementException;

    /**
     * 修改操作员身份
     * @param acOperatorIdentityRequest
     * @return
     * @throws AcManagementException
     */
    AcOperatorIdentity change(AcOperatorIdentityRequest acOperatorIdentityRequest)throws AcManagementException;

    /**
     * 根据操作员ID查询操作员身份
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws AcManagementException
     */
    Page<AcOperatorIdentity> queryByOperator(Page<AcOperatorIdentity> page, Wrapper<AcOperatorIdentity> wrapper,
                                             String id)throws AcManagementException;

    /**
     * 删除操作员身份
     * @param id
     * @throws AcManagementException
     */
    void moveIdentity(String id)throws AcManagementException;

}

