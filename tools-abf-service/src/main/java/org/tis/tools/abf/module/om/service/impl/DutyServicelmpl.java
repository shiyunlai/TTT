package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.tis.tools.abf.module.om.dao.OmAppGroupMapper;
import org.tis.tools.abf.module.om.dao.OmDutyMapper;
import org.tis.tools.abf.module.om.entity.OmDuty;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class DutyServicelmpl extends ServiceImpl<OmDutyMapper,OmDuty> implements IOmDutyService {
	/** 拷贝新增时，代码前缀  */
	private static final String CODE_HEAD_COPYFROM = "Copyfrom-";
	@Autowired
	IOmDutyService omDutyService;

	@Override
	public void addOmDuty(OmDuty t) {

	}

	@Override
	public void update(OmDuty t) {

	}

	@Override
	public void updateForce(OmDuty t) {

	}

	@Override
	public void delete(String guid) {
		OmDuty om = new OmDuty();
		om.setDutyCode("AAAA");
		om.getDutyCode();
	}

	@Override
	public OmDuty loadByGuid(String guid) {
		return null;
	}
}
