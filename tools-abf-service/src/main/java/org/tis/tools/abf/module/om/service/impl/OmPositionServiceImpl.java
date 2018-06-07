package org.tis.tools.abf.module.om.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.controller.request.OmPositionRequest;
import org.tis.tools.abf.module.om.dao.OmPositionMapper;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.vo.OmPositionDetail;
import org.tis.tools.abf.module.om.exception.OMExceptionCodes;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
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
            //查询所有的子岗位
            Wrapper<OmPosition> wrapper = new EntityWrapper<OmPosition>();
            wrapper.eq(OmPosition.COLUMN_GUID_PARENTS,id);
            //子岗位列表
            List<OmPosition> lists = selectList(wrapper);
            //删除所有的子岗位
            for (OmPosition omPosition:lists) {
                deleteById(omPosition.getGuid());
            }

            //删除根岗位
            deleteById(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new OrgManagementException(OMExceptionCodes.FAILURE_WHEN_DELETE_ROOT_POSITION,wrap(e.getMessage()));
        }
    }

}

