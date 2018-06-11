package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncDateRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * acOperatorFunc的Controller类
 * 操作员特殊权限配置
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorFunc")
public class AcOperatorFuncController extends BaseController<AcOperatorFunc>  {

    @Autowired
    private IAcOperatorFuncService acOperatorFuncService;

    @OperateLog(type = OperateType.ADD,desc = "新增操作员特殊权限配置")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorFuncRequest acOperatorFuncRequestc) {
        acOperatorFuncService.add(acOperatorFuncRequestc);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员特殊权限配置")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorFuncRequest acOperatorFuncRequestc) {
        AcOperatorFunc acOperatorFunc = acOperatorFuncService.selectById(acOperatorFuncRequestc.getGuid());
        if (acOperatorFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorFunc = acOperatorFuncService.change(acOperatorFuncRequestc);
        return ResultVO.success("修改成功！",acOperatorFunc);
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorFunc acOperatorFunc = acOperatorFuncService.selectById(id);
        if (acOperatorFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorFuncService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorFunc acOperatorFunc = acOperatorFuncService.selectById(id);
        if (acOperatorFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorFunc);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorFunc> page) {
        return  ResultVO.success("查询成功", acOperatorFuncService.selectPage(getPage(page), getCondition(page)));
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改有效时间")
    @PutMapping("/setDate")
    public ResultVO setDate(@RequestBody @Validated AcOperatorFuncDateRequest acOperatorFuncDateRequest){
        AcOperatorFunc acOperatorFunc = acOperatorFuncService.selectById(acOperatorFuncDateRequest.getGuid());
        if (acOperatorFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        //收集参数
        acOperatorFunc.setStartDate(acOperatorFuncDateRequest.getStartDate());
        acOperatorFunc.setEndDate(acOperatorFuncDateRequest.getEndDate());

        acOperatorFuncService.updateById(acOperatorFunc);

        return ResultVO.success("时间设置成功",acOperatorFunc);
    }
    
}

