package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;

/**
 * acOperator的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorService extends IService<AcOperator>  {

    boolean addAcOperator(AcOperatorAddRequest acOperatorAddRequest) throws AcOperatorManagementException;

    boolean updateAcOperatorByCondition(AcOperator acOperator) throws AcOperatorManagementException;

}

