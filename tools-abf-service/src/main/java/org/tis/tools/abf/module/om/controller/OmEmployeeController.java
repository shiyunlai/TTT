package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

import java.util.Date;
import java.util.List;

/**
 * omEmployee的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmployee")
public class OmEmployeeController extends BaseController<OmEmployee>  {

    @Autowired
    private IOmEmployeeService omEmployeeService;

    @OperateLog(type = OperateType.ADD,desc = "新增员工")
    @PostMapping
    public ResultVO add(@RequestBody @Validated OmEmployeeAddRequest omEmployeeAddRequest) {
        boolean isexist = omEmployeeService.add(omEmployeeAddRequest);
        if (!isexist){
            return ResultVO.error("404","员工代码已存在,请重新输入!");
        }
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改员工")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmployeeUpdateRequest omEmployeeUpdateRequest) {
        OmEmployee omEmployee = omEmployeeService.selectById(omEmployeeUpdateRequest.getGuid());
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        boolean isexist = omEmployeeService.change(omEmployeeUpdateRequest);
        if (!isexist){
            return ResultVO.error("404","员工代码已存在,请重新输入!");
        }

        OmEmployee omEmployeeQuery = omEmployeeService.selectById(omEmployeeUpdateRequest.getGuid());
        return ResultVO.success("修改成功！",omEmployeeQuery);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除员工")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omEmployeeService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmployee);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmployee> page) {
        return  ResultVO.success("查询成功", omEmployeeService.selectPage(getPage(page), getCondition(page)));
    }


    @PostMapping("/queryEmpByOrg/{id}")
    public ResultVO queryEmpByOrg(@RequestBody @Validated SmartPage<OmEmployee> page,@PathVariable @NotBlank(message
            = "id不能为空") String id){
        return ResultVO.success("查询成功",omEmployeeService.queryEmpByOrg(getPage(page),getCondition(page),id));
    }

    @GetMapping("/listsByOrg/{id}")
    public ResultVO queryEmpByOrg(@PathVariable @NotBlank(message = "id不能为空") String id){

        List<OmEmployee> list = omEmployeeService.queryEmpListByOrg(id);

        return ResultVO.success("查询成功",list);
    }


    @OperateLog(type = OperateType.UPDATE,desc = "员工入职")
    @PutMapping("/onJob/{id}")
    public ResultVO onJob(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmEmployeeStatus.OFFER != omEmployee.getEmpstatus()){
            return ResultVO.error("404", "员工不是在招状态不能办理入职");
        }

        omEmployee.setEmpstatus(OmEmployeeStatus.ONJOB);
        omEmployeeService.updateById(omEmployee);
        return ResultVO.success("入职成功！",omEmployee);
    }

    @OperateLog(type = OperateType.UPDATE,desc = "员工离职")
    @PutMapping("/outJob/{id}")
    public ResultVO outJob(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmEmployeeStatus.ONJOB != omEmployee.getEmpstatus()){
            return ResultVO.error("404", "员工不是在职状态不能办理离职");
        }

        omEmployee.setEmpstatus(OmEmployeeStatus.OFFJOB);
        omEmployee.setOutdate(new Date());
        omEmployeeService.updateById(omEmployee);
        return ResultVO.success("离职成功！",omEmployee);
    }
    
}

