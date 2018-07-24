package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcAppListRequest;
import org.tis.tools.abf.module.ac.dao.AcAppMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.service.IOmPositionAppService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acApp的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppServiceImpl extends ServiceImpl<AcAppMapper, AcApp> implements IAcAppService {

    @Autowired
    IAcAppService acAppService;

    @Autowired
    private IAcAppConfigService acAppConfigService;

    @Autowired
    private IOmPositionAppService omPositionAppService;

    @Autowired
    private IAcFuncService acFuncService;

    /**
     * 应用新增
     */
    @Override
    public AcApp creatRootApp(String appCode, String appName, AcAppType appType, String url, String ipAddr, String ipPort, String appDesc,YON isopen,String openDate) throws AcManagementException {
        AcApp app = new AcApp();

        /**
         * 是否开通:取值来自业务菜单： DICT_YON
         * 默认为N，新建后，必须执行应用开通操作，才被开通。
         */
        if (null == isopen){
            isopen = YON.NO;
        }


        try{
            //处理时间格式
            Date openDateNew = null;
            if (null == openDate || "".equals(openDate)){
                openDateNew = new Date();
            }else {
                openDateNew = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(openDate);
            }

            //收集入参
            app.setAppType(appType);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);
            app.setIsopen(isopen);
            app.setOpenDate(openDateNew);

            insert(app);
        }catch (AcManagementException ae){
            throw ae;
        } catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_APP,
                    wrap(e.getMessage())
            );
        }
        return app;
    }


    /**
     * 修改应用
     * @throws AcManagementException
     */
    @Override
    public AcApp changeById(String guid,String appCode, String appName, AcAppType appType,YON isopen,String openDate ,String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException {
        AcApp app = new AcApp();

        try{
            //处理时间格式
            Date openDateNew = null;
            if (null == openDate || "".equals(openDate)){
                openDateNew = null;
            }else {
                openDateNew = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(openDate);
            }

            //收集入参
            app.setGuid(guid);
            app.setIsopen(isopen);
            app.setOpenDate(openDateNew);
            app.setAppType(appType);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);

            updateById(app);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_APP,
                    wrap(e.getMessage())
            );
        }
        return app;
    }


    @Override
    public List<AcApp> queryAll() throws AcManagementException {

        Wrapper<AcApp> wrapper = new EntityWrapper<AcApp>();

        List<AcApp> list = selectList(wrapper);

        return list;
    }


    @Override
    public List<AcApp> batchQuery(AcAppListRequest acAppListRequest) throws AcManagementException{


        List<AcApp> list = new ArrayList<AcApp>();

        List<String> listGuid = acAppListRequest.getGuidList();


        for (String guid : listGuid) {
                AcApp acApp = selectById(guid);
                if (null == acApp){
                    throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("查询的应用不存在",guid));
                }
                list.add(acApp);
        }

        return list;
    }


    @Override
    public void moveApp(String id) throws AcManagementException {

        try {

            //删除应用的个性化配置
            Wrapper<AcAppConfig> wrapperConfig = new EntityWrapper<AcAppConfig>();
            wrapperConfig.eq(AcAppConfig.COLUMN_GUID_APP,id);
            List<AcAppConfig> listConfig = acAppConfigService.selectList(wrapperConfig);
            if (listConfig.size() != 0){
                for (AcAppConfig acAppConfig : listConfig){
                    if (null != acAppConfig){
                        acAppConfigService.moveAppConfig(acAppConfig.getGuid());
                    }
                }
            }

            //删除岗位的应用权限
            Wrapper<OmPositionApp> wrapperPosition = new EntityWrapper<OmPositionApp>();
            wrapperPosition.eq(OmPositionApp.COLUMN_GUID_APP,id);
            List<OmPositionApp> listPosition = omPositionAppService.selectList(wrapperPosition);
            if (0 != listPosition.size()){
                omPositionAppService.delete(wrapperPosition);
            }

            //删除应用ID对应的功能
            Wrapper<AcFunc> wrapperFunc = new EntityWrapper<AcFunc>();
            wrapperFunc.eq(AcFunc.COLUMN_GUID_APP,id);
            List<AcFunc> listFunc = acFuncService.selectList(wrapperFunc);
            if (0 != listFunc.size()){
                for (AcFunc acFunc :listFunc){
                    if (null != acFunc){
                        acFuncService.moveFunc(acFunc.getGuid());
                    }
                }
            }

            //删除应用信息
            deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_DELETE_AC_APP,wrap(e.getMessage()));
        }
    }
}

