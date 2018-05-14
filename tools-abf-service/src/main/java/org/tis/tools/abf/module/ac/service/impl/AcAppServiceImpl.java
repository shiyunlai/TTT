package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcAppMapper;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.*;

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

    /**
     * 应用新增
     */
    @Override
    public AcApp creatRootApp(String appCode, String appName, AcAppType appType, String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException {
        AcApp app = new AcApp();

        /**
         * 是否开通:取值来自业务菜单： DICT_YON
         * 默认为N，新建后，必须执行应用开通操作，才被开通。
         */
        app.setIsopen(YON.NO);
        /**
         * 开通时间:记录到时分秒
         */
        app.setOpenDate(new Date());
        try{
            //收集入参
            app.setAppType(appType);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);

            insert(app);
        }catch (Exception exception){
            throw  exception;
        }
        return app;
    }

    /**
     * 应用列表
     * @throws AcManagementException
     */
    @Override
    public List<AcApp> selectAppList() throws AcManagementException {

        List<AcApp> appList = null;
        EntityWrapper<AcApp> wrapper1 = new EntityWrapper<AcApp>();

        try {
            appList = acAppService.selectList(wrapper1);
        }catch (Exception exception){
            throw exception;
        }

        return appList;
    }

    /**
     * 修改应用
     * @throws AcManagementException
     */
    @Override
    public AcApp changeById(String guid,String appCode, String appName, AcAppType appType, YON isopen, Date openDate, String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException {
        AcApp app = new AcApp();

        try{
            //收集入参
            app.setGuid(guid);
            app.setIsopen(isopen);
            app.setOpenDate(openDate);
            app.setAppType(appType);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);

            updateById(app);
        }catch (Exception exception){
            throw exception;
        }
        return app;
    }
}

