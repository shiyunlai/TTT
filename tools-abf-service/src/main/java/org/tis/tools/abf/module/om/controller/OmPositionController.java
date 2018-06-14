package org.tis.tools.abf.module.om.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmOrgSetDateRequest;
import org.tis.tools.abf.module.om.controller.request.OmPositionRequest;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.service.IOmPositionService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

/**
 * omPosition的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omPosition")
public class OmPositionController extends BaseController<OmPosition>  {

    @Autowired
    private IOmPositionService omPositionService;

    @OperateLog(type = OperateType.ADD,desc = "新增根岗位")
    @PostMapping("/addRoot")
    public ResultVO addRoot(@RequestBody @Validated OmPositionRequest omPositionRequest) {

        boolean isexist = omPositionService.addRoot(omPositionRequest);
        if (!isexist){
            return ResultVO.error("404","岗位代码已存在,请重新输入!");
        }
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.ADD,desc = "新增子岗位")
    @PostMapping("/addChild")
    public ResultVO addChild(@RequestBody @Validated OmPositionRequest omPositionRequest) {

        boolean isexist = omPositionService.addChild(omPositionRequest);
        if (!isexist){
            return ResultVO.error("404","岗位代码已存在,请重新输入!");
        }
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改岗位")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmPositionRequest omPositionRequest) {
        OmPosition omPosition = omPositionService.selectById(omPositionRequest.getGuid());
        if (omPosition == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        boolean isexist = omPositionService.change(omPositionRequest);
        if (!isexist){
            return ResultVO.error("404","岗位代码已存在,请重新输入!");
        }

        OmPosition omPositionQuery = omPositionService.selectById(omPositionRequest.getGuid());
        return ResultVO.success("修改成功！",omPositionQuery);
    }


    @OperateLog(type = OperateType.DELETE,desc = "删除岗位")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPosition omPosition = omPositionService.selectById(id);
        if (omPosition == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omPositionService.deleteRoot(id);
        return ResultVO.success("删除成功");
    }


    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPosition omPosition = omPositionService.selectById(id);
        if (omPosition == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omPosition);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmPosition> page) {
        return  ResultVO.success("查询成功", omPositionService.selectPage(getPage(page), getCondition(page)));
    }

    @GetMapping("/allPositionList")
    public ResultVO QueryAllPosition(){

        List<OmPosition> list = omPositionService.QueryAllPosition();

        return ResultVO.success("查询成功",list);
    }

    @PostMapping("/tree/{guid}")
    public ResultVO tree(@PathVariable @NotBlank(message = "guid不能为空") String guid){

        OmPosition omPosition = omPositionService.selectById(guid);
        if (omPosition == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("修改成功！",omPositionService.queryPositionTree(guid));
    }

    @PostMapping("/treeByOrgId/{guid}")
    public ResultVO treeByOrgId(@RequestBody @Validated SmartPage<OmPosition> page, @PathVariable @NotBlank(message = "组织机构guid不能为空") String guid){
        return ResultVO.success("修改成功！",omPositionService.treeByOrgId(getPage(page),getCondition(page),guid));
    }

    @GetMapping("/listsByOrgId/{guid}")
    public ResultVO QueryPositionByOrgId(@PathVariable @NotBlank(message = "guid不能为空") String guid){
        return ResultVO.success("查询成功",omPositionService.QueryPositionByOrgId(guid));
    }

    /**
     * 设置岗位有效时间
     * @param omOrgSetDateRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "设置岗位的有效时间")
    @PutMapping("/setDate")
    public ResultVO setDate(@RequestBody @Validated OmOrgSetDateRequest omOrgSetDateRequest){
        OmPosition omPosition = omPositionService.selectById(omOrgSetDateRequest.getGuid());
        if (null == omPosition){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        //收集参数
        omPosition.setStartDate(omOrgSetDateRequest.getStartDate());
        omPosition.setEndDate(omOrgSetDateRequest.getEndDate());

        omPositionService.updateById(omPosition);

        return ResultVO.success("设置成功！",omPosition);
    }


    /**
     * 注销机构
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "注销岗位")
    @PutMapping("/cancelStatus/{id}")
    public ResultVO cancelStatus(@PathVariable @NotBlank(message = "id不能为空") String id){
        OmPosition omPosition = omPositionService.selectById(id);
        if (null == omPosition){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmPositionStatus.RUNNING != omPosition.getPositionStatus()){
            return ResultVO.error("404", "只有机构状态为正常时才可以注销！");
        }

        omPosition.setPositionStatus(OmPositionStatus.CANCEL);
        omPositionService.updateById(omPosition);

        return ResultVO.success("注销成功！",omPosition);
    }

    @OperateLog(type = OperateType.UPDATE,desc = "启用岗位")
    @PutMapping("/runningStatus/{id}")
    public ResultVO runningStatus(@PathVariable @NotBlank(message = "id不能为空") String id){
        OmPosition omPosition = omPositionService.selectById(id);
        if (null == omPosition){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmPositionStatus.CANCEL != omPosition.getPositionStatus()){
            return ResultVO.error("404", "只有机构状态为注销时才可以启用！");
        }

        omPosition.setPositionStatus(OmPositionStatus.RUNNING);
        omPositionService.updateById(omPosition);

        return ResultVO.success("启用成功！",omPosition);
    }


    
}

