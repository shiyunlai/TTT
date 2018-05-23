package org.tis.tools.abf.module.ac.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;


import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/5/16 11:46
 */
public class AcFuncAttrServiceImplTest extends BaseTest {

    @Autowired
    IAcFuncAttrService acFuncAttrService;

    /**
     * 测试新增
     * @throws Exception
     */
    @Test
    public void add() throws Exception{

        String guidFunc = "995937631999619074";
        String attrType = "3的类型";
        String attrKey = "003";
        String attrValue = "003Value";
        String memo = "003的描述";

        acFuncAttrService.creatFuncAttr(guidFunc,attrType,attrKey,attrValue,memo);

    }

    /**
     * 测试修改
     * @throws Exception
     */
    @Test
    public void change() throws Exception{

        String guid = "998761475143598082";
        String guidFunc = "996295466776137729";
        String attrType = "10的类型";
        String attrKey = "010";
        String attrValue = "010Value";
        String memo = "010的描述";

        acFuncAttrService.changeFuncAttr(guid,guidFunc,attrType,attrKey,attrValue,memo);

    }

    /**
     * 测试查询功能属性列表
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception{
        String id = "996295466776137729";
        List<AcFuncAttr> list = acFuncAttrService.queryList(id);

        for (AcFuncAttr acFuncAttr:list) {
            System.out.println(acFuncAttr);
        }
    }

}
