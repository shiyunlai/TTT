package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcFuncSonRequest;
import org.tis.tools.abf.module.ac.controller.request.AcFuncUpdateRequest;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * acFunc的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acFunc")
@Validated
public class AcFuncController extends BaseController<AcFunc>  {

    @Autowired
    private IAcFuncService acFuncService;


    /**
     * 新增功能行为
     * @param acFuncSonRequest
     * @return ResultVO
     */
    @OperateLog(type = OperateType.ADD, desc = "新增功能行为")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcFuncSonRequest acFuncSonRequest){

         acFuncService.creatFunc(acFuncSonRequest.getGuidApp(),acFuncSonRequest.getFuncType(),acFuncSonRequest.getFuncCode(),
                acFuncSonRequest.getFuncName(), acFuncSonRequest.getDisplayOrder(),acFuncSonRequest.getFuncDesc(),
                acFuncSonRequest.getGuidFunc(),acFuncSonRequest.getIsopen(),acFuncSonRequest.getIscheck());

        return ResultVO.success("新增成功！");
    }

    /**
     * 修改功能行为
     * @param acFuncUpdateRequest
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE, desc = "修改功能行为")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcFuncUpdateRequest acFuncUpdateRequest) {

        AcFunc acFunc = acFuncService.selectById(acFuncUpdateRequest.getGuid());
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        AcFunc acFunc1 = acFuncService.changeFunc(acFuncUpdateRequest.getGuid(),acFuncUpdateRequest.getGuidApp(),acFuncUpdateRequest.getFuncType(), acFuncUpdateRequest.getFuncCode(),acFuncUpdateRequest.getFuncName(),acFuncUpdateRequest.getFuncDesc(),acFuncUpdateRequest.getIsopen(),acFuncUpdateRequest.getIscheck(),acFuncUpdateRequest.getDisplayOrder(),acFuncUpdateRequest.getGuidFunc());
        return ResultVO.success("修改成功！",acFunc1);
    }

    /**
     * 删除功能行为
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除功能行为")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        Boolean isDel = acFuncService.deleteById(id);
        return ResultVO.success("删除成功!");
    }


    /**
     * 查询功能行为
     * @param id
     * @return ResultVO
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acFunc);
    }


    /**
     * 查询功能列表
     * @param page
     * @return ResultVO
     */
    @PostMapping("/list/{id}")
    public ResultVO list(@RequestBody @Validated SmartPage<AcFunc> page,@PathVariable @NotBlank(message = "id不能为空") String id ) {
        return  ResultVO.success("查询成功", acFuncService.queryPageById(getPage(page), getCondition(page),id));
    }

    /**
     * 开通功能
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE,desc = "开通功能")
    @PutMapping("/openFunc/{id}")
    public ResultVO openFunc(@PathVariable @NotBlank(message = "id不能为空") String id) {

        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除!");
        }
        acFunc.setIsopen(YON.YES);
        acFuncService.updateById(acFunc);

        return ResultVO.success("开通成功！",acFunc);
    }

    /**
     * 停用功能
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE,desc = "停用功能")
    @PutMapping("/closeFunc/{id}")
    public ResultVO closeFunc(@PathVariable @NotBlank(message = "id不能为空") String id) {

        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除!");
        }
        acFunc.setIsopen(YON.NO);
        acFuncService.updateById(acFunc);
        return ResultVO.success("停用成功！",acFunc);
    }
}

