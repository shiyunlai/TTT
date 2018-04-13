
/**
 * Copyright (C) 2016 bronsp.com, All rights reserved.
 */
package org.tis.tools.abf.module.om.service;


import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.entity.OmDuty;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

import java.util.List;


/**
 * <pre>
 * 职务定义表(OM_DUTY)的基础远程服务接口定义
 * </pre>
 *
 * @autor generated by tools:gen-dao
 *
 */
public interface IOmDutyService extends IService<OmDuty> {

	/**
	 * 新增职务定义表(OM_DUTY)
	 * @param t 新记录
	 */
	public void addOmDuty(OmDuty t);

	/**
	 * 更新职务定义表(OM_DUTY),只修改t对象有值的字段
	 * @param t 新值
	 */
	public void update(OmDuty t);

	/**
	 * 强制更新职务定义表(OM_DUTY)
	 * @param t 新值
	 */
	public void updateForce(OmDuty t);

	/**
	 * 删除职务定义表(OM_DUTY)
	 * @param guid 记录guid
	 */
	public void delete(String guid);

//	/**
//	 * 根据条件删除职务定义表(OM_DUTY)
//	 * @param wc 条件
//	 */
//	public void deleteByCondition(WhereCondition wc);
//
//	/**
//	 * 根据条件更新职务定义表(OM_DUTY)
//	 * @param wc 条件
//	 * @param t 新值
//	 */
//	public void updateByCondition(WhereCondition wc, OmDuty t);
//
//	/**
//	 * 根据条件查询职务定义表(OM_DUTY)
//	 * @param wc 条件
//	 * @return 满足条件的记录list
//	 */
//	public List<OmDuty> query(WhereCondition wc);
//
//	/**
//	 * 根据条件统计职务定义表(OM_DUTY)记录数
//	 * @param wc 条件
//	 * @return 记录数
//	 */
//	public int count(WhereCondition wc);

	/**
	 * 根据id查询职务定义表(OM_DUTY)记录
	 * @param guid 记录guid
	 * @return 匹配的记录
	 */
	public OmDuty loadByGuid(String guid);
}
