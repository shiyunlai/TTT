package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncDateRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncQueConditionRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.model.common.ResultVO;
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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorFunc acOperatorFunc = acOperatorFuncService.selectById(id);
        if (acOperatorFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorFunc);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorFunc> page) {
        return  ResultVO.success("查询成功", acOperatorFuncService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 修改有效时间
     * @param acOperatorFuncDateRequest
     * @return
     */
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

    /**
     * 查询所有应用和操作员下已有应用
     * @param operatorId
     * @return
     */
    @GetMapping("/queryAppByOperator/{operatorId}")
    public ResultVO queryAppByOperator (@PathVariable @NotBlank(message = "操作员id不能为空") String operatorId){
        return ResultVO.success("查询成功",acOperatorFuncService.queryAppByOperator(operatorId));
    }

    /**
     * 查询应用下所有功能和操作员下已有功能
     * @param conditionRequest
     * @return
     */
    @PostMapping("/queryFuncByOperator")
    public ResultVO queryFuncByOperator (@RequestBody @Validated AcOperatorFuncQueConditionRequest conditionRequest){
        return ResultVO.success("查询成功",acOperatorFuncService.queryFuncByOperator(conditionRequest));
    }

    /**
     * 查询功能下所有行为和操作员下已有行为
     * @param conditionRequest
     * @return
     */
    @PostMapping("/queryBehaveByOperator")
    public ResultVO queryBehaveByOperator (@RequestBody @Validated AcOperatorFuncQueConditionRequest conditionRequest){
        return ResultVO.success("查询成功",acOperatorFuncService.queryBehaveByOperator(conditionRequest));
    }

    /**
     * 批量新增和删除操作员功能
     * @param batchRequest
     * @return
     */
    @OperateLog(type = OperateType.ADD,desc = "批量新增操作员功能信息")
    @PostMapping("/batchAdd")
    public ResultVO batchAddClear(@RequestBody @Validated AcOperatorBatchAddRequest batchRequest){
        acOperatorFuncService.batchAdd(batchRequest);
        return ResultVO.success("处理成功");
    }
    
}

