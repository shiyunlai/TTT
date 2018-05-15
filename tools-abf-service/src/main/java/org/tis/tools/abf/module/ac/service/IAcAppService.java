package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * acApp的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcAppService extends IService<AcApp>  {

    /**
     * 应用新增
     * @param appCode 应用代码
     * @param appName 应用名称
     * @param appType 应用类型
     * @param url     访问地址
     * @param ipAddr  IP
     * @param ipPort  端口
     * @param appDesc 应用描述
     * @return  AcApp
     */
    AcApp creatRootApp(String appCode, String appName, AcAppType appType, String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException;

    /**
     *
     * @param guid
     * @param appCode  应用代码
     * @param appName  应用名称
     * @param appType  应用类型
     * @param isopen   是否开通应用
     * @param openDate 开通日期
     * @param url      访问地址
     * @param ipAddr   IP
     * @param ipPort   端口
     * @param appDesc  应用描述
     * @return  AcApp
     * @throws AcManagementException
     */
    AcApp changeById(String guid,String appCode, String appName, AcAppType appType,YON isopen,Date openDate ,String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException;

    /**
     *
     * @return List<AcApp>
     * @throws AcManagementException
     */
    List<AcApp> selectAppList() throws AcManagementException;
}

