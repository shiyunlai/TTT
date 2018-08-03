package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueConditionRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueRequest;
import org.tis.tools.abf.module.ac.dao.AcRoleFuncMapper;
import org.tis.tools.abf.module.ac.entity.*;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.entity.vo.OperatorRoleAdd;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.exception.AcRoleFuncManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.tis.tools.core.exception.i18.ExceptionCodes;
import org.tis.tools.core.utils.StringUtil;

import java.util.ArrayList;
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
            //判断该记录是否已经存在,如果存在则不需要重复添加
            Wrapper<AcRoleFunc> wrapper = new EntityWrapper<>();
            wrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,acRoleFuncAddRequest.getGuidRole());
            wrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFuncAddRequest.getGuidFunc());
            wrapper.eq(AcRoleFunc.COLUMN_GUID_APP,acRoleFuncAddRequest.getGuidApp());

            AcRoleFunc acRoleFuncQurey = selectOne(wrapper);
            if (null != acRoleFuncQurey){
                //该记录已经添加,不需要重复添加
                throw new AcRoleFuncManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_IS_EXIST,wrap("该记录已存在",acRoleFuncAddRequest.getGuidFunc()));
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
                throw new AcRoleFuncManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_ROLE_FUNC,wrap("功能权限ID对应的权限集不存在"));
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

    /**
     * 根据角色和应用id查询已有功能
     *
     * @param appId
     * @param roleId
     * @param funcId
     * @return
     * @throws AcRoleFuncManagementException
     */
    @Override
    public List<AcFunc> queryFuncByRoleApp(String appId, String roleId, String funcId) throws AcRoleFuncManagementException {

        //根据条件查询记录
        Wrapper<AcRoleFunc> wrapper = new EntityWrapper<AcRoleFunc>();
        wrapper.eq(AcRoleFunc.COLUMN_GUID_APP,appId);
        wrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,roleId);

        List<AcRoleFunc> roleFuncList = selectList(wrapper);

        //根据查询结果查询所有的功能和行为
        List<AcFunc> funcList = new ArrayList<AcFunc>();
        for(AcRoleFunc acRoleFunc : roleFuncList){
            if (null != acRoleFunc){
                AcFunc acFunc = new AcFunc();

                Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
                funcWrapper.eq(AcFunc.COLUMN_GUID,acRoleFunc.getGuidFunc());
                if (StringUtil.isEmpty(funcId)){
                    funcWrapper.isNull(AcFunc.COLUMN_GUID_FUNC);
                }else {
                    funcWrapper.eq(AcFunc.COLUMN_GUID_FUNC,funcId);
                }
                funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE, FuncType.FUNCTION);

                acFunc = iAcFuncService.selectOne(funcWrapper);

                funcList.add(acFunc);
            }
        }

        return funcList;
    }

    /**
     * 根据角色和应用id查询已有行为
     *
     * @param appId
     * @param roleId
     * @param funcId
     * @return
     * @throws AcRoleFuncManagementException
     */
    @Override
    public List<AcFunc> queryBehaveByRoleApp(String appId, String roleId, String funcId) throws AcRoleFuncManagementException {
        //根据条件查询记录
        Wrapper<AcRoleFunc> wrapper = new EntityWrapper<AcRoleFunc>();
        wrapper.eq(AcRoleFunc.COLUMN_GUID_APP,appId);
        wrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,roleId);

        List<AcRoleFunc> roleFuncList = selectList(wrapper);

        //根据查询结果查询所有的功能和行为
        List<AcFunc> funcList = new ArrayList<AcFunc>();
        for(AcRoleFunc acRoleFunc : roleFuncList){
            if (null != acRoleFunc){
                AcFunc acFunc = new AcFunc();

                Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
                funcWrapper.eq(AcFunc.COLUMN_GUID,acRoleFunc.getGuidFunc());
                if (StringUtil.isEmpty(funcId)){
                    throw new AcRoleFuncManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY,wrap("应用的id不能为空"));
                }else {
                    funcWrapper.eq(AcFunc.COLUMN_GUID_FUNC,funcId);
                }
                funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE, FuncType.BEHAVE);

                acFunc = iAcFuncService.selectOne(funcWrapper);

                funcList.add(acFunc);
            }
        }

        return funcList;
    }

    /**
     * 批量新增和删除角色的应用
     * @param batchAddRequest
     * @throws AcManagementException
     */
    @Override
    public void batchAdd(AcRoleFuncBatchAddRequest batchAddRequest) throws AcManagementException {

            //待处理的前端数据
           List<AcRoleFunc> listsNew = findAllData(batchAddRequest);

           List<AcRoleFunc> listNewAll = new ArrayList<AcRoleFunc>();
            //添加新数据
            if (null != listsNew){
                for (AcRoleFunc acRoleFunc : listsNew){
                    if (null != acRoleFunc){
                        Wrapper<AcRoleFunc> funcAddWrapper = new EntityWrapper<AcRoleFunc>();
                        funcAddWrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,batchAddRequest.getRoleId());
                        funcAddWrapper.eq(AcRoleFunc.COLUMN_GUID_APP,acRoleFunc.getGuidApp());
                        funcAddWrapper.eq(AcRoleFunc.COLUMN_GUID_FUNC,acRoleFunc.getGuidFunc());

                        AcRoleFunc acRoleFuncQue = selectOne(funcAddWrapper);
                        if (null == acRoleFuncQue){
                            AcRoleFunc acRoleFuncAdd = new AcRoleFunc();
                            acRoleFuncAdd.setGuidRole(batchAddRequest.getRoleId());
                            acRoleFuncAdd.setGuidFunc(acRoleFunc.getGuidFunc());
                            acRoleFuncAdd.setGuidApp(acRoleFunc.getGuidApp());

                            insert(acRoleFuncAdd);
                            AcRoleFunc acRoleFunc1 = selectOne(funcAddWrapper);
                            listNewAll.add(acRoleFunc1);
                        }else {
                            listNewAll.add(acRoleFuncQue);
                        }
                    }
                }
            }

            //查询出该角色下的所有 角色-功能 数据
            Wrapper<AcRoleFunc> wrapper = new EntityWrapper<AcRoleFunc>();
            wrapper.eq(AcRoleFunc.COLUMN_GUID_ROLE,batchAddRequest.getRoleId());

            List<AcRoleFunc> listOld = selectList(wrapper);

            //找出原数据有,现数据中没有的数据,用于删除使用
            listOld.removeAll(listNewAll);
            //删除多于的数据
            if (null != listOld){
                for (AcRoleFunc acRoleFunc : listOld){
                    if (null != acRoleFunc){
                        deleteById(acRoleFunc.getGuid());
                    }
                }
            }

    }

    /** 整理出前端传来的数据 */
    private List<AcRoleFunc> findAllData(AcRoleFuncBatchAddRequest batchAddRequest){
        //查询该角色现有数据
        List<AcRoleFunc> listsNew = new ArrayList<AcRoleFunc>();
        if (null != batchAddRequest.getBatchData()){
            //遍历map,获取应用id
            for(String  appId : batchAddRequest.getBatchData().keySet()){
                if (!StringUtil.isEmpty(appId)){
                    //获得
                    OperatorRoleAdd operatorRoleAdd = batchAddRequest.getBatchData().get(appId);
                    if (null != operatorRoleAdd){
                        for (String funcId : operatorRoleAdd.getFuncData().keySet()){
                            if (!StringUtil.isEmpty(funcId)){
                                AcRoleFunc acRoleFunc1 = new AcRoleFunc();
                                for (String behaveId : operatorRoleAdd.getFuncData().get(funcId)){
                                    if (!StringUtil.isEmpty(behaveId)){
                                        AcRoleFunc acRoleFunc2 = new AcRoleFunc();
                                        acRoleFunc2.setGuidApp(appId);
                                        acRoleFunc2.setGuidRole(batchAddRequest.getRoleId());
                                        acRoleFunc2.setGuidFunc(behaveId);

                                        listsNew.add(acRoleFunc2);
                                    }
                                }

                                acRoleFunc1.setGuidApp(appId);
                                acRoleFunc1.setGuidRole(batchAddRequest.getRoleId());
                                acRoleFunc1.setGuidFunc(funcId);
                                listsNew.add(acRoleFunc1);
                            }
                        }
                    }
                }
            }
        }
        return listsNew;
    }

    /**
     * 查询所有应用和角色下已有应用
     *
     * @param roleId
     * @return
     * @throws AcManagementException
     */
    @Override
    public AcRoleFuncQueRequest<AcApp> queryAppByRole(String roleId) throws AcManagementException {

        //查询所有应用
        Wrapper<AcApp> appWrapper = new EntityWrapper<AcApp>();
        List<AcApp> allAppList = iAcAppService.selectList(appWrapper);

        //查询角色下已有应用
        List<AcApp> oldAppList = this.baseMapper.queryAppByRole(roleId);

        AcRoleFuncQueRequest<AcApp> queRequest = new AcRoleFuncQueRequest<AcApp>();
        queRequest.setAllData(allAppList);
        queRequest.setOldData(oldAppList);

        return queRequest;
    }

    /**
     * 查询应用下的所有功能 和 角色和应用下的所有功能
     *
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    @Override
    public AcRoleFuncQueRequest<AcFunc> queryFuncByRole(AcRoleFuncQueConditionRequest conditionRequest) throws AcManagementException {

        //判断应用id是否为空
        if (StringUtil.isEmpty(conditionRequest.getAppId())){
            throw new AcManagementException(AcExceptionCodes.APPID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("应用的id不能为空"));
        }

        //查询应用下的所有功能
        Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
        funcWrapper.eq(AcFunc.COLUMN_GUID_APP,conditionRequest.getAppId());
        funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.FUNCTION);

        List<AcFunc> allFuncs = iAcFuncService.selectList(funcWrapper);

        //查询角色和应用下的功能
        List<AcFunc> olFuncs = this.baseMapper.queryFuncByRole(conditionRequest.getRoleId(),conditionRequest.getAppId
                ());

        AcRoleFuncQueRequest<AcFunc> queRequest = new AcRoleFuncQueRequest<AcFunc>();
        queRequest.setAllData(allFuncs);
        queRequest.setOldData(olFuncs);

        return queRequest;
    }

    /**
     * 查询功能下的所有行为 和 角色和功能下的所有行为
     */
    @Override
    public AcRoleFuncQueRequest<AcFunc> queryBehaveByRole(AcRoleFuncQueConditionRequest conditionRequest) throws AcManagementException {
        //判断应用id是否为空
        if (StringUtil.isEmpty(conditionRequest.getAppId())){
            throw new AcManagementException(AcExceptionCodes.APPID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("应用的id不能为空"));
        }

        //判断功能id是否为空
        if (StringUtil.isEmpty(conditionRequest.getFuncId())){
            throw new AcManagementException(AcExceptionCodes.FUNCID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("功能的id不能为空"));
        }

        //查询功能下的所有行为
        Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
        funcWrapper.eq(AcFunc.COLUMN_GUID_APP,conditionRequest.getAppId());
        funcWrapper.eq(AcFunc.COLUMN_GUID_FUNC,conditionRequest.getFuncId());
        funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.BEHAVE);

        List<AcFunc> allBehaves= iAcFuncService.selectList(funcWrapper);

        //查询功能下已有的行为
        List<AcFunc> oldBehaves = this.baseMapper.queryBehaveByRole(conditionRequest.getRoleId(),conditionRequest
                .getAppId(),conditionRequest.getFuncId());

        AcRoleFuncQueRequest<AcFunc> queRequest = new AcRoleFuncQueRequest<AcFunc>();
        queRequest.setOldData(oldBehaves);
        queRequest.setAllData(allBehaves);

        return queRequest;
    }
}

