package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.tis.tools.abf.module.common.exception.ToolsRuntimeException;
import org.tis.tools.abf.module.om.dao.OmEmployeeMapper;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.service.*;
import org.tis.tools.common.utils.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static org.tis.tools.common.utils.BasicUtil.wrap;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmployeeServicelmpl extends ServiceImpl<OmEmployeeMapper,OmEmployee> implements IOmEmployeeService {
	/**
	 * 拷贝新增时，代码前缀
	 */
	private static final String CODE_HEAD_COPYFROM = "Copyfrom-";
	@Override
	public void update(OmEmployee t) {

	}

	@Override
	public void updateForce(OmEmployee t) {

	}

	@Override
	public void delete(String guid) {

	}

	@Override
	public OmEmployee loadByGuid(String guid) {
		return null;
	}
}
