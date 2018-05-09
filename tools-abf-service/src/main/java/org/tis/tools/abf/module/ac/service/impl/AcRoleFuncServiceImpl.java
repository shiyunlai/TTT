package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcRoleFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

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
     *通过角色ID查询某个角色的数据
     *
     * </pre>
     *
     * @param guidRole
     *
     * @return 查询某个角色数据
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc queryRoleFunByRoleGuid(String guidRole) throws AcRoleFuncManagementException {
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,guidRole);
        try {
            AcRoleFunc acRoleFunc = selectOne(acRoleFuncEntityWrapper);
            return acRoleFunc;
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_QUERY,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }

    /**
     * <pre>
     *查询某个角色所有功能
     * </pre>
     *
     * @param guidRole
     * @return 查询某个角色所有功能
     * @throws AcRoleFuncManagementException
     */
    @Override
    public List<AcRoleFunc> queryAllRoleFunByRoleGuid(String guidRole) throws AcRoleFuncManagementException {
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,guidRole);
        try {
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
     * @return 增加结果
     * @throws AcRoleFuncManagementException
     */
    @Override
    public boolean addRoleFunc(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException {
        try {
            boolean bolen = insert(acRoleFunc);
            return bolen;
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }


    /**
     * <pre>
     *删除某个角色的所有相关记录
     * </pre>
     *
     *
     * @param guidRole
     * @return 返回删除结果
     * @throws AcRoleFuncManagementException
     */
    @Override
    public boolean deleteAcRole(String guidRole) throws AcRoleFuncManagementException {
        if (StringUtils.isBlank(guidRole)){
            throw new AcRoleFuncManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("GUIDROLE","ACROLEFUNC"));
        }
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,guidRole);

        try {
            //删除前查询有没有可删除的数据
            AcRoleFunc acRoleFunc = queryRoleFunByRoleGuid(guidRole);
            return delete(acRoleFuncEntityWrapper);
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("GUIDROLE","ACROLEFUNC"));
        }

    }


    /**
     * <pre>
     *删除某个角色的某些功能
     * </pre>
     *
     * @param acRoleFunc
     * @return 返回删除结果
     * @throws AcRoleFuncManagementException
     */
    @Override
    public boolean deleteAcRoleByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException {
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,acRoleFunc.getGuidRole());
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFunc.getGuidFunc());
        acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFunc.getGuidApp());
        try {
            //删除前先查询下存在否
            AcRoleFunc acRoleFunc1 = queryRoleFunByRoleGuid(acRoleFunc.getGuidRole());
            if(acRoleFunc1 == null){
                throw new AcRoleFuncManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("ACROLEFUNC","ACROLEFUNC"));
            }
            boolean bolen = delete(acRoleFuncEntityWrapper);
            return bolen;
        }catch (Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }
}

