package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorConfigRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorConfig;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorConfig的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorConfigService extends IService<AcOperatorConfig>  {

    /**
     *新增操作员个性配置
     * @param acOperatorConfigRequest
     * @throws AcManagementException
     */
    void add(AcOperatorConfigRequest acOperatorConfigRequest) throws AcManagementException;

    /**
     * 修改操作员个性配置
     * @param acOperatorConfigRequest
     * @return
     * @throws AcManagementException
     */
    AcOperatorConfig change(AcOperatorConfigRequest acOperatorConfigRequest)throws AcManagementException;

}

