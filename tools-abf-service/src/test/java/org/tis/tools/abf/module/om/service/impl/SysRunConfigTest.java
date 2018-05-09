package org.tis.tools.abf.module.om.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.tis.tools.abf.module.sys.service.ISysRunConfigService;

import java.util.List;

public class SysRunConfigTest extends BaseTest{
    @Autowired
    ISysRunConfigService iSysRunConfigService;
    @Test
    public void SelectSys(){
//       List<SysRunConfig> list = iSysRunConfigService.queryAllSysRunConfig();
//       for (int i=0;i<list.size();i++){
//           System.out.println(list.get(i));
//       }
    }
    @Test
    public void add(){
        SysRunConfig sysRunConfig = new SysRunConfig();
        sysRunConfig.setGuidApp("CS");
        sysRunConfig.setGroupName("测试");
        sysRunConfig.setKeyName("测试");
        sysRunConfig.setValue("CS");
        sysRunConfig.setValue("测试数据");
        sysRunConfig.setDescription("cscscsc");
        sysRunConfig.setValueFrom("bishu");
//        iSysRunConfigService.createSysRunConfig(sysRunConfig);
    }
    @Test
    public void update(){
        SysRunConfig sysRunConfig = new SysRunConfig();
        sysRunConfig.setGuid("");
        sysRunConfig.setGuidApp("CSa");
        sysRunConfig.setGroupName("测试a");
        sysRunConfig.setKeyName("测试a");
        sysRunConfig.setValue("CSa");
        sysRunConfig.setValue("测试数据a");
        sysRunConfig.setDescription("cscscsca");
        sysRunConfig.setValueFrom("bishussd");
//        iSysRunConfigService.editSysRunConfig(sysRunConfig);
    }
    @Test
    public void delete(){
//        iSysRunConfigService.deleteSysRunConfig("986179571516792834");
    }

}
