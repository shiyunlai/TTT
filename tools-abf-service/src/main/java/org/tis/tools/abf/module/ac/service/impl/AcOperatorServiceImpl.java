package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.enums.AuthMode;
import org.tis.tools.abf.module.ac.entity.enums.OperatorStatus;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperator的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorServiceImpl extends ServiceImpl<AcOperatorMapper, AcOperator> implements IAcOperatorService {

    /**
    * <pre>
     *  新增操作员
    * </pre>
    * @param request
    * @return 新增结果
     * @throws AcOperatorManagementException
    *
    */

    @Override
    public boolean addAcOperator(AcOperatorAddRequest request) throws AcOperatorManagementException {
        AcOperator acOperator = new AcOperator();
        acOperator.setUserId(request.getUserId());
        acOperator.setPassword(request.getPassword());
        acOperator.setOperatorName(request.getOperatorName());
        acOperator.setOperatorStatus(OperatorStatus.valueOf(request.getOperatorStatus()));
        acOperator.setInvalDate(request.getInvalDate());
        acOperator.setAuthMode(AuthMode.valueOf(request.getAuthMode()));
        acOperator.setLockLimit(request.getLockLimit());
        acOperator.setErrCount(request.getErrCount());
        acOperator.setLockTime(request.getLockTime());
        acOperator.setUnlockTime(request.getUnlockTime());
        acOperator.setLastLogin(request.getLastLogin());
        acOperator.setStartDate(request.getStartDate());
        acOperator.setEndDate(request.getEndDate());
        acOperator.setValidTime(request.getValidTime());
        acOperator.setMacCode(request.getMacCode());
        acOperator.setIpAddress(request.getIpAddress());
        try {
            boolean b = insert(acOperator);
            return b;
        }catch (Exception e){
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACOPERATOR",e));
        }
    }

    /**
     *
     * @param acOperator
     * @return 修改操作员结果
     * @throws AcOperatorManagementException
     */
    @Override
    public boolean updateAcOperatorByCondition(AcOperator acOperator) throws AcOperatorManagementException {
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,acOperator.getUserId());
        try {
            boolean b = update(acOperator,acOperatorEntityWrapper);
            return b;
        }catch (Exception e){
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_UPDATE,wrap("ACOPERATOR",e));
        }

    }

    /**
     *
     * @param acOperator
     * @return 删除操作员结果
     * @throws AcOperatorManagementException
     */
    @Override
    public boolean deleteAcOperator(AcOperator acOperator) throws AcOperatorManagementException {
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,acOperator.getUserId());
        try {
            boolean b = delete(acOperatorEntityWrapper);
            return b;
        }catch (Exception e){
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACOPERATOR",e));
        }
    }


}

