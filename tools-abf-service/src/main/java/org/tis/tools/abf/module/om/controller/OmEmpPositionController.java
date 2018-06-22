package org.tis.tools.abf.module.om.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmEmpPositionRequest;
import org.tis.tools.abf.module.om.entity.OmEmpPosition;
import org.tis.tools.abf.module.om.service.IOmEmpPositionService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * omEmpPosition的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmpPosition")
public class OmEmpPositionController extends BaseController<OmEmpPosition>  {

    @Autowired
    private IOmEmpPositionService omEmpPositionService;

    @OperateLog(type = OperateType.ADD,desc = "新增岗位员工")
    @PostMapping
    public ResultVO add(@RequestBody @Validated OmEmpPositionRequest OmEmpPositionRequest) {
        omEmpPositionService.add(OmEmpPositionRequest);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改岗位员工")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmpPosition omEmpPosition) {
        omEmpPositionService.updateById(omEmpPosition);
        return ResultVO.success("修改成功！");
    }

    @OperateLog(type = OperateType.DELETE,desc = "根据ID删除岗位员工")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omEmpPositionService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @OperateLog(type = OperateType.DELETE,desc = "根据员工和岗位ID删除岗位员工")
    @DeleteMapping("/delete/{guidEmp}/{guidPosition}")
    public ResultVO deleteByEmpPositionId(@PathVariable @NotBlank(message = "员工GUID不能为空") String guidEmp,@PathVariable @NotBlank(message = "所在岗位GUID不能为空") String guidPosition) {
        omEmpPositionService.deleteByEmpPositionId(guidEmp,guidPosition);
        return ResultVO.success("删除成功");
    }

    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpPosition omEmpPosition = omEmpPositionService.selectById(id);
        if (omEmpPositionService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmpPosition);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmpPosition> page) {
        return  ResultVO.success("查询成功", omEmpPositionService.selectPage(getPage(page), getCondition(page)));
    }
    
}

