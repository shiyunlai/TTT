package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorRoleBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleOperatorBatchAddRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorRoleMapper;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorRole;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.ac.entity.vo.OperatorExit;
import org.tis.tools.abf.module.ac.entity.vo.RoleExit;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcOperatorRoleService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.core.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorRole的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorRoleServiceImpl extends ServiceImpl<AcOperatorRoleMapper, AcOperatorRole> implements IAcOperatorRoleService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcRoleService acRoleService;

    @Override
    public void batchAdd(AcOperatorRoleBatchAddRequest batchAddRequest) throws AcManagementException {

        if (null == batchAddRequest.getList()) {
            //列表中的数据为空,删除该角色下的已绑定操作员
            Wrapper<AcOperatorRole> wrapper = new EntityWrapper<AcOperatorRole>();
            wrapper.eq(AcOperatorRole.COLUMN_GUID_ROLE,batchAddRequest.getRoleId());
            delete(wrapper);
        }else {
            //查询该角色已绑定的操作员数据
            Wrapper<AcOperatorRole> operatorRoleWrapper = new EntityWrapper<AcOperatorRole>();
            operatorRoleWrapper.eq(AcOperatorRole.COLUMN_GUID_ROLE,batchAddRequest.getRoleId());
            List<AcOperatorRole> allList = selectList(operatorRoleWrapper);

            List<AcOperatorRole> oldList = new ArrayList<AcOperatorRole>();
            for (String operatorId : batchAddRequest.getList()){
                if (!StringUtil.isEmpty(operatorId)){

                    Wrapper<AcOperatorRole> wrapper = new EntityWrapper<AcOperatorRole>();
                    wrapper.eq(AcOperatorRole.COLUMN_GUID_ROLE,batchAddRequest.getRoleId());
                    wrapper.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,operatorId);

                    AcOperatorRole acOperatorRoleQue = selectOne(wrapper);
                    if (null == acOperatorRoleQue){
                        AcOperatorRole acOperatorRoleAdd = new AcOperatorRole();
                        acOperatorRoleAdd.setGuidRole(batchAddRequest.getRoleId());
                        acOperatorRoleAdd.setGuidOperator(operatorId);

                        insert(acOperatorRoleAdd);
                    }else {
                        oldList.add(acOperatorRoleQue);
                    }
                }
            }

            //将多余的数据删除
            allList.removeAll(oldList);
            if (null != allList){
                for (AcOperatorRole acOperatorRoleDel : allList){
                    if (null != acOperatorRoleDel){
                        deleteById(acOperatorRoleDel.getGuid());
                    }
                }
            }
        }
    }

    /**
     * 为操作员分配角色
     *
     * @throws AcManagementException
     */
    @Override
    public void batchAddRole(AcRoleOperatorBatchAddRequest batchAddRequest) throws AcManagementException {

        if (null == batchAddRequest.getList()){
            //列表中的数据为空,删除该操作员下的已绑定角色
            Wrapper<AcOperatorRole> wrapper = new EntityWrapper<AcOperatorRole>();
            wrapper.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,batchAddRequest.getOperatorId());
            delete(wrapper);
        }else {
            //查询该操作员已绑定的所有角色
            Wrapper<AcOperatorRole> wrapper = new EntityWrapper<AcOperatorRole>();
            wrapper.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,batchAddRequest.getOperatorId());
            List<AcOperatorRole> allList = selectList(wrapper);

            List<AcOperatorRole> oldList = new ArrayList<AcOperatorRole>();
            for (String roleId : batchAddRequest.getList()){
                if (!StringUtil.isEmpty(roleId)){

                    Wrapper<AcOperatorRole> queryWrapper = new EntityWrapper<AcOperatorRole>();
                    queryWrapper.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,batchAddRequest.getOperatorId());
                    queryWrapper.eq(AcOperatorRole.COLUMN_GUID_ROLE,roleId);

                    AcOperatorRole acOperatorRole = selectOne(queryWrapper);
                    if (null == acOperatorRole){
                        AcOperatorRole acOperatorRoleAdd = new AcOperatorRole();
                        acOperatorRoleAdd.setGuidRole(roleId);
                        acOperatorRoleAdd.setGuidOperator(batchAddRequest.getOperatorId());

                        insert(acOperatorRoleAdd);
                    }else {
                        oldList.add(acOperatorRole);
                    }
                }
            }
            //将多余的数据删除
            allList.removeAll(oldList);
            if (null != allList){
                for (AcOperatorRole acOperatorRoleDel : allList){
                    if (null != acOperatorRoleDel){
                        deleteById(acOperatorRoleDel.getGuid());
                    }
                }
            }
        }
    }

    /**
     * 查询未分配角色和已绑定角色
     *
     * @param userId
     */
    @Override
    public List<RoleExit> queryRoleByOperator(String userId) throws AcManagementException {

        //查询所有角色
        Wrapper<AcRole> roleWrapper = new EntityWrapper<AcRole>();
        List<AcRole> allRoles = acRoleService.selectList(roleWrapper);

        //查询出已绑定的角色
        Wrapper<AcOperator> operatorWrapper = new EntityWrapper<AcOperator>();
        operatorWrapper.eq(AcOperator.COLUMN_USER_ID,userId);
        AcOperator acOperator = acOperatorService.selectOne(operatorWrapper);
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("功能的id不能为空"));
        }
        List<AcRole> oldRoles = this.baseMapper.queryRoleByOperator(acOperator.getGuid());

        //未绑定的角色
        allRoles.removeAll(oldRoles);

        List<RoleExit> exitList = new ArrayList<RoleExit>();
        //为未绑定角色塞 true
        for (AcRole acRole : allRoles){
            if (null != acRole){
                RoleExit roleExit = new RoleExit(acRole,"true");
                exitList.add(roleExit);
            }
        }
        //为已绑定的角色 塞 false
        for (AcRole acRole : oldRoles){
            if (null != acRole){
                RoleExit roleExit = new RoleExit(acRole,"false");
                exitList.add(roleExit);
            }
        }

        return exitList;
    }

    /**
     * 查询未分配操作员和已绑定操作员
     *
     * @param roleId
     */
    @Override
    public List<OperatorExit> queryOperatorByRole(String roleId) throws AcManagementException {


        //查询所有的操作员
        Wrapper<AcOperator> operatorWrapper = new EntityWrapper<AcOperator>();
        List<AcOperator> operatorList = acOperatorService.selectList(operatorWrapper);

        //查询已绑定的操作员
        List<AcOperator> oldOperatorList = this.baseMapper.queryOperatorByRole(roleId);

        //未绑定的操作员
        operatorList.removeAll(oldOperatorList);

        List<OperatorExit> exitList = new ArrayList<OperatorExit>();
        //为所有的未绑定操作员塞值 true
        for (AcOperator acOperator : operatorList){
            if (null != acOperator){
                OperatorExit operatorExit = new OperatorExit(acOperator,"true");
                exitList.add(operatorExit);
            }
        }

        //为所有已绑定操作员塞值 false
        for (AcOperator acOperator : oldOperatorList){
            if (null != acOperator){
                OperatorExit operatorExit = new OperatorExit(acOperator,"false");
                exitList.add(operatorExit);
            }
        }

        return exitList;
    }
}

