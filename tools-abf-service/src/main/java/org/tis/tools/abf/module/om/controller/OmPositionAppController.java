package org.tis.tools.abf.module.om.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppListRequest;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppRequest;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.service.IOmPositionAppService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * omPositionApp的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omPositionApp")
@Validated
public class OmPositionAppController extends BaseController<OmPositionApp>  {

    @Autowired
    private IOmPositionAppService omPositionAppService;

    @OperateLog(type = OperateType.ADD,desc = "新增岗位应用列表")
    @PostMapping
    public ResultVO add(@RequestBody @Validated OmPositionAppRequest omPositionAppRequest) {
        omPositionAppService.add(omPositionAppRequest);
        return ResultVO.success("新增成功!");
    }

    @OperateLog(type = OperateType.ADD,desc = "批量新增岗位应用列表")
    @PostMapping("addByList")
    public ResultVO add(@RequestBody @Validated OmPositionAppListRequest om ){
        omPositionAppService.addList(om);
        return ResultVO.success("新增成功!");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改岗位应用列表")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmPositionAppRequest omPositionAppRequest) {
        OmPositionApp omPositionAppQuery = omPositionAppService.selectById(omPositionAppRequest.getGuid());
        if (omPositionAppQuery == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omPositionAppQuery = omPositionAppService.change(omPositionAppRequest);
        return ResultVO.success("修改成功！",omPositionAppQuery);
    }

    @OperateLog(type = OperateType.DELETE,desc ="删除岗位应用列表")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPositionApp omPositionApp = omPositionAppService.selectById(id);
        if (omPositionApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omPositionAppService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @OperateLog(type = OperateType.DELETE,desc ="根据岗位id和应用id删除岗位应用列表")
    @DeleteMapping("/delete")
    public ResultVO deleteByPositionAndApp(@RequestBody @Validated OmPositionAppRequest omPositionAppRequest) {
        omPositionAppService.deleteByPositionAndApp(omPositionAppRequest);
        return ResultVO.success("删除成功！");
    }

    /**
     * 根据ID查询岗位应用
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPositionApp omPositionApp = omPositionAppService.selectById(id);
        if (omPositionApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omPositionApp);
    }

    /**
     * 查询所有岗位应用,分页
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmPositionApp> page) {
        return  ResultVO.success("查询成功", omPositionAppService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 查询岗位已经有的应用权限
     */
    @PostMapping("/listByPosition/{guidPosition}")
    public ResultVO listByPosition(@RequestBody @Validated SmartPage<OmPositionApp> page,@PathVariable @NotBlank
            (message = "岗位guidPosition不能为空") String guidPosition){
        return ResultVO.success("查询成功",omPositionAppService.queryAppByPosition(getPage(page),getCondition(page),
                guidPosition));
    }

    /**
     * 根据岗位ID,查询不在该岗位下的应用
     * @param id
     * @return
     */
    @GetMapping("/appNotInPosition/{id}")
    public ResultVO listNotInPosition(@PathVariable @NotBlank(message = "岗位id不能为空") String id){
        return ResultVO.success("查询成功",omPositionAppService.queryNotInPosition(id));
    }

    
}

