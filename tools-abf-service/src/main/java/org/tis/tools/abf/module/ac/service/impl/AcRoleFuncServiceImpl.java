package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcRoleFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

/**
 * acRoleFunc的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleFuncServiceImpl extends ServiceImpl<AcRoleFuncMapper, AcRoleFunc> implements IAcRoleFuncService {

    /**
     * <pre>
     *查询某个角色所有功能
     * </pre>
     *
     *
     * @return 查询某个角色所有功能
     * @throws AcRoleFuncManagementException
     */
    @Override
    public List<AcRoleFunc> queryAllRoleFunByRoleGuid(String roelGuid) throws AcRoleFuncManagementException {
        try {
            EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,roelGuid);
            List<AcRoleFunc> acRoleFuncList = selectList(acRoleFuncEntityWrapper);
            return acRoleFuncList;
        }catch (Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }


    /**
     * <pre>
     *增加某个角色的功能
     * </pre>
     *
     *
     * @param acRoleFunc
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException {
        try {
            insert(acRoleFunc);
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACROLEFUNC","ACROLEFUNC"));
        }
        //wfl 不确定返回对不对
        return acRoleFunc;
    }


    /**
     * <pre>
     *删除某个角色的所有相关记录
     * </pre>
     *
     *
     * @param guidRole
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc deleteAcRole(String guidRole) throws AcRoleFuncManagementException {
        try {
            if (StringUtils.isBlank(guidRole)){
                throw new AcRoleFuncManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("GUIDROLE","ACROLEFUNC"));
            }
            EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,guidRole);
            delete(acRoleFuncEntityWrapper);
            //wfl 不知道怎么返回
            return null;
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("GUIDROLE","ACROLEFUNC"));
        }

    }


    /**
     * <pre>
     *删除某个角色的某些功能
     * </pre>
     *
     * @param guidFunc
     * @param guidRole
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc deleteAcRoleByCondition(String guidRole, String guidFunc) throws AcRoleFuncManagementException {
        try {
            EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,guidRole);
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,guidFunc);
            delete(acRoleFuncEntityWrapper);
        }catch (Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACROLEFUNC","ACROLEFUNC"));
        }
        return null;
    }
}

