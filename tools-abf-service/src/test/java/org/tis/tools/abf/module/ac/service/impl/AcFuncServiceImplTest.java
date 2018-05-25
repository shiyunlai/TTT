package org.tis.tools.abf.module.ac.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.service.IAcFuncService;

/**
 * 功能的Service实现类的测试类
 * Created by chenchao
 * Created on 2018/5/14 10:44
 */
public class AcFuncServiceImplTest extends BaseTest {

    @Autowired
    IAcFuncService acFuncService;

    /**
     * 新增子功能
     * @throws Exception
     */
    @Test
    public void creatFunc() throws Exception{

        String guidApp = "989411000669696001";
        String funcType = "TWSTX";
        String funcCode = "003";
        String funcName = "003应用";
        String displayOrder = "2";
        String funcDesc = "003的描述";
        String guidFunc = "996295466776137729";
        String isopen ="yes";
        String ischeck = "yes";
        acFuncService.creatFunc(guidApp,funcType,funcCode,funcName,displayOrder,funcDesc,guidFunc,isopen,ischeck);

    }

    /**
     * 测试修改
     * @throws Exception
     */
    @Test
    public void changeFunc() throws Exception{
        String guid = "995937977815744514";
        String guidApp = "989411000669696001";
        String funcCode = "004";
        String funcName = "004应用";
        String displayOrder = "5";
        String funcDesc = "004的修改描述";
        String guidFunc = "0";
        String  isopen = "YES";
        String ischeck = "NO";
        String funcType ="RESTFUL";

        acFuncService.changeFunc(guid,guidApp,funcType,funcCode,funcName,funcDesc,isopen,ischeck,displayOrder,guidFunc);

    }

}
