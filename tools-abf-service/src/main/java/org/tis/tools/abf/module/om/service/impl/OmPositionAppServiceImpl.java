package org.tis.tools.abf.module.om.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppRequest;
import org.tis.tools.abf.module.om.dao.OmPositionAppMapper;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmPositionAppService;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.service.IOmPositionService;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * omPositionApp的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmPositionAppServiceImpl extends ServiceImpl<OmPositionAppMapper, OmPositionApp> implements IOmPositionAppService {

    @Autowired
    private IOmPositionService omPositionService;

    @Autowired
    private IAcAppService acAppService;

    /**
     * 新增
     *
     * @param omPositionAppRequest
     * @throws OrgManagementException
     */
    @Override
    public void add(OmPositionAppRequest omPositionAppRequest) throws OrgManagementException {

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omPositionAppRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.POSITION_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("岗位GUID",
                    omPositionAppRequest.getGuidPosition()));
        }

        //判断应用是否存在
        AcApp acApp = acAppService.selectById(omPositionAppRequest.getGuidApp());
        if (acApp == null){
            throw new OrgManagementException(OMExceptionCodes.APP_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("应用GUID",
                    omPositionAppRequest.getGuidApp()));
        }

        //如果岗位和应用均存在,插入数据
        OmPositionApp omPositionApp = new OmPositionApp();

        //收集参数
        omPositionApp.setGuidApp(omPositionAppRequest.getGuidApp());
        omPositionApp.setGuidPosition(omPositionAppRequest.getGuidPosition());

        insert(omPositionApp);
    }
}

