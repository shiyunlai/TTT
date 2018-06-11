package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncRequest;
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

}

