package org.tis.tools.abf.module.om.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.enums.OmOrgArea;
import org.tis.tools.abf.module.om.entity.enums.OmOrgDegree;
import org.tis.tools.abf.module.om.entity.enums.OmOrgType;
import org.tis.tools.abf.module.om.service.IOmOrgService;

import java.util.Date;

public class OmOrgServiceImplTest extends BaseTest {

    @Autowired
    IOmOrgService omOrgService;


    @Test
    public void genOrgCode() {
    }

    @Test
    public void createRootOrg() {
        OmOrgArea areaCode = OmOrgArea.BEIJING;
        OmOrgDegree orgDegree = OmOrgDegree.BRANCH;
        String orgName = "1";
        String orgAddr = "1111";
        String linkMan = "1111";
        String linkTel = "1111";
        Date startDate = new Date();
        Date endDate = new Date();
        String remark = "1";
        OmOrgType orgType = OmOrgType.BRANCHOFFICE;
        OmOrg rootOrg = omOrgService.createRootOrg(areaCode, orgDegree, orgName, orgType,orgAddr,linkMan,linkTel,
                startDate,endDate,remark);
        Assert.assertEquals(rootOrg.getOrgName(), orgName);
        Assert.assertEquals(rootOrg.getOrgType(), orgType);
        Assert.assertEquals(rootOrg.getOrgDegree(), orgDegree);
    }
}