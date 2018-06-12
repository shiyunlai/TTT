package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorIdentityMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorIdentity的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorIdentityServiceImpl extends ServiceImpl<AcOperatorIdentityMapper, AcOperatorIdentity> implements IAcOperatorIdentityService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcAppService acAppService;


    @Override
    public void add(AcOperatorIdentityRequest acOperatorIdentityRequest) throws AcManagementException {

        //判断guidOperator对应的操作员是否存在
        AcOperator acOperator = acOperatorService.selectById(acOperatorIdentityRequest.getGuidOperator());
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("查询操作员失败",acOperatorIdentityRequest.getGuidOperator()));
        }

        //判断guidApp对应的应用是否存在
        AcApp acApp = acAppService.selectById(acOperatorIdentityRequest.getGuidApp());
        if (null == acApp){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("查询应用失败"),acOperatorIdentityRequest.getGuidApp());
        }

        changeOtherToNo(acOperatorIdentityRequest);

        AcOperatorIdentity acOperatorIdentity = new AcOperatorIdentity();

        acOperatorIdentity.setGuidOperator(acOperatorIdentityRequest.getGuidOperator());
        acOperatorIdentity.setIdentityName(acOperatorIdentityRequest.getIdentityName());
        acOperatorIdentity.setIdentityFlag(acOperatorIdentityRequest.getIdentityFlag());
        acOperatorIdentity.setGuidApp(acOperatorIdentityRequest.getGuidApp());
        acOperatorIdentity.setSeqNo(acOperatorIdentityRequest.getSeqNo());

        insert(acOperatorIdentity);
    }


    @Override
    public AcOperatorIdentity change(AcOperatorIdentityRequest acOperatorIdentityRequest) throws AcManagementException {

        //判断guidOperator对应的操作员是否存在
        AcOperator acOperator = acOperatorService.selectById(acOperatorIdentityRequest.getGuidOperator());
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("查询操作员失败",acOperatorIdentityRequest.getGuidOperator()));
        }

        //判断guidApp对应的应用是否存在
        AcApp acApp = acAppService.selectById(acOperatorIdentityRequest.getGuidApp());
        if (null == acApp){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("查询应用失败"),acOperatorIdentityRequest.getGuidApp());
        }

        changeOtherToNo(acOperatorIdentityRequest);

        AcOperatorIdentity acOperatorIdentity = new AcOperatorIdentity();

        acOperatorIdentity.setGuid(acOperatorIdentityRequest.getGuid());
        acOperatorIdentity.setGuidOperator(acOperatorIdentityRequest.getGuidOperator());
        acOperatorIdentity.setIdentityName(acOperatorIdentityRequest.getIdentityName());
        acOperatorIdentity.setIdentityFlag(acOperatorIdentityRequest.getIdentityFlag());
        acOperatorIdentity.setGuidApp(acOperatorIdentityRequest.getGuidApp());
        acOperatorIdentity.setSeqNo(acOperatorIdentityRequest.getSeqNo());

        updateById(acOperatorIdentity);

        return acOperatorIdentity;
    }


    @Override
    public Page<AcOperatorIdentity> queryByOperator(Page<AcOperatorIdentity> page, Wrapper<AcOperatorIdentity> wrapper, String id) throws AcManagementException {

        if (null == wrapper){
            wrapper = new EntityWrapper<AcOperatorIdentity>();
        }

        wrapper.eq(AcOperatorIdentity.COLUMN_GUID_OPERATOR,id);

        Page<AcOperatorIdentity> pageQue = selectPage(page,wrapper);

        return pageQue;
    }

    /**如果设置该操作员身份为默认操作员身份,则设置该操作员的其他操作员身份为非默认*/
    private void changeOtherToNo(AcOperatorIdentityRequest acOperatorIdentityRequest){
        if (YON.YES.equals(acOperatorIdentityRequest.getIdentityFlag())) {
            Wrapper<AcOperatorIdentity> wrapper = new EntityWrapper<AcOperatorIdentity>();
            wrapper.eq(AcOperatorIdentity.COLUMN_GUID_OPERATOR,acOperatorIdentityRequest.getGuidOperator());
            List<AcOperatorIdentity> list = selectList(wrapper);

            for (AcOperatorIdentity acOperatorIdentity:list){
                if (YON.YES.equals(acOperatorIdentity.getIdentityFlag())){
                    acOperatorIdentity.setIdentityFlag(YON.NO);
                    updateById(acOperatorIdentity);
                }
            }
        }
    }
}

