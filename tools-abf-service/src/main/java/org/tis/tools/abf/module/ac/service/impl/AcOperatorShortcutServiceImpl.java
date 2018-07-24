package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorShortcutRequest;
import org.tis.tools.abf.module.ac.entity.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcOperatorShortcutMapper;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.*;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorShortcut的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorShortcutServiceImpl extends ServiceImpl<AcOperatorShortcutMapper, AcOperatorShortcut> implements IAcOperatorShortcutService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcAppService acAppService;

    @Autowired
    private IAcFuncService acFuncService;


    @Override
    public void add(AcOperatorShortcutRequest acOperatorShortcutRequest) throws AcManagementException {

        //判断操作员id对应的操作员是否存在
        AcOperator acOperator = acOperatorService.selectById(acOperatorShortcutRequest.getGuidOperator());
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在"),acOperatorShortcutRequest.getGuidOperator());
        }

        //判断应用id对应的应用是否存在
        AcApp acApp = acAppService.selectById(acOperatorShortcutRequest.getGuidApp());
        if (null == acApp){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在"),acOperatorShortcutRequest.getGuidApp());
        }

        //判断功能ID对应的功能是否存在
        AcFunc acFunc = acFuncService.selectById(acOperatorShortcutRequest.getGuidFunc());
        if (null == acFunc){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在"),acOperatorShortcutRequest.getGuidFunc());
        }


        AcOperatorShortcut acOperatorShortcut = new AcOperatorShortcut();

        acOperatorShortcut.setGuidOperator(acOperatorShortcutRequest.getGuidOperator());
        acOperatorShortcut.setShortcutKey(acOperatorShortcutRequest.getShortcutKey());
        acOperatorShortcut.setGuidApp(acOperatorShortcutRequest.getGuidApp());
        acOperatorShortcut.setGuidFunc(acOperatorShortcutRequest.getGuidFunc());
        acOperatorShortcut.setAliasFuncName(acOperatorShortcutRequest.getAliasFuncName());
        acOperatorShortcut.setOrderNo(acOperatorShortcutRequest.getOrderNo());
        acOperatorShortcut.setImagePath(acOperatorShortcutRequest.getImagePath());
        acOperatorShortcut.setExpandPath(acOperatorShortcutRequest.getExpandPath());

        insert(acOperatorShortcut);

    }


    @Override
    public AcOperatorShortcut change(AcOperatorShortcutRequest acOperatorShortcutRequest) throws AcManagementException {

        //判断操作员id对应的操作员是否存在
        AcOperator acOperator = acOperatorService.selectById(acOperatorShortcutRequest.getGuidOperator());
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在"),acOperatorShortcutRequest.getGuidOperator());
        }

        //判断应用id对应的应用是否存在
        AcApp acApp = acAppService.selectById(acOperatorShortcutRequest.getGuidApp());
        if (null == acApp){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在"),acOperatorShortcutRequest.getGuidApp());
        }

        //判断功能ID对应的功能是否存在
        AcFunc acFunc = acFuncService.selectById(acOperatorShortcutRequest.getGuidFunc());
        if (null == acFunc){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在"),acOperatorShortcutRequest.getGuidFunc());
        }

        AcOperatorShortcut acOperatorShortcut = new AcOperatorShortcut();

        acOperatorShortcut.setGuid(acOperatorShortcutRequest.getGuid());
        acOperatorShortcut.setGuidOperator(acOperatorShortcutRequest.getGuidOperator());
        acOperatorShortcut.setShortcutKey(acOperatorShortcutRequest.getShortcutKey());
        acOperatorShortcut.setGuidApp(acOperatorShortcutRequest.getGuidApp());
        acOperatorShortcut.setGuidFunc(acOperatorShortcutRequest.getGuidFunc());
        acOperatorShortcut.setAliasFuncName(acOperatorShortcutRequest.getAliasFuncName());
        acOperatorShortcut.setOrderNo(acOperatorShortcutRequest.getOrderNo());
        acOperatorShortcut.setImagePath(acOperatorShortcutRequest.getImagePath());
        acOperatorShortcut.setExpandPath(acOperatorShortcutRequest.getExpandPath());

        updateById(acOperatorShortcut);

        return acOperatorShortcut;
    }


    @Override
    public Page<AcOperatorShortcut> queryByOperator(Page<AcOperatorShortcut> page, Wrapper<AcOperatorShortcut>
            wrapper, String OperatorId) throws AcManagementException {

        if (null == wrapper){
            wrapper = new EntityWrapper<AcOperatorShortcut>();
        }

        wrapper.eq(AcOperatorShortcut.COLUMN_GUID_OPERATOR,OperatorId);

        Page<AcOperatorShortcut> pageQuery = selectPage(page,wrapper);

        return pageQuery;
    }
}