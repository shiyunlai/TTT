package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.dao.AcRoleFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
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

    @Autowired
    private IAcAppService iAcAppService;

    @Autowired
    private IAcFuncService iAcFuncService;



    /**
     * <pre>
     *通过角色ID查询某个角色的数据
     *
     * </pre>
     *
     * @param acRoleFunc
     *
     * @return 查询某个角色数据
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc queryRoleFunByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException {
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(acRoleFunc.getGuidRole())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,acRoleFunc.getGuidRole());
        }
        if(StringUtils.isNotBlank(acRoleFunc.getGuidApp())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_APP,acRoleFunc.getGuidApp());
        }
        if(StringUtils.isNotBlank(acRoleFunc.getGuidFunc())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFunc.getGuidFunc());
        }
        try {
            AcRoleFunc acRoleFunc1 = selectOne(acRoleFuncEntityWrapper);
            return acRoleFunc1;
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
     * @param acRoleFuncAddRequest
     * @return 增加结果
     * @throws AcRoleFuncManagementException
     */
    @Override
    public boolean addRoleFunc(AcRoleFuncAddRequest acRoleFuncAddRequest) throws AcRoleFuncManagementException {
        try {
            //判断功能是否存在
            AcFunc acFunc = iAcFuncService.selectById(acRoleFuncAddRequest.getGuidFunc());
            if (null == acFunc){
                throw new AcRoleFuncManagementException(AcExceptionCodes.NOT_FUNC_WHEN_CREATE_ROLEFUNC,wrap("功能ID对应的功能不存在",acRoleFuncAddRequest.getGuidFunc()));
            }

            AcApp acApp = iAcAppService.selectById(acRoleFuncAddRequest.getGuidApp());
            if (null == acApp){
                throw new AcRoleFuncManagementException(AcExceptionCodes.NOT_APP_WHEN_CREATE_ROLEFUNC,wrap("应用ID对应的应用不存在",acRoleFuncAddRequest.getGuidApp()));
            }

            AcRoleFunc acRoleFunc = new AcRoleFunc();
            acRoleFunc.setGuidRole(acRoleFuncAddRequest.getGuidRole());
            acRoleFunc.setGuidFunc(acRoleFuncAddRequest.getGuidFunc());
            acRoleFunc.setGuidApp(acRoleFuncAddRequest.getGuidApp());

            boolean bolen = insert(acRoleFunc);
            return bolen;
        }catch ( Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }
    /**
     * <pre>
     *修改某个角色的功能
     * </pre>
     *
     *
     * @param acRoleFuncAddRequest
     * @return 修改结果
     * @throws AcRoleFuncManagementException
     */
    @Override
    public AcRoleFunc update(AcRoleFuncAddRequest acRoleFuncAddRequest) throws AcRoleFuncManagementException {

        if (StringUtils.isNotBlank(acRoleFuncAddRequest.getGuid())){
            AcRoleFunc acRoleFunc = selectById(acRoleFuncAddRequest.getGuid());
            if (null == acRoleFunc){
                throw new AcRoleFuncManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_ROLE_FUNC,wrap("功能权限ID对应的权限集不存在",acRoleFuncAddRequest.getGuid()));
            }
        }else {
            throw new AcRoleFuncManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_ROLE_FUNC,wrap("权限集ID不能为空"));
        }

        //判断功能是否存在
        AcFunc acFunc = iAcFuncService.selectById(acRoleFuncAddRequest.getGuidFunc());
        if (null == acFunc){
            throw new AcRoleFuncManagementException(AcExceptionCodes.NOT_FUNC_WHEN_CREATE_ROLEFUNC,wrap("功能ID对应的功能不存在",acRoleFuncAddRequest.getGuidFunc()));
        }

        AcApp acApp = iAcAppService.selectById(acRoleFuncAddRequest.getGuidApp());
        if (null == acApp){
            throw new AcRoleFuncManagementException(AcExceptionCodes.NOT_APP_WHEN_CREATE_ROLEFUNC,wrap("应用ID对应的应用不存在",acRoleFuncAddRequest.getGuidApp()));
        }

        AcRoleFunc acRoleFunc1 = new AcRoleFunc();
        acRoleFunc1.setGuid(acRoleFuncAddRequest.getGuid());
        acRoleFunc1.setGuidRole(acRoleFuncAddRequest.getGuidRole());
        acRoleFunc1.setGuidFunc(acRoleFuncAddRequest.getGuidFunc());
        acRoleFunc1.setGuidApp(acRoleFuncAddRequest.getGuidApp());

        boolean bolen = updateById(acRoleFunc1);

        return acRoleFunc1;
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
    public boolean deleteAcRoleFuncByCondition(AcRoleFunc acRoleFunc) throws AcRoleFuncManagementException {
        EntityWrapper<AcRoleFunc> acRoleFuncEntityWrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(acRoleFunc.getGuidRole())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,acRoleFunc.getGuidRole());
        }
        if(StringUtils.isNotBlank(acRoleFunc.getGuidFunc())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFunc.getGuidFunc());
        }
        if(StringUtils.isNotBlank(acRoleFunc.getGuidApp())){
            acRoleFuncEntityWrapper.eq(AcRoleFunc.COLUMN_GUID_APP,acRoleFunc.getGuidApp());
        }
        try {
            boolean bolen = delete(acRoleFuncEntityWrapper);
            return bolen;
        }catch (Exception e){
            throw new AcRoleFuncManagementException(ExceptionCodes.FAILURE_WHEN_DELETE,wrap("ACROLEFUNC","ACROLEFUNC"));
        }

    }
}

