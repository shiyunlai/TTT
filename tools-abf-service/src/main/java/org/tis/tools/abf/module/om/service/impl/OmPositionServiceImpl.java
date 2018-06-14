package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmPositionRequest;
import org.tis.tools.abf.module.om.dao.OmPositionMapper;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.vo.OmPositionDetail;
import org.tis.tools.abf.module.om.entity.vo.OmPositionForParentDetail;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.abf.module.om.service.IOmOrgService;
import org.tis.tools.abf.module.om.service.IOmPositionService;

import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * omPosition的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmPositionServiceImpl extends ServiceImpl<OmPositionMapper, OmPosition> implements IOmPositionService {

    @Autowired
    private IOmOrgService omOrgService;

    @Autowired
    private IOmEmployeeService omEmployeeService;

    @Override
    public boolean addRoot(OmPositionRequest omPositionRequest) throws OrgManagementException {

        boolean isexist = new Boolean(false);

        //确保岗位代码唯一
        Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
        List<OmPosition> list = selectList(wrapper);

        for (OmPosition omPosition : list){
            if (omPosition.getPositionCode().equals(omPositionRequest.getPositionCode())){
                return isexist;
            }
        }

        isexist = true;

        OmPosition omPosition = new OmPosition();

        // 补充父岗位，根节点没有父岗位
        omPosition.setGuidParents("");
        // 新增节点都先算叶子节点 Y
        omPosition.setIsleaf(YON.YES);
        //新建岗位为正常状态
        omPosition.setPositionStatus(OmPositionStatus.RUNNING);

        omPosition.setGuidOrg(omPositionRequest.getGuidOrg());
        omPosition.setPositionCode(omPositionRequest.getPositionCode());
        omPosition.setPositionName(omPositionRequest.getPositionName());
        omPosition.setPositionType(omPositionRequest.getPositionType());
        omPosition.setSubCount(omPositionRequest.getSubCount());
        omPosition.setPositionLevel(omPositionRequest.getPositionLevel());
        omPosition.setPositionSeq(omPositionRequest.getPositionSeq());
        omPosition.setStartDate(omPositionRequest.getStartDate());
        omPosition.setEndDate(omPositionRequest.getEndDate());

        insert(omPosition);
        return isexist;
    }


    @Override
    public boolean addChild(OmPositionRequest omPositionRequest) throws OrgManagementException {

        boolean isexist = new Boolean(false);

        //确保岗位代码唯一
        Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
        List<OmPosition> list = selectList(wrapper);

        for (OmPosition omPosition : list){
            if (omPosition.getPositionCode().equals(omPositionRequest.getPositionCode())){
                return isexist;
            }
        }

        isexist = true;

        //查询父岗位信息
        OmPosition omPositionRoot = selectById(omPositionRequest.getGuidParents());
        if(omPositionRoot == null) {
            throw new OrgManagementException(
                    OMExceptionCodes.POSITION_NOT_EXIST_BY_POSITION_CODE, wrap(omPositionRequest.getGuidParents()));
        }

        OmPosition omPosition = new OmPosition();

        // 新增节点都先算叶子节点 Y
        omPosition.setIsleaf(YON.YES);
        //新建岗位为正常状态
        omPosition.setPositionStatus(OmPositionStatus.RUNNING);

        omPosition.setGuidOrg(omPositionRequest.getGuidOrg());
        omPosition.setPositionCode(omPositionRequest.getPositionCode());
        omPosition.setPositionName(omPositionRequest.getPositionName());
        omPosition.setPositionType(omPositionRequest.getPositionType());
        omPosition.setSubCount(omPositionRequest.getSubCount());
        omPosition.setPositionLevel(omPositionRequest.getPositionLevel());
        omPosition.setPositionSeq(omPositionRequest.getPositionSeq());
        omPosition.setStartDate(omPositionRequest.getStartDate());
        omPosition.setEndDate(omPositionRequest.getEndDate());
        omPosition.setGuidParents(omPositionRequest.getGuidParents());

        // 更新父节点机构 是否叶子节点 节点数 最新更新时间 和最新更新人员
        omPositionRoot.setIsleaf(YON.NO);
        insert(omPosition);
        updateById(omPositionRoot);

        return isexist;
    }


    @Override
    public boolean change(OmPositionRequest omPositionRequest) throws OrgManagementException {

        boolean isexist = new Boolean(false);

        //确保岗位代码唯一
        Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
        List<OmPosition> list = selectList(wrapper);

        for (OmPosition omPosition : list){
            if (omPosition.getPositionCode().equals(omPositionRequest.getPositionCode()) && !(omPosition.getGuid().equals(omPositionRequest.getGuid())) ){
                return isexist;
            }
        }

        isexist = true;

        OmPosition omPosition = new OmPosition();

        omPosition.setGuid(omPositionRequest.getGuid());
        omPosition.setGuidOrg(omPositionRequest.getGuidOrg());
        omPosition.setPositionCode(omPositionRequest.getPositionCode());
        omPosition.setPositionName(omPositionRequest.getPositionName());
        omPosition.setPositionType(omPositionRequest.getPositionType());
        omPosition.setPositionStatus(omPositionRequest.getPositionStatus());
        omPosition.setSubCount(omPositionRequest.getSubCount());
        omPosition.setPositionLevel(omPositionRequest.getPositionLevel());
        omPosition.setPositionSeq(omPositionRequest.getPositionSeq());
        omPosition.setStartDate(omPositionRequest.getStartDate());
        omPosition.setEndDate(omPositionRequest.getEndDate());
        omPosition.setGuidParents(omPositionRequest.getGuidParents());
        omPosition.setIsleaf(omPositionRequest.getIsleaf());

        updateById(omPosition);
        return isexist;
    }


    @Override
    public OmPositionDetail queryPositionTree(String id) throws OrgManagementException {

        OmPositionDetail omPositionDetail = new OmPositionDetail();

        try {
            OmPosition omPosition = selectById(id);

            //创建子功能的list
            List<OmPosition> list = new ArrayList<OmPosition>();

            //查询子功能字典
            Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
            wrapper.eq(OmPosition.COLUMN_GUID_PARENTS,id);

            List<OmPosition> queryList = selectList(wrapper);

            for (OmPosition omPositionQuery: queryList) {
                list.add(omPositionQuery);
            }

            //收集参数
            omPositionDetail.setGuid(omPosition.getGuid());
            omPositionDetail.setGuidOrg(omPosition.getGuidOrg());
            omPositionDetail.setPositionCode(omPosition.getPositionCode());
            omPositionDetail.setPositionName(omPosition.getPositionName());
            omPositionDetail.setPositionType(omPosition.getPositionType());
            omPositionDetail.setPositionStatus(omPosition.getPositionStatus());
            omPositionDetail.setSubCount(omPosition.getSubCount());
            omPositionDetail.setPositionLevel(omPosition.getPositionLevel());
            omPositionDetail.setPositionSeq(omPosition.getPositionSeq());
            omPositionDetail.setStartDate(omPosition.getStartDate());
            omPositionDetail.setEndDate(omPosition.getEndDate());
            omPositionDetail.setGuidParents(omPosition.getGuidParents());
            omPositionDetail.setIsleaf(omPosition.getIsleaf());
            omPositionDetail.setChildren(list);

        }catch (Exception e){
            e.printStackTrace();
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_POSITION_TREE,wrap(e.getMessage()));
        }

        return omPositionDetail;
    }


    @Override
    public void deleteRoot(String id) throws OrgManagementException {

        try {
            //删除父岗位下的所有子岗位
            deleteAllChild(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_ROOT_POSITION,wrap(e.getMessage()));
        }
    }

    //删除父岗位下的所有子岗位
    public void deleteAllChild(String id){
        //首先删除父岗位对应的子岗位
        Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
        wrapper.eq(OmPosition.COLUMN_GUID_PARENTS,id);

        //获取子岗位列表
        List<OmPosition> lists = selectList(wrapper);

        if (0 == lists.size() || null == lists){
            deleteById(id);
        }else {
            for (OmPosition omPosition :lists){
                deleteAllChild(omPosition.getGuid());
            }
            deleteById(id);
        }
    }

    /**
     * 根据岗位ID查询机构
     *
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws OrgManagementException
     */
    @Override
    public Page<OmPositionForParentDetail> treeByOrgId(Page<OmPosition> page, Wrapper<OmPosition> wrapper, String id) throws
            OrgManagementException {

        OmOrg omOrg = omOrgService.selectById(id);
        if (omOrg == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("组织机构ID对应的组织机构不存在",id));
        }

        if (null == wrapper){
            wrapper = new EntityWrapper<OmPosition>();
        }

        wrapper.eq(OmPosition.COLUMN_GUID_ORG,id);
        Page<OmPosition> pageQuuery = selectPage(page,wrapper);
        List<OmPosition> list = pageQuuery.getRecords();

        Page<OmPositionForParentDetail> pageParent = new Page<OmPositionForParentDetail>();
        List<OmPositionForParentDetail> listParent = new ArrayList<OmPositionForParentDetail>();

        for (OmPosition omPosition : list){
            //查询对应机构且在该岗位下的员工数
            int employeeCountQue = 0;
            Wrapper<OmEmployee> wrapperEmp = new EntityWrapper<OmEmployee>();
            System.out.println("***************"+id);
            System.out.println("***************"+omPosition.getGuid());
            wrapperEmp.eq(OmEmployee.COLUMN_GUID_ORG,id);
            wrapperEmp.eq(OmEmployee.COLUMN_GUID_POSITION,omPosition.getGuid());
            employeeCountQue = omEmployeeService.selectList(wrapperEmp).size();
            System.out.println("***************"+employeeCountQue);
            System.out.println("***************"+omEmployeeService.selectCount(wrapperEmp));

            //查询出父岗位的名称
            String parentNameQue = "";
            if ("".equals(omPosition.getGuidParents()) || null == omPosition.getGuidParents()){
            }else {
                OmPosition omPositionForParent = selectById(omPosition.getGuidParents());
                parentNameQue = omPositionForParent.getPositionName();
            }

            OmPositionForParentDetail omPositionForParentDetail = new OmPositionForParentDetail(omPosition,
                    parentNameQue, employeeCountQue);
            listParent.add(omPositionForParentDetail);
        }

        pageParent.setRecords(listParent);
        pageParent.setCurrent(pageQuuery.getCurrent());
        pageParent.setSize(pageQuuery.getSize());
        pageParent.setTotal(pageQuuery.getTotal());

        return pageParent;
    }


    @Override
    public List<OmPosition> QueryAllPosition() throws OrgManagementException {

        Wrapper<OmPosition> wrapper = new EntityWrapper<>();
        List<OmPosition> list = selectList(wrapper);

        return list;
    }


    @Override
    public List<OmPosition> QueryPositionByOrgId(String id) throws OrgManagementException {

        Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
        wrapper.eq(OmPosition.COLUMN_GUID_ORG,id);
        List<OmPosition> list = selectList(wrapper);

        return list;
    }
}

