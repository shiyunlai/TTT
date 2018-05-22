package org.tis.tools.abf.module.ac.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;

import java.math.BigDecimal;

/**
 * Created by chenchao
 * Created on 2018/5/10 21:01
 */
public class AcAppConfigServiceImplTest extends BaseTest{

    @Autowired
    IAcAppConfigService acAppConfigService;

    @Test
    public void createRootAppConfig(){

        String guidApp = "989411000669696001";
        String configType= "004";
        String configName= "应用4";
        String configDict= "local";
        String configStyle= "4";
        String configValue= "默认值04";
        String enabled= "N";
        String displayOrder= "1";
        String configDesc= "应用4的描述新增";

        acAppConfigService.createRootAppConfig(guidApp,configType,configName,configDict,configStyle,configValue,enabled,displayOrder,configDesc);
    }

    @Test
    public void changeById(){

        String guid = "994566257687052289";
        String guidApp = "989411000669696001";
        String configType= "004";
        String configName= "应用4";
        String configDict= "local";
        String configStyle= "4";
        String configValue= "默认值04";
        String enabled= "Y";
        String displayOrder= "6";
        String configDesc= "应用4的描述修改";

        acAppConfigService.changeById(guid,guidApp,configType,configName,configDict,configStyle,configValue,enabled,displayOrder,configDesc);
    }

}
