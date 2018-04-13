package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.tis.tools.abf.module.om.dao.OmPositionMapper;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.service.IOmPositionService;
import org.tis.tools.common.utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tis.tools.common.utils.BasicUtil.wrap;
@Service
@Transactional(rollbackFor = Exception.class)
public class OmPositionServiceImpl extends ServiceImpl<OmPositionMapper,OmPosition> implements IOmPositionService {
	/**
	 * 拷贝新增时，代码前缀
	 */
	private static final String CODE_HEAD_COPYFROM = "Copyfrom-";
	@Override
	public void update(OmPosition t) {

	}

	@Override
	public void updateForce(OmPosition t) {

	}

	@Override
	public void delete(String guid) {

	}

	@Override
	public OmPosition loadByGuid(String guid) {
		return null;
	}
}
