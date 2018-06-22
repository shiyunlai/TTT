package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeByOrgAndPositionRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.dao.OmEmployeeMapper;
import org.tis.tools.abf.module.om.entity.*;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.abf.module.om.entity.vo.OmEmployeeForPositionDetail;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.om.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;


/**
 * omEmployee的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmployeeServiceImpl extends ServiceImpl<OmEmployeeMapper, OmEmployee> implements IOmEmployeeService {

    @Autowired
    private IOmOrgService omOrgService;

    @Autowired
    private IOmPositionService omPositionService;

    @Autowired
    private IAcOperatorService operatorService;

    @Autowired
    private IOmEmpOrgService empOrgService;

    @Autowired
    private IOmEmpOrgService omEmpOrgService;

    @Autowired
    private IOmEmpPositionService omEmpPositionService;

    @Autowired
    private IOmEmpGroupService omEmpGroupService;


    @Override
    public List<OmEmployee> queryEmployeeByGuid(String orgGuid) {
        EntityWrapper<OmEmpOrg> omEmpOrgEntityWrapper = new EntityWrapper<>();
        omEmpOrgEntityWrapper.eq(OmEmpOrg.COLUMN_GUID_ORG, orgGuid);
        List<OmEmpOrg> list = empOrgService.selectList(omEmpOrgEntityWrapper);
        if(list.isEmpty()){
            return null;
        }else{
            List<String> list2 = new ArrayList<>();
            for (OmEmpOrg oeo : list) {
                list2.add(oeo.getGuidEmp());
            }
            EntityWrapper<OmEmployee> omEmployeeEntityWrapper = new EntityWrapper<>();
            omEmployeeEntityWrapper.in(OmEmployee.COLUMN_GUID, list2);
            List<OmEmployee> omEmployeeList = this.baseMapper.selectList(omEmployeeEntityWrapper);
            return omEmployeeList;
        }
    }

    @Override
    public boolean add(OmEmployeeAddRequest omEmployeeAddRequest) throws OrgManagementException {

        //判断机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmployeeAddRequest.getGuidOrg());
        if (omOrg == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("组织GUID对应的组织不存在",omEmployeeAddRequest.getGuidOrg()));
        }

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omEmployeeAddRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_POSITION,wrap("岗位GUID对应的岗位不存在",omEmployeeAddRequest.getGuidPosition()));
        }


        boolean isexist = new Boolean(false);

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        List<OmEmployee> list = selectList(wrapper);

        for (OmEmployee omEmployee : list) {
            if (omEmployee.getEmpCode().equals(omEmployeeAddRequest.getEmpCode())) {
                return isexist;
            }
        }

        isexist = true;

        OmEmployee omEmployee = new OmEmployee();

        //新建人员后停留在在招状态
        omEmployee.setEmpstatus(OmEmployeeStatus.OFFER);


        //收集参数
        omEmployee.setEmpCode(omEmployeeAddRequest.getEmpCode());
        omEmployee.setEmpName(omEmployeeAddRequest.getEmpName());
        omEmployee.setEmpRealname(omEmployeeAddRequest.getEmpRealname());
        omEmployee.setGender(omEmployeeAddRequest.getGender());
        omEmployee.setGuidOrg(omEmployeeAddRequest.getGuidOrg());
        omEmployee.setGuidOperator(omEmployeeAddRequest.getGuidOperator());
        omEmployee.setGuidPosition(omEmployeeAddRequest.getGuidPosition());
        omEmployee.setGuidEmpMajor(omEmployeeAddRequest.getGuidEmpMajor());
        omEmployee.setIndate(omEmployeeAddRequest.getIndate());
        omEmployee.setOutdate(omEmployeeAddRequest.getOutdate());
        omEmployee.setMobileno(omEmployeeAddRequest.getMobileno());
        omEmployee.setPaperType(omEmployeeAddRequest.getPaperType());
        omEmployee.setPaperNo(omEmployeeAddRequest.getPaperNo());
        omEmployee.setBirthdate(omEmployeeAddRequest.getBirthdate());
        omEmployee.setHtel(omEmployeeAddRequest.getHtel());
        omEmployee.setHaddress(omEmployeeAddRequest.getHaddress());
        omEmployee.setHzipcode(omEmployeeAddRequest.getHzipcode());
        omEmployee.setUserId(omEmployeeAddRequest.getUserId());
        omEmployee.setRemark(omEmployeeAddRequest.getRemark());

        insert(omEmployee);

        //为员工隶属机构新增一条信息
        OmEmpOrg omEmpOrg = new OmEmpOrg();
        omEmpOrg.setGuidOrg(omEmployee.getGuidOrg());
        omEmpOrg.setGuidEmp(omEmployee.getGuid());
        omEmpOrg.setIsmain(YON.YES);
        omEmpOrgService.insert(omEmpOrg);

        OmEmpPosition omEmpPosition = new OmEmpPosition();
        omEmpPosition.setGuidPosition(omEmployee.getGuidPosition());
        omEmpPosition.setGuidEmp(omEmployee.getGuid());
        omEmpPosition.setIsmain(YON.YES);
        omEmpPositionService.insert(omEmpPosition);

        return isexist;
    }

    @Override
    public boolean change(OmEmployeeUpdateRequest omEmployeeUpdateRequest) throws OrgManagementException {

        //判断机构是否存在
        OmOrg omOrg = omOrgService.selectById(omEmployeeUpdateRequest.getGuidOrg());
        if (omOrg == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_ORG,wrap("组织GUID对应的组织不存在",omEmployeeUpdateRequest
                    .getGuidOrg()));
        }

        //判断岗位是否存在
        OmPosition omPosition = omPositionService.selectById(omEmployeeUpdateRequest.getGuidPosition());
        if (omPosition == null){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_OM_POSITION,wrap("岗位GUID对应的岗位不存在",omEmployeeUpdateRequest
                    .getGuidPosition()));
        }


        //如果操作员ID存在,判断操作员是否存在
        if (!"".equals(omEmployeeUpdateRequest.getGuidOperator())){
            AcOperator acOperator = operatorService.selectById(omEmployeeUpdateRequest.getGuidOperator());
            if (acOperator == null){
                throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("应用GUID对应的应用不存在",omEmployeeUpdateRequest.getGuidOperator()));
            }
        }

        boolean isexist = new Boolean(false);

        Wrapper<OmEmployee> wrapper = new EntityWrapper<OmEmployee>();
        List<OmEmployee> list = selectList(wrapper);

        for (OmEmployee omEmployee : list){
            if (omEmployee.getEmpCode().equals(omEmployeeUpdateRequest.getEmpCode()) && !(omEmployee.getGuid().equals(omEmployeeUpdateRequest.getGuid()))){
                return isexist;
            }
        }

        isexist = true;

        OmEmployee omEmployee = new OmEmployee();

        //收集参数
        omEmployee.setGuid(omEmployeeUpdateRequest.getGuid());
        omEmployee.setEmpCode(omEmployeeUpdateRequest.getEmpCode());
        omEmployee.setEmpName(omEmployeeUpdateRequest.getEmpName());
        omEmployee.setEmpRealname(omEmployeeUpdateRequest.getEmpRealname());
        omEmployee.setGender(omEmployeeUpdateRequest.getGender());
        omEmployee.setEmpstatus(omEmployeeUpdateRequest.getEmpstatus());
        omEmployee.setGuidOrg(omEmployeeUpdateRequest.getGuidOrg());
        omEmployee.setGuidOperator(omEmployeeUpdateRequest.getGuidOperator());
        omEmployee.setGuidPosition(omEmployeeUpdateRequest.getGuidPosition());
        omEmployee.setGuidEmpMajor(omEmployeeUpdateRequest.getGuidEmpMajor());
        omEmployee.setIndate(omEmployeeUpdateRequest.getIndate());
        omEmployee.setOutdate(omEmployeeUpdateRequest.getOutdate());
        omEmployee.setMobileno(omEmployeeUpdateRequest.getMobileno());
        omEmployee.setPaperType(omEmployeeUpdateRequest.getPaperType());
        omEmployee.setPaperNo(omEmployeeUpdateRequest.getPaperNo());
        omEmployee.setBirthdate(omEmployeeUpdateRequest.getBirthdate());
        omEmployee.setHtel(omEmployeeUpdateRequest.getHtel());
        omEmployee.setHaddress(omEmployeeUpdateRequest.getHaddress());
        omEmployee.setHzipcode(omEmployeeUpdateRequest.getHzipcode());
        omEmployee.setUserId(omEmployeeUpdateRequest.getUserId());
        omEmployee.setRemark(omEmployeeUpdateRequest.getRemark());

        updateById(omEmployee);
        return isexist;
    }

    /**
     * 删除员工
     */
    @Override
    public void moveEmp(String id) throws OrgManagementException {
        boolean isDelete = deleteById(id);

        if (isDelete){
            //删除员工和机构之间的关系
            Wrapper<OmEmpOrg> wrapperOrg = new EntityWrapper<OmEmpOrg>();
            wrapperOrg.eq(OmEmpOrg.COLUMN_GUID_EMP,id);
            List<OmEmpOrg> listOrg = omEmpOrgService.selectList(wrapperOrg);
            if (null != listOrg){
                //进行删除
                boolean deleteEmpOrg = omEmpOrgService.delete(wrapperOrg);
                if (!deleteEmpOrg){
                    throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_OM_EMP_ORG,wrap
                            ("删除员工和机构之间的关系失败"));
                }
            }

            //删除员工和岗位之间的关系
            Wrapper<OmEmpPosition> wrapperPosition = new EntityWrapper<OmEmpPosition>();
            wrapperPosition.eq(OmEmpPosition.COLUMN_GUID_EMP,id);
            List<OmEmpPosition> listPosition = omEmpPositionService.selectList(wrapperPosition);
            if (null != listPosition){
                //进行删除
                boolean deleteEmpPosition = omEmpPositionService.delete(wrapperPosition);
                if (!deleteEmpPosition){
                    throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_OM_EMP_POSITION,wrap
                            ("删除员工和岗位之间的关系失败"));
                }
            }

            //删除员工和工作组之间的关系
            Wrapper<OmEmpGroup> wrapperGroup = new EntityWrapper<OmEmpGroup>();
            wrapperGroup.eq(OmEmpPosition.COLUMN_GUID_EMP,id);
            List<OmEmpGroup> listGroup = omEmpGroupService.selectList(wrapperGroup);
            if (null != listGroup){
                //进行删除
                boolean deleteEmpGroup = omEmpGroupService.delete(wrapperGroup);
                if (!deleteEmpGroup){
                    throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_OM_EMP_GROUP,wrap
                            ("删除员工和工作组之间的关系失败"));
                }
            }
        }
    }

/*    @Override
    public Page<OmEmployeeForPositionDetail> queryEmpByOrg(Page<OmEmployee> page, Wrapper<OmEmployee> wrapper, String id) throws
            OrgManagementException {

        if (wrapper == null){
            wrapper = new EntityWrapper<OmEmployee>();
        }

        //分页查询机构ID为"id"的员工
        wrapper.eq(OmEmployee.COLUMN_GUID_ORG,id);
        Page<OmEmployee>  pageQuery = selectPage(page,wrapper);
        List<OmEmployee> list = pageQuery.getRecords();

        Page<OmEmployeeForPositionDetail> pageForPosition = new Page<OmEmployeeForPositionDetail>();
        List<OmEmployeeForPositionDetail> listPosition = new ArrayList<OmEmployeeForPositionDetail>();

        for (OmEmployee omEmployee : list){
            OmPosition omPosition = omPositionService.selectById(omEmployee.getGuidPosition());
            String omPositionName = "";
            if (null != omPosition){
                omPositionName = omPosition.getPositionName();
            }

            OmEmployeeForPositionDetail omEmployeeForPositionDetail = new OmEmployeeForPositionDetail(omEmployee,omPositionName);

            //将查询回来的信息放回到list中
            listPosition.add(omEmployeeForPositionDetail);
        }

        pageForPosition.setCondition(pageQuery.getCondition());
        pageForPosition.setRecords(listPosition);
        pageForPosition.setTotal(pageQuery.getTotal());
        pageForPosition.setSize(pageQuery.getSize());
        pageForPosition.setCurrent(pageQuery.getCurrent());

        return pageForPosition;
    }*/

    /**
     *根据机构查询员工
     */
    @Override
    public Page<OmEmployeeForPositionDetail> queryEmpByOrg(Page<OmEmployee> page, Wrapper<OmEmployee> wrapper, String id) throws OrgManagementException {

        if (wrapper == null){
            wrapper = new EntityWrapper<OmEmployee>();
        }

        //查询员工和机构
        Page<OmEmpOrg> pageOrg = new Page<OmEmpOrg>();
        pageOrg.setCurrent(page.getCurrent());
        pageOrg.setSize(page.getSize());
        Wrapper<OmEmpOrg> wrapperOrg = new EntityWrapper<OmEmpOrg>();
        wrapperOrg.eq(OmEmpOrg.COLUMN_GUID_ORG,id);

        Page<OmEmpOrg> orgPageQue = omEmpOrgService.selectPage(pageOrg,wrapperOrg);
        List<OmEmpOrg> orgListQue = orgPageQue.getRecords();


        List<OmEmployee> listEmp = new ArrayList<OmEmployee>();
        //查询机构和员工关系下的员工
        for (OmEmpOrg omEmpOrg : orgListQue){
            OmEmployee omEmployee = selectById(omEmpOrg.getGuidEmp());
            if (null != omEmployee){
                listEmp.add(omEmployee);
            }
        }

        Page<OmEmployeeForPositionDetail> pageForPosition = new Page<OmEmployeeForPositionDetail>();
        List<OmEmployeeForPositionDetail> listPosition = new ArrayList<OmEmployeeForPositionDetail>();

        for (OmEmployee omEmployee : listEmp){
            OmPosition omPosition = omPositionService.selectById(omEmployee.getGuidPosition());
            String omPositionName = "";
            if (null != omPosition){
                omPositionName = omPosition.getPositionName();
            }
            OmEmployeeForPositionDetail omEmployeeForPositionDetail = new OmEmployeeForPositionDetail(omEmployee,omPositionName);

            //将查询回来的信息放回到list中
            listPosition.add(omEmployeeForPositionDetail);
        }

        pageForPosition.setCondition(pageOrg.getCondition());
        pageForPosition.setRecords(listPosition);
        pageForPosition.setTotal(pageOrg.getTotal());
        pageForPosition.setSize(pageOrg.getSize());
        pageForPosition.setCurrent(pageOrg.getCurrent());

        return pageForPosition;
    }


    @Override
    public List<OmEmployee> queryEmpListByOrg(String id) throws OrgManagementException {

        Wrapper<OmEmpOrg> wrapper = new EntityWrapper<OmEmpOrg>();
        wrapper.eq(OmEmpOrg.COLUMN_GUID_ORG,id);
        //查询出机构和员工关系表中的所有信息
        List<OmEmpOrg> listorg = omEmpOrgService.selectList(wrapper);

        //查询出所有相关的员工
        List<OmEmployee> listEmp = new ArrayList<OmEmployee>();
        for (OmEmpOrg omEmpOrg:listorg){
            if (null != omEmpOrg){
                OmEmployee omEmployee = selectById(omEmpOrg.getGuidEmp());
                if (null != omEmployee){
                    listEmp.add(omEmployee);
                }
            }
        }

        return listEmp;
    }


    @Override
    public OmEmployee onJob(OmEmployee omEmployee) throws OrgManagementException {

        OmEmployee omEmployeeQue = selectById(omEmployee.getGuid());

            String guidOperator = omEmployee.getGuidOperator();
            String userId = omEmployee.getUserId();

            if (!(null == guidOperator || "".equals(guidOperator))){
                userId = queryUserIdByOpertary(guidOperator);
            }
            if (!(null == userId || "".equals(userId))){
                guidOperator = queryGuidByUserId(userId);
            }

        if (null == omEmployee.getIndate()){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_OM_EMPLOYEE_ONJOB,wrap
                    ("员工入职日期不能为空"));
        }

            omEmployeeQue.setGuidOperator(guidOperator);
            omEmployeeQue.setUserId(userId);
            omEmployeeQue.setIndate(omEmployee.getIndate());
            omEmployeeQue.setEmpstatus(OmEmployeeStatus.ONJOB);

            updateById(omEmployeeQue);
        return omEmployeeQue;
    }


    @Override
    public OmEmployee changeOnJob(OmEmployee omEmployee) throws OrgManagementException {

        OmEmployee omEmployeeQue = selectById(omEmployee.getGuid());

        String guidOperator = omEmployee.getGuidOperator();
        String userId = omEmployee.getUserId();

        if (!(null == guidOperator || "".equals(guidOperator))){
            userId = queryUserIdByOpertary(guidOperator);
        }
        if (!(null == userId || "".equals(userId))){
            guidOperator = queryGuidByUserId(userId);
        }

        if (null == omEmployee.getIndate()){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_UPDATE_OM_EMPLOYEE_ONJOB,wrap
                    ("修改员工入职信息时入职时间不能为空"));
        }

        omEmployeeQue.setGuidOperator(guidOperator);
        omEmployeeQue.setUserId(userId);
        omEmployeeQue.setIndate(omEmployee.getIndate());

        updateById(omEmployeeQue);
        return omEmployeeQue;
    }

    @Override
    public OmEmployee outJob(OmEmployee omEmployee) throws OrgManagementException {
        OmEmployee omEmployeeQue = selectById(omEmployee.getGuid());

        if (null == omEmployee.getOutdate()){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_OM_EMPLOYEE_OUTJOB,wrap
                    ("员工离职日期不能为空"));
        }

        omEmployeeQue.setGuidOperator(omEmployee.getGuidOperator());
        omEmployeeQue.setUserId(omEmployee.getUserId());
        omEmployeeQue.setOutdate(omEmployee.getOutdate());
        omEmployeeQue.setEmpstatus(OmEmployeeStatus.OFFJOB);

        updateById(omEmployeeQue);
        return omEmployeeQue;
    }

    public String queryUserIdByOpertary(String guidOperator){

        String userId = null;

        AcOperator acOperator = operatorService.selectById(guidOperator);
        if (null != acOperator){
            userId = acOperator.getUserId();
        }else {
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("查询操作员失败",guidOperator));
        }

        return userId;
    }

    public String queryGuidByUserId(String userId){

        String guidOperator = null;

        Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();
        wrapper.eq(AcOperator.COLUMN_USER_ID,userId);
        AcOperator acOperator = operatorService.selectOne(wrapper);
        if (null != acOperator){
            guidOperator = acOperator.getGuid();
        }else {
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("查询操作员失败",userId));
        }

        return guidOperator;
    }

    /**  该方法需要写sql **/
    @Override
    public Page<OmEmployee> queryByOrgPosition(Page<OmEmployee> page, String orgId, String positionId) throws OrgManagementException {

        return page.setRecords(this.baseMapper.queryByOrgPosition(page,orgId,positionId));
    }


    @Override
    public List<OmEmployee> getOtherEmp(OmEmployeeByOrgAndPositionRequest om) throws OrgManagementException {

        //查询机构下的所有员工
        Wrapper<OmEmpOrg> wrapperOrg = new EntityWrapper<OmEmpOrg>();
        wrapperOrg.eq(OmEmpOrg.COLUMN_GUID_ORG,om.getGuidOrg());
        List<OmEmpOrg> listOrg = omEmpOrgService.selectList(wrapperOrg);

        //查询机构下的所有员工
        List<OmEmployee> listByOrg = new ArrayList<OmEmployee>();
        for (OmEmpOrg omEmpOrg:listOrg){
            if (null != omEmpOrg){
                OmEmployee omEmployee = selectById(omEmpOrg.getGuidEmp());
                if (null != omEmployee){
                    listByOrg.add(omEmployee);
                }
            }
        }

        //查询岗位下的所有员工
        Wrapper<OmEmpPosition> wrapperPosition = new EntityWrapper<OmEmpPosition>();
        wrapperPosition.eq(OmEmpPosition.COLUMN_GUID_POSITION,om.getGuidPosition());
        List<OmEmpPosition> listPosition = omEmpPositionService.selectList(wrapperPosition);

        //查询岗位下的所有员工
        List<OmEmployee> listByPosition = new ArrayList<OmEmployee>();
        for (OmEmpPosition omEmpPosition:listPosition){
            if (null != omEmpPosition){
                OmEmployee omEmployee = selectById(omEmpPosition.getGuidEmp());
                if (null != omEmployee){
                    listByPosition.add(omEmployee);
                }
            }
        }

        listByOrg.removeAll(listByPosition);

        return listByOrg;
    }


    @Override
    public void addInOrgAndPosition(OmEmployee omEmployee) throws OrgManagementException {

        boolean empOrgExist = false;
        boolean empPositionExist = false;

        if (!"".equals(omEmployee.getGuidOrg())){
            Wrapper<OmEmpOrg> wrapper = new EntityWrapper<OmEmpOrg>();
            wrapper.eq(OmEmpOrg.COLUMN_GUID_EMP,omEmployee.getGuid());
            wrapper.eq(OmEmpOrg.COLUMN_GUID_ORG,omEmployee.getGuidOrg());
            OmEmpOrg omEmpOrg = omEmpOrgService.selectOne(wrapper);
            if (null == omEmpOrg){
                OmEmpOrg omEmpOrgAdd = new OmEmpOrg();
                omEmpOrgAdd.setGuidEmp(omEmployee.getGuid());
                omEmpOrgAdd.setGuidOrg(omEmployee.getGuidOrg());
                omEmpOrgAdd.setIsmain(YON.NO);
                omEmpOrgService.insert(omEmpOrgAdd);
            }else {
               empOrgExist = true;
            }
        }

        if (!"".equals(omEmployee.getGuidPosition())){
            Wrapper<OmEmpPosition> wrapper = new EntityWrapper<OmEmpPosition>();
            wrapper.eq(OmEmpPosition.COLUMN_GUID_EMP,omEmployee.getGuid());
            wrapper.eq(OmEmpPosition.COLUMN_GUID_POSITION,omEmployee.getGuidPosition());
            OmEmpPosition omEmpPosition = omEmpPositionService.selectOne(wrapper);
            if (null == omEmpPosition){
                OmEmpPosition omEmpPositionAdd = new OmEmpPosition();
                omEmpPositionAdd.setGuidEmp(omEmployee.getGuid());
                omEmpPositionAdd.setGuidPosition(omEmployee.getGuidPosition());
                omEmpPositionAdd.setIsmain(YON.NO);
                omEmpPositionService.insert(omEmpPositionAdd);
            }else {
                empPositionExist = true;
            }
        }

        if (empOrgExist && empPositionExist){
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_CREATE_OM_EMP_ORG_AND_POSITION,wrap("该员工已存在于该机构的岗位下"));
        }
    }
}

