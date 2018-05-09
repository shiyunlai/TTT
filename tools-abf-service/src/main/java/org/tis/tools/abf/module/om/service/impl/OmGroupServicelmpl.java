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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OmGroupServicelmpl extends ServiceImpl<OmGroupMapper,OmGroup> implements IOmGroupService {

}
