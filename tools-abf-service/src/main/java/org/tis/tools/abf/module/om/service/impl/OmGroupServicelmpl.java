package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.tis.tools.abf.module.om.dao.OmGroupMapper;
import org.tis.tools.abf.module.om.entity.OmGroup;
import org.tis.tools.abf.module.om.service.IOmGroupService;
import org.tis.tools.common.utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tis.tools.common.utils.BasicUtil.wrap;

public class OmGroupServicelmpl extends ServiceImpl<OmGroupMapper,OmGroup> implements IOmGroupService {

    @Override
    public void update(OmGroup t) {

    }

    @Override
    public void updateForce(OmGroup t) {

    }

    @Override
    public void delete(String guid) {

    }

    @Override
    public OmGroup loadByGuid(String guid) {
        return null;
    }
}
