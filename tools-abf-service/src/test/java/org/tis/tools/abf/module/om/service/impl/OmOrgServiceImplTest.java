package org.tis.tools.abf.module.om.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.service.IOmOrgService;

import static org.junit.Assert.*;

public class OmOrgServiceImplTest extends BaseTest {

    @Autowired
    IOmOrgService omOrgService;


    @Test
    public void genOrgCode() {
    }

    @Test
    public void createRootOrg() {
       String areaCode = "CS";
        String orgDegree = "CS";
        String orgName = "CS";
        String orgType = "CS" ;
        OmOrg omOrg = omOrgService.queryOrg("981360076294012929");
        System.out.println(omOrg.getArea());
        System.out.println(omOrg.getCreateTime());
        System.out.println(omOrg.getEndDate());
        System.out.println(omOrg.getGuidParents());
        System.out.println(omOrg.getLinkMan());
        System.out.println(omOrg.getOrgType());
        System.out.println(omOrg.getSortNo());

//        omOrgService.queryAllRoot();
//        OmOrg rootOrg = omOrgService.createRootOrg(areaCode, orgDegree, orgName, orgType);
//        Assert.assertEquals(rootOrg.getOrgName(), orgName);
//        Assert.assertEquals(rootOrg.getOrgType(), orgType);
//        Assert.assertEquals(rootOrg.getOrgDegree(), orgDegree);
    }
}