package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.controller.request.AcAppConfigAddRequest;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;

import java.math.BigDecimal;

/**
 * acAppConfig的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acAppConfig")
@Validated
public class AcAppConfigController extends BaseController<AcAppConfig> {

    @Autowired
    private IAcAppConfigService acAppConfigService;

    @OperateLog(
            operateType = OperateType.ADD, // 操作类型
            operateDesc = "新增个性化配置", // 操作描述
            retType = ReturnType.Object, // 返回类型,对象或数组
            id = "configType", // 操作对象标识
            name = "configName", //操作对象名
            keys = {"configType","configName"}) // 操作对象的关键值的键值名
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcAppConfigAddRequest acAppConfigAddRequest) {

        AcAppConfig acAppConfig;

        BigDecimal displayOrder;
        if ("".equals(acAppConfigAddRequest.getDisplayOrder()) || null == acAppConfigAddRequest.getDisplayOrder()){
            displayOrder = null;
        }else {
            displayOrder = BigDecimal.valueOf(Double.valueOf(acAppConfigAddRequest.getDisplayOrder()));
        }

        acAppConfig = acAppConfigService.createRootAppConfig(acAppConfigAddRequest.getGuidApp(), acAppConfigAddRequest.getConfigType(),
                acAppConfigAddRequest.getConfigName(), acAppConfigAddRequest.getConfigDict(), acAppConfigAddRequest.getConfigStyle(),
                acAppConfigAddRequest.getConfigValue(),acAppConfigAddRequest.getEnabled(), displayOrder,acAppConfigAddRequest.getConfigDesc());
        return ResultVO.success("新增成功！",acAppConfig);
    }

    @OperateLog(
            operateType = OperateType.UPDATE, // 操作类型
            operateDesc = "修改个性化配置", // 操作描述
            retType = ReturnType.Object, // 返回类型,对象或数组
            id = "configType", // 操作对象标识
            name = "configName", //操作对象名
            keys = {"configType","configName"}) // 操作对象的关键值的键值名
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppConfigAddRequest acAppConfigAddRequest) {

        AcAppConfig acAppConfig;

        BigDecimal displayOrder;
        if ("".equals(acAppConfigAddRequest.getDisplayOrder()) || null == acAppConfigAddRequest.getDisplayOrder()){
            displayOrder = null;
        }else {
            displayOrder = BigDecimal.valueOf(Double.valueOf(acAppConfigAddRequest.getDisplayOrder()));
        }

        acAppConfig = acAppConfigService.changeById(acAppConfigAddRequest.getGuid(),acAppConfigAddRequest.getGuidApp(), acAppConfigAddRequest.getConfigType(), acAppConfigAddRequest.getConfigName(), acAppConfigAddRequest.getConfigDict(), acAppConfigAddRequest.getConfigStyle(), acAppConfigAddRequest.getConfigValue(), acAppConfigAddRequest.getEnabled(), displayOrder,acAppConfigAddRequest.getConfigDesc());
        return ResultVO.success("修改成功！",acAppConfig);
    }

    @OperateLog(
            operateType = OperateType.DELETE, // 操作类型
            operateDesc = "删除个性化配置", // 操作描述
            retType = ReturnType.Object, // 返回类型,对象或数组
            id = "configType", // 操作对象标识
            name = "configName", //操作对象名
            keys = {"configType","configName"}) // 操作对象的关键值的键值名
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        Boolean isDel = acAppConfigService.deleteById(id);
        return ResultVO.success("删除成功",isDel);
    }

    @OperateLog(
            operateType = OperateType.QUERY, // 操作类型
            operateDesc = "根据ID查询个性化配置", // 操作描述
            retType = ReturnType.Object, // 返回类型,对象或数组
            id = "configType", // 操作对象标识
            name = "configName", //操作对象名
            keys = {"configType","configName"}) // 操作对象的关键值的键值名
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcAppConfig acAppConfig = acAppConfigService.selectById(id);
        if (acAppConfigService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acAppConfig);
    }

    @OperateLog(
            operateType = OperateType.QUERY, // 操作类型
            operateDesc = "分页查询个性化配置", // 操作描述
            retType = ReturnType.List, // 返回类型,对象或数组
            id = "configType", // 操作对象标识
            name = "configName", //操作对象名
            keys = {"configType","configName"}) // 操作对象的关键值的键值名
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcAppConfig> page) {
        return ResultVO.success("查询成功", acAppConfigService.queryByPage(getPage(page), getCondition(page)));
    }

}

