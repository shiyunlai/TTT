package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorMapper;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

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

        boolean isexist = new Boolean(false);

        try {

        //判断user_id是否已经存在,确保user_id的唯一性
        Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();
        List<AcOperator > lists = selectList(wrapper);

        for (AcOperator acOperator:lists) {
            if (request.getUserId().equals(acOperator.getUserId())){
                return isexist;
            }
        }
        //循环结束之后表示没有重复,故设置标志位为true
        isexist = true;

        //收集参数
        AcOperator acOperator = new AcOperator();
        acOperator.setUserId(request.getUserId());
        acOperator.setPassword(request.getPassword());
        acOperator.setOperatorName(request.getOperatorName());
        acOperator.setOperatorStatus(request.getOperatorStatus());
        acOperator.setInvalDate(request.getInvalDate());
        acOperator.setAuthMode(request.getAuthMode());
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

        insert(acOperator);

        return isexist;
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

        boolean isexist = new Boolean(false);

        try {

                //判断user_id是否已经存在,确保user_id的唯一性
                Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();
                List<AcOperator > lists = selectList(wrapper);

                for (AcOperator acOperatorNew:lists) {
                    if (acOperator.getUserId().equals(acOperatorNew.getUserId()) && !(acOperator.getGuid().equals(acOperatorNew.getGuid())) ){
                        return isexist;
                    }
                }
                //循环结束之后表示没有重复,故设置标志位为true
                isexist = true;

            updateById(acOperator);
            return isexist;
        }catch (Exception e){
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_UPDATE,wrap("ACOPERATOR",e));
        }

    }
}

