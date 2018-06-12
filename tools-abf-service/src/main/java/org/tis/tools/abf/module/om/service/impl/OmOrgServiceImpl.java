/**
 * 
 */
package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmOrgUpdateRequest;
import org.tis.tools.abf.module.om.dao.OmOrgMapper;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.enums.OmOrgArea;
import org.tis.tools.abf.module.om.entity.enums.OmOrgDegree;
import org.tis.tools.abf.module.om.entity.enums.OmOrgStatus;
import org.tis.tools.abf.module.om.entity.enums.OmOrgType;
import org.tis.tools.abf.module.om.entity.vo.OmOrgDetail;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmOrgService;
import org.tis.tools.abf.module.om.service.IOrgCodeGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;


/**
 * <pre>
 * 机构（Organization）管理服务功能的实现类
 * 
 * <pre>
 * 
 * @author megapro
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrgServiceImpl extends ServiceImpl<OmOrgMapper, OmOrg> implements IOmOrgService {

	/** 拷贝新增时，代码前缀  */
	private static final String CODE_HEAD_COPYFROM = "Copyfrom-";

	@Autowired
	private IOrgCodeGenerator orgCodeGenerator;


	/**
	 * 生成机构代码
	 *
	 * @param areaCode  区域代码
	 * @param orgDegree 机构等级
	 * @return 机构代码
	 * @throws OrgManagementException
	 */
	@Override
	public String genOrgCode(String areaCode, String orgDegree) throws OrgManagementException {
		return orgCodeGenerator.genOrgCode(orgDegree, areaCode);
	}

	/**
	 *
	 * @param areaCode
	 * 			  区域代码
	 * @param orgDegree
	 *            机构等级
	 * @param orgName
	 *            机构名称
	 * @param orgType
	 *            机构类型
	 * @return
	 * @throws OrgManagementException
	 */
	@Override
	public OmOrg createRootOrg(OmOrgArea areaCode, OmOrgDegree orgDegree, String orgName, OmOrgType orgType,String orgAddr,String linkMan,String linkTel,Date startDate,Date endDate,String remark)
			throws OrgManagementException {

		OmOrg org = new OmOrg();
		// 补充信息
//		org.setGuid(GUID.org());// 补充GUID
		// 补充机构状态，新增机构初始状态为 停用
		org.setOrgStatus(OmOrgStatus.STOP);
		// 补充父机构，根节点没有父机构,设置为默认值0
		org.setGuidParents("");
		// 新增节点都先算叶子节点 Y
		org.setIsleaf(YON.YES);
		// 设置机构序列,根机构直接用guid
		org.setOrgSeq(org.getGuid());
		//设置排序字段
		EntityWrapper<OmOrg> wrapper = new EntityWrapper<>();
		wrapper.isNull(OmOrg.COLUMN_GUID_PARENTS);
		org.setSortNo(new BigDecimal(selectCount(wrapper)));
		// 收集入参
		org.setOrgCode(genOrgCode(orgDegree.toString(), areaCode.toString()));
		org.setOrgName(orgName);
		org.setOrgType(orgType);
		org.setOrgDegree(orgDegree);
		org.setArea(areaCode);
		org.setOrgAddr(orgAddr);
		org.setLinkMan(linkMan);
		org.setLinkTel(linkTel);
		org.setStartDate(startDate);
		org.setEndDate(endDate);
		org.setRemark(remark);
		insert(org);
		return org;
	}

	@Override
	public OmOrg createChildOrg(OmOrgArea areaCode, OmOrgDegree orgDegree, String orgName, OmOrgType orgType, String guidParents,String orgAddr,String linkMan,String linkTel,Date startDate,Date endDate,String remark)
			throws OrgManagementException {
		// 查询父机构信息
		OmOrg parentsOrg = selectById(guidParents);
		if(parentsOrg == null) {
			throw new OrgManagementException(
					OMExceptionCodes.ORGANIZATION_NOT_EXIST_BY_ORG_CODE, wrap(guidParents));
		}
		String parentsOrgSeq = parentsOrg.getOrgSeq();
		OmOrg org = new OmOrg();
		// 补充信息
		// 补充机构状态，新增机构初始状态为 停用
		org.setOrgStatus(OmOrgStatus.STOP);
		// 补充父机构，根节点没有父机构
		org.setGuidParents(parentsOrg.getGuid());
		// 新增节点都先算叶子节点 Y
		org.setIsleaf(YON.YES);
		String newOrgSeq = parentsOrgSeq + "." + org.getGuid();
		// 设置机构序列,根据父机构的序列+"."+机构的GUID
		org.setOrgSeq(newOrgSeq);
		//设置排序字段
		EntityWrapper<OmOrg> wrapper = new EntityWrapper<>();
		wrapper.isNull(OmOrg.COLUMN_GUID_PARENTS);
		org.setSortNo(new BigDecimal(selectCount(wrapper)));
		// 收集入参
		org.setOrgCode(orgCodeGenerator.genOrgCode(orgDegree.toString(), areaCode.toString()));
		org.setOrgName(orgName);
		org.setOrgType(orgType);
		org.setOrgDegree(orgDegree);
		org.setArea(areaCode);
		org.setOrgAddr(orgAddr);
		org.setLinkMan(linkMan);
		org.setLinkTel(linkTel);
		org.setStartDate(startDate);
		org.setEndDate(endDate);
		org.setRemark(remark);
		// 更新父节点机构 是否叶子节点 节点数 最新更新时间 和最新更新人员
		parentsOrg.setIsleaf(YON.NO);
		insert(org);//新增子节点
		updateById(parentsOrg);//更新父节点
		return org;
	}

	@Override
	public boolean changeOrg(OmOrgUpdateRequest omOrgUpdateRequest) throws OrgManagementException {

		boolean isexist = false;

		try {

		Wrapper<OmOrg> wrapper = new EntityWrapper<>();
		List<OmOrg> lists = selectList(wrapper);

		for (OmOrg omOrg:lists){
			if (omOrg.getOrgCode().equals(omOrgUpdateRequest.getOrgCode()) && !(omOrg.getGuid().equals
					(omOrgUpdateRequest.getGuid()))){
				return isexist;
			}
		}

		isexist = true;

		OmOrg omOrg = new OmOrg();

		//收集参数
		omOrg.setGuid(omOrgUpdateRequest.getGuid());
		omOrg.setOrgCode(omOrgUpdateRequest.getOrgCode());
		omOrg.setOrgName(omOrgUpdateRequest.getOrgName());
		omOrg.setOrgDegree(omOrgUpdateRequest.getOrgDegree());
		omOrg.setOrgType(omOrgUpdateRequest.getOrgType());
		omOrg.setOrgStatus(omOrgUpdateRequest.getOrgStatus());
		omOrg.setGuidParents(omOrgUpdateRequest.getGuidParents());
		omOrg.setOrgSeq(omOrgUpdateRequest.getOrgSeq());
		omOrg.setOrgAddr(omOrgUpdateRequest.getOrgAddr());
		omOrg.setLinkMan(omOrgUpdateRequest.getLinkMan());
		omOrg.setLinkTel(omOrgUpdateRequest.getLinkTel());
		omOrg.setStartDate(omOrgUpdateRequest.getStartDate());
		omOrg.setEndDate(omOrgUpdateRequest.getEndDate());
		omOrg.setArea(omOrgUpdateRequest.getArea());
		omOrg.setSortNo(omOrgUpdateRequest.getSortNo());
		omOrg.setIsleaf(omOrgUpdateRequest.getIsleaf());
		omOrg.setRemark(omOrgUpdateRequest.getRemark());


		updateById(omOrg);
		}catch (Exception e){
			e.printStackTrace();
			throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_UPDATE_ORG_APP,wrap(e.getMessage()));
		}

		return isexist;
	}


	@Override
	public OmOrgDetail queryOrgTree(String id) throws OrgManagementException {

		OmOrgDetail omOrgDetail = new OmOrgDetail();

		try {
			//创建子机构的list
			List<OmOrg> list = new ArrayList<OmOrg>();

			if ("null".equals(id)){
				//查询子机构字典
				Wrapper<OmOrg> wrapper = new EntityWrapper<OmOrg>();
				wrapper.isNull(OmOrg.COLUMN_GUID_PARENTS);

				List<OmOrg> queryList = selectList(wrapper);

				for (OmOrg omOrgQuery: queryList) {
					list.add(omOrgQuery);
				}

			}else {
				OmOrg omOrg = selectById(id);

				//查询子机构字典
				Wrapper<OmOrg> wrapper = new EntityWrapper<OmOrg>();
				wrapper.eq(OmOrg.COLUMN_GUID_PARENTS,id);

				List<OmOrg> queryList = selectList(wrapper);

				for (OmOrg omOrgQuery: queryList) {
					list.add(omOrgQuery);
				}

				//收集查询出来的结果
				omOrgDetail.setGuid(omOrg.getGuid());
				omOrgDetail.setOrgCode(omOrg.getOrgCode());
				omOrgDetail.setOrgName(omOrg.getOrgName());
				omOrgDetail.setOrgDegree(omOrg.getOrgDegree());
				omOrgDetail.setOrgType(omOrg.getOrgType());
				omOrgDetail.setOrgStatus(omOrg.getOrgStatus());
				omOrgDetail.setGuidParents(omOrg.getGuidParents());
				omOrgDetail.setOrgSeq(omOrg.getOrgSeq());
				omOrgDetail.setOrgAddr(omOrg.getOrgAddr());
				omOrgDetail.setLinkMan(omOrg.getLinkMan());
				omOrgDetail.setLinkTel(omOrg.getLinkTel());
				omOrgDetail.setStartDate(omOrg.getStartDate());
				omOrgDetail.setEndDate(omOrg.getEndDate());
				omOrgDetail.setArea(omOrg.getArea());
				omOrgDetail.setSortNo(omOrg.getSortNo());
				omOrgDetail.setIsleaf(omOrg.getIsleaf());
				omOrgDetail.setRemark(omOrg.getRemark());
			}

			omOrgDetail.setChildren(list);

		}catch (Exception e){
			e.printStackTrace();
			throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_ORG_TREE,wrap(e.getMessage()));
		}

		return omOrgDetail;
	}


	@Override
	public void delectRoot(String id) throws OrgManagementException {

		try {
			//删除父机构下的所有子机构
			deleteAllChild(id);

		}catch (Exception e){
			e.printStackTrace();
			throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_ROOT_ORG,wrap(e.getMessage()));
		}
	}

	//删除父机构下的所有子机构
	public void deleteAllChild(String id){
		//首先删除父机构对应的子机构
		Wrapper<OmOrg> wrapper = new EntityWrapper<OmOrg>();
		wrapper.eq(OmOrg.COLUMN_GUID_PARENTS,id);

		//获取子机构列表
		List<OmOrg> lists = selectList(wrapper);

		if (0 == lists.size() || null == lists){
			deleteById(id);
		}else {
			for (OmOrg omOrg :lists){
				deleteAllChild(omOrg.getGuid());
			}
			deleteById(id);
		}
	}

	@Override
	public boolean moveOrg(String orgCode, String fromParentsOrgCode, String toParentsOrgCode, int toSortNo) throws OrgManagementException {
		return false;
	}

	@Override
	public OmOrg copyOrg(String copyFromOrgCode) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg copyOrgDeep(String copyFromOrgCode, boolean copyOrgRole, boolean copyPosition, boolean copyPositionRole, boolean copyGroup, boolean copyGroupRole) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg enabledOrg(String orgCode, Date startDate, Date endDate) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg reenabledOrg(String orgCode) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg disabledOrg(String orgCode) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg cancelOrg(String orgCode) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg deleteEmptyOrg(String orgCode) throws OrgManagementException {
		return null;
	}

	@Override
	public OmOrg queryOrg(String orgCode) {
		return null;
	}

	@Override
	public List<OmOrg> queryOrgsByName(String name) {
		return null;
	}

	@Override
	public List<OmOrg> queryChilds(String orgCode) {
		return null;
	}

	@Override
	public List<OmOrg> queryAllChilds(String orgCode) {
		return null;
	}

	@Override
	public List<OmOrg> queryChildsByCondition(String orgCode, OmOrg orgCondition) {
		return null;
	}

	@Override
	public List<OmOrg> queryAllRoot() {
		return null;
	}

	@Override
	public List<OmOrg> queryAllOrg() {
		return null;
	}
}
