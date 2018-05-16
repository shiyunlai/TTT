package org.tis.tools.abf.module.ac.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 应用的service测试类
 * Created by chenchao
 * Created on 2018/5/2 14:12
 */
public class AcAppServiceImplTest extends BaseTest {

    @Autowired
    IAcAppService acAppService;

    @Test
    public void creatRootApp(){

        AcAppType appType = AcAppType.LOCAL;
        String appName = "22";
        String appCode = "33";
        String appDesc = "44";
        String url = "http://123.com";
        String ipAddr = "66";
        String ipPort = "77";
        acAppService.creatRootApp(appCode,appName,appType,url,ipAddr,ipPort,appDesc);

    }

    @Test
    public void updateById(){

        String guid = "994499587048157186";
        YON isopen = YON.YES;
        Date openDate = new Date();
        AcAppType appType = AcAppType.REMOTE;
        String appName = "222";
        String appCode = "333";
        String appDesc = "444";
        String url = "http://1234.com";
        String ipAddr = "666";
        String ipPort = "777";

        acAppService.changeById(guid,appCode,appName,appType,isopen,openDate,url,ipAddr,ipPort,appDesc);
    }

    @Test
    public void selectAppList(){

        List<AcApp> acAppList = acAppService.selectAppList();
       for(AcApp acApp : acAppList){
           System.out.println(acApp.toString());
       }

    }

}
