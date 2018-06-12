package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmEmpOrgRequest;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmEmpOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * omEmpOrg的Controller类
 * 员工隶属机构关系表
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmpOrg")
public class OmEmpOrgController extends BaseController<OmEmpOrg>  {

    @Autowired
    private IOmEmpOrgService omEmpOrgService;

    @OperateLog(type = OperateType.ADD,desc = "新增员工隶属机构关系表")
    @PostMapping
    public ResultVO add(@RequestBody @Validated OmEmpOrgRequest omEmpOrgRequest) {
        omEmpOrgService.add(omEmpOrgRequest);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改员工隶属机构关系表")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmpOrgRequest omEmpOrgRequest) {
        OmEmpOrg omEmpOrg = omEmpOrgService.selectById(omEmpOrgRequest);
        if (omEmpOrg == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omEmpOrgService.change(omEmpOrgRequest);
        return ResultVO.success("修改成功！");
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除员工隶属机构关系表")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpOrg omEmpOrg = omEmpOrgService.selectById(id);
        if (omEmpOrg == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omEmpOrgService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpOrg omEmpOrg = omEmpOrgService.selectById(id);
        if (omEmpOrg == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmpOrg);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmpOrg> page) {
        return  ResultVO.success("查询成功", omEmpOrgService.selectPage(getPage(page), getCondition(page)));
    }
    
}

