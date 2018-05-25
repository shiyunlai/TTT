package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcFuncRequest;
import org.tis.tools.abf.module.ac.controller.request.AcFuncSonRequest;
import org.tis.tools.abf.module.ac.controller.request.AcFuncUpdateRequest;
import org.tis.tools.abf.module.ac.controller.request.AcFuncUpdateRootRequest;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

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
     * 新增根功能
     * @param acFuncRequest
     * @return ResultVO
     */
    @OperateLog(type = OperateType.ADD, desc = "新增根功能")
    @PostMapping("/addRoot")
    public ResultVO addRoot(@RequestBody @Validated AcFuncRequest acFuncRequest) {

        AcFunc acFunc;

        acFunc = acFuncService.creatRootFunc(acFuncRequest.getGuidApp(),acFuncRequest.getFuncType(),acFuncRequest.getFuncCode(),
                acFuncRequest.getFuncName(),acFuncRequest.getDisplayOrder(),acFuncRequest.getFuncDesc(),acFuncRequest.getIsopen(),acFuncRequest.getIscheck());
        return ResultVO.success("新增成功！",acFunc);
    }

    /**
     * 新增子功能
     * @param acFuncSonRequest
     * @return ResultVO
     */
    @OperateLog(type = OperateType.ADD, desc = "新增子功能")
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcFuncSonRequest acFuncSonRequest){

        AcFunc acFunc;

        acFunc = acFuncService.creatFunc(acFuncSonRequest.getGuidApp(),acFuncSonRequest.getFuncType(),acFuncSonRequest.getFuncCode(),
                acFuncSonRequest.getFuncName(), acFuncSonRequest.getDisplayOrder(),acFuncSonRequest.getFuncDesc(),
                acFuncSonRequest.getGuidFunc(),acFuncSonRequest.getIsopen(),acFuncSonRequest.getIscheck());

        return ResultVO.success("新增成功！",acFunc);
    }

    /**
     * 修改子功能
     * @param acFuncUpdateRequest
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE, desc = "修改功能")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcFuncUpdateRequest acFuncUpdateRequest) {

        AcFunc acFunc = acFuncService.selectById(acFuncUpdateRequest.getGuid());
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        AcFunc acFunc1 = acFuncService.changeFunc(acFuncUpdateRequest.getGuid(),acFuncUpdateRequest.getGuidApp(),acFuncUpdateRequest.getFuncType(),
                acFuncUpdateRequest.getFuncCode(),acFuncUpdateRequest.getFuncName(),acFuncUpdateRequest.getFuncDesc(),
                acFuncUpdateRequest.getIsopen(),acFuncUpdateRequest.getIscheck(),acFuncUpdateRequest.getDisplayOrder(),acFuncUpdateRequest.getGuidFunc());
        return ResultVO.success("修改成功！",acFunc1);
    }

    /**
     * 修改根功能
     * @param updateRoot
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改根功能")
    @PutMapping("/updateRoot")
    public ResultVO updateRoot(@RequestBody @Validated AcFuncUpdateRootRequest updateRoot) {

        AcFunc acFunc = acFuncService.selectById(updateRoot.getGuid());
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        String guidFunc = "0";

        AcFunc acFunc1 = acFuncService.changeFunc(updateRoot.getGuid(),updateRoot.getGuidApp(),updateRoot.getFuncType(),
                updateRoot.getFuncCode(),updateRoot.getFuncName(),updateRoot.getFuncDesc(),
                updateRoot.getIsopen(),updateRoot.getIscheck(),updateRoot.getDisplayOrder(),guidFunc);
        return ResultVO.success("修改成功！",acFunc1);
    }

    /**
     * 删除子功能
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除子功能")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        Boolean isDel = acFuncService.deleteById(id);
        return ResultVO.success("删除成功!",isDel);
    }

    /**
     * 删除根功能
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除根功能")
    @DeleteMapping("/deleteRoot/{id}")
    public ResultVO deleteRoot(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        Boolean isDel = acFuncService.clearRootFunc(id,acFunc.getDataStatus());
        return ResultVO.success("删除成功!",isDel);
    }


    /**
     * 查询功能
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.QUERY,desc = "查询功能")
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
    @OperateLog(type = OperateType.QUERY,desc = "查询功能列表")
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcFunc> page) {
        return  ResultVO.success("查询成功", acFuncService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 查询根功能的子列表
     * @param id
     * @return ResultVO
     */
    @OperateLog(type= OperateType.QUERY,desc = "查询根功能的子列表")
    @GetMapping("/childList/{id}")
    public ResultVO childList(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcFunc acFunc = acFuncService.selectById(id);
        if (acFunc == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除!");
        }else if(!("0".equals(acFunc.getGuidFunc()))){
            return ResultVO.error("404","该功能不是根功能,没有子功能!");
        }

        List<AcFunc> acFuncList = acFuncService.queryFuncList(id);
        if (acFuncList == null || 0 == acFuncList.size()){
            return ResultVO.error("404","该功能下没有查到子功能!");
        }
        return ResultVO.success("查询成功",acFuncList);
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

