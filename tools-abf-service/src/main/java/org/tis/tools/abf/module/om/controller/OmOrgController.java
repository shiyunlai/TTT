package org.tis.tools.abf.module.om.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmOrgAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmOrgSetDateRequest;
import org.tis.tools.abf.module.om.controller.request.OmOrgUpdateRequest;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.entity.enums.OmOrgStatus;
import org.tis.tools.abf.module.om.service.IOmOrgService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * describe: 机构controller
 *
 * @author zhaoch
 * @date 2018/3/27
**/
@RestController
@RequestMapping("/omOrg")
@Validated
public class OmOrgController extends BaseController<OmOrg> {

    @Autowired
    private IOmOrgService orgService;

    /**
     * 新增机构综合
     *
     * @param request
     * @return
     */
    @OperateLog(type = OperateType.ADD, desc = "新增根机构")
    @PostMapping("/addRoot")
    public ResultVO addRoot(@RequestBody @Validated OmOrgAddRequest request) {
            orgService.createRootOrg(request.getArea(), request.getOrgDegree(),request.getOrgName(), request.getOrgType(),request.getOrgAddr(),request.getLinkMan(),request.getLinkTel(),request.getStartDate(),request.getEndDate(),request.getRemark());
        return ResultVO.success("新增成功！");
    }

    /**
     * 新增机构综合
     *
     * @param request
     * @return
     */
    @OperateLog(type = OperateType.ADD, desc = "新增子机构")
    @PostMapping("/addChild")
    public ResultVO addChild(@RequestBody @Validated OmOrgAddRequest request) {
        orgService.createChildOrg(request.getArea(), request.getOrgDegree(),request.getOrgName(), request.getOrgType(), request.getGuidParents(),request.getOrgAddr(),request.getLinkMan(),request.getLinkTel(),request.getStartDate(),request.getEndDate(),request.getRemark());
        return ResultVO.success("新增成功！");
    }


    /**
     * 修改机构
     * @param omOrgUpdateRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改机构")
    @PutMapping
    public ResultVO updateRoot(@RequestBody @Validated OmOrgUpdateRequest omOrgUpdateRequest){
        OmOrg omOrg = orgService.selectById(omOrgUpdateRequest.getGuid());
        if (null == omOrg){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        boolean isexist = orgService.changeOrg(omOrgUpdateRequest);
        if (!isexist){
            return ResultVO.error("404","机构代码已存在,请重新输入!");
        }

        OmOrg omOrgquery = orgService.selectById(omOrgUpdateRequest.getGuid());
        return ResultVO.success("修改成功！",omOrgquery);
    }

    /**
     * 删除机构
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.DELETE, desc = "删除机构")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmOrg omOrg = orgService.selectById(id);
        if (omOrg == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        orgService.delectRoot(id);
        return ResultVO.success("删除成功");
    }

    /**
     * 根据ID查询机构
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmOrg omOrg = orgService.selectById(id);
        if (omOrg == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omOrg);
    }

    /**
     * 查询机构列表
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmOrg> page) {
        return  ResultVO.success("查询成功", orgService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 查询所有机构
     * @return
     */
    @GetMapping("/queryAll")
    public ResultVO queryAllOrg(){
        return ResultVO.success("查询成功",orgService.queryAllOrg());
    }


    /**
     * 查询机构的树结构
     * @param guid
     * @return
     */
    @PostMapping("/tree/{guid}")
    public ResultVO tree(@PathVariable @NotBlank(message = "guid不能为空") String guid){

        if (!"null".equals(guid)){
            OmOrg omOrg = orgService.selectById(guid);
            if (null == omOrg){
                return ResultVO.error("404", "找不到对应记录或已经被删除！");
            }
        }

        return ResultVO.success("查询成功！",orgService.queryOrgTree(guid));
    }


    /**
     * 设置机构有效时间
     * @param omOrgSetDateRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "设置机构的有效时间")
    @PutMapping("/setDate")
    public ResultVO setDate(@RequestBody @Validated OmOrgSetDateRequest omOrgSetDateRequest){
        OmOrg omOrg = orgService.selectById(omOrgSetDateRequest.getGuid());
        if (null == omOrg){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        //收集参数
        omOrg.setStartDate(omOrgSetDateRequest.getStartDate());
        omOrg.setEndDate(omOrgSetDateRequest.getEndDate());

        orgService.updateById(omOrg);

        return ResultVO.success("设置成功！",omOrg);
    }

    /**
     * 注销机构
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "注销机构")
    @PutMapping("/cancelStatus/{id}")
    public ResultVO cancelStatus(@PathVariable @NotBlank(message = "id不能为空") String id){
        OmOrg omOrg = orgService.selectById(id);
        if (null == omOrg){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmOrgStatus.RUNNING != omOrg.getOrgStatus()){
            return ResultVO.error("404", "只有机构状态为正常时才可以注销！");
        }

        omOrg.setOrgStatus(OmOrgStatus.CANCEL);
        orgService.updateById(omOrg);

        return ResultVO.success("注销成功！",omOrg);
    }

    /**
     * 停用机构
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "停用机构")
    @PutMapping("/stopStatus/{id}")
    public ResultVO stopStatus(@PathVariable @NotBlank(message = "id不能为空") String id){
        OmOrg omOrg = orgService.selectById(id);
        if (null == omOrg){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmOrgStatus.CANCEL != omOrg.getOrgStatus()){
            return ResultVO.error("404", "只有机构状态为注销时才可以停用！");
        }

        omOrg.setOrgStatus(OmOrgStatus.STOP);
        orgService.updateById(omOrg);

        return ResultVO.success("停用成功！",omOrg);
    }

    /**
     * 启用机构
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "启用机构")
    @PutMapping("/runningStatus/{id}")
    public ResultVO runningStatus(@PathVariable @NotBlank(message = "id不能为空") String id){
        OmOrg omOrg = orgService.selectById(id);
        if (null == omOrg){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        omOrg.setOrgStatus(OmOrgStatus.RUNNING);
        orgService.updateById(omOrg);

        return ResultVO.success("启用成功！",omOrg);
    }

}
