package org.tis.tools.abf.module.om.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.service.IOmDutyService;
import org.tis.tools.abf.module.om.service.IOmOrgService;

public class AcTestServiceImpTest extends BaseTest {
    @Autowired
    IOmDutyService AcTestCodeGenerator;

    @Test
    public void createRootOrg() {
       AcTestCodeGenerator.delete("123123123");

    }
}
