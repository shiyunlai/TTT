package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppListRequest;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppRequest;
import org.tis.tools.abf.module.om.dao.OmPositionAppMapper;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmPositionAppService;
import org.tis.tools.abf.module.om.service.IOmPositionService;

import java.util.ArrayList;
import java.util.List;

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
            throw new OrgManagementException(OMExceptionCodes.POSITION_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("岗位GUID对应的岗位不存在", omPositionAppRequest.getGuidPosition()));
        }

        //判断应用是否存在
        AcApp acApp = acAppService.selectById(omPositionAppRequest.getGuidApp());
        if (acApp == null){
            throw new OrgManagementException(OMExceptionCodes.APP_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("应用GUID对应的应用不存在", omPositionAppRequest.getGuidApp()));
        }

        //如果岗位和应用均存在,插入数据
        OmPositionApp omPositionApp = new OmPositionApp();

        //收集参数
        omPositionApp.setGuidApp(omPositionAppRequest.getGuidApp());
        omPositionApp.setGuidPosition(omPositionAppRequest.getGuidPosition());

        //如果该岗位下已存在应用权限,则不需要重复插入
        Wrapper<OmPositionApp> wrapper = new EntityWrapper<OmPositionApp>();
        wrapper.eq(OmPositionApp.COLUMN_GUID_POSITION,omPositionAppRequest.getGuidPosition());
        wrapper.eq(OmPositionApp.COLUMN_GUID_APP,omPositionAppRequest.getGuidApp());
        OmPositionApp omPositionAppQue = selectOne(wrapper);
        if (null != omPositionAppQue){
            throw new OrgManagementException(OMExceptionCodes.IS_EXIST_BY_OM_APP_POSITION,wrap("新增应用权限失败,因已存在该应用权限"));
        }else {
            insert(omPositionApp);
        }
    }


    @Override
    public void addList(OmPositionAppListRequest om) throws OrgManagementException {

        OmPositionApp omPositionApp = new OmPositionApp();

        omPositionApp.setGuidPosition(om.getGuidPosition());

        OmPositionAppRequest omPositionAppRequest = new OmPositionAppRequest();

        for (String guidApp : om.getAppList()){
            omPositionApp.setGuidApp(guidApp);
            //如果该岗位下已存在应用权限,则不需要重复插入
            Wrapper<OmPositionApp> wrapper = new EntityWrapper<OmPositionApp>();
            wrapper.eq(OmPositionApp.COLUMN_GUID_POSITION,om.getGuidPosition());
            wrapper.eq(OmPositionApp.COLUMN_GUID_APP,guidApp);
            OmPositionApp omPositionAppQue = selectOne(wrapper);
            if (null != omPositionAppQue){
                throw new OrgManagementException(OMExceptionCodes.IS_EXIST_BY_OM_APP_POSITION,wrap("新增应用权限失败,因已存在该应用权限"));
            }else {
                insert(omPositionApp);
            }
        }
    }

    @Override
    public OmPositionApp change(OmPositionAppRequest omPositionAppRequest) throws OrgManagementException {

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omPositionAppRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.POSITION_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("岗位GUID对应的岗位不存在", omPositionAppRequest.getGuidPosition()));
        }

        //判断应用是否存在
        AcApp acApp = acAppService.selectById(omPositionAppRequest.getGuidApp());
        if (acApp == null){
            throw new OrgManagementException(OMExceptionCodes.APP_NOT_EXIST_BY_CREAT_POSITIONAPP,wrap("应用GUID对应的应用不存在", omPositionAppRequest.getGuidApp()));
        }

        //如果岗位和应用均存在,插入数据
        OmPositionApp omPositionApp = new OmPositionApp();

        //收集参数
        omPositionApp.setGuid(omPositionAppRequest.getGuid());
        omPositionApp.setGuidApp(omPositionAppRequest.getGuidApp());
        omPositionApp.setGuidPosition(omPositionAppRequest.getGuidPosition());

        updateById(omPositionApp);

        return omPositionApp;
    }


    @Override
    public Page<AcApp> queryAppByPosition(Page<OmPositionApp> page, Wrapper<OmPositionApp> wrapper, String id) throws
            OrgManagementException {

        if (wrapper == null){
            wrapper = new EntityWrapper<OmPositionApp>();
        }

        //应用的分页信息
        Page<AcApp> pageApp = new Page<AcApp>();
        List<AcApp> listApp = new ArrayList<AcApp>();

        //查询某岗位的应用权限信息
        wrapper.eq(OmPositionApp.COLUMN_GUID_POSITION,id);
        Page<OmPositionApp> positionAppPage = selectPage(page,wrapper);

        //岗位应用权限信息集合
        List<OmPositionApp> list = positionAppPage.getRecords();

        //根据应用权限集合查询出应用信息
        System.out.println("**********************************"+list.size());
        for (OmPositionApp omPositionApp : list){
            if (null != omPositionApp){
                AcApp acApp = acAppService.selectById(omPositionApp.getGuidApp());
                System.out.println("**********************************"+acApp);
                if (acApp != null){
                    listApp.add(acApp);
                }
            }
        }

        //收集分页信息
        pageApp.setSize(page.getSize());
        pageApp.setCurrent(page.getCurrent());
        pageApp.setTotal(page.getTotal());
        pageApp.setRecords(listApp);

        return pageApp;
    }


    @Override
    public List<AcApp> queryNotInPosition(String id) throws OrgManagementException {

        try {

            //查询岗位下的应用权限
            Wrapper<OmPositionApp> wrapperPosition = new EntityWrapper<OmPositionApp>();
            wrapperPosition.eq(OmPositionApp.COLUMN_GUID_POSITION,id);
            List<OmPositionApp> listPosition = selectList(wrapperPosition);
            List<AcApp> listPositionApp = new ArrayList<AcApp>();
            if (null != listPosition || 0 != listPosition.size()){
                //查询出所有的应用
                for (OmPositionApp omPositionApp : listPosition){
                    if (null != omPositionApp){
                        AcApp acApp = acAppService.selectById(omPositionApp.getGuidApp());
                        listPositionApp.add(acApp);
                    }
                }
            }

            //查询所有的应用
            Wrapper<AcApp> wrapperApp = new EntityWrapper<AcApp>();
            List<AcApp> listApp = acAppService.selectList(wrapperApp);

            //从所有的应用中除去岗位下的应用
            listApp.removeAll(listPositionApp);

            return listApp;
        }catch (Exception e){
            e.printStackTrace();
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_APP_POSITION,wrap(e.getMessage()));
        }
    }
}

