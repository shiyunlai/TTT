package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcAppConfigAddRequest;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

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

    @OperateLog(type = OperateType.ADD, desc = "新增个性化配置")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcAppConfigAddRequest acAppConfigAddRequest) {

        acAppConfigService.createRootAppConfig(acAppConfigAddRequest.getGuidApp(), acAppConfigAddRequest.getConfigType(),acAppConfigAddRequest.getConfigName(), acAppConfigAddRequest.getConfigDict(), acAppConfigAddRequest.getConfigStyle(), acAppConfigAddRequest.getConfigValue(),acAppConfigAddRequest.getEnabled(), acAppConfigAddRequest.getDisplayOrder(),acAppConfigAddRequest.getConfigDesc());
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE, desc = "修改个性化配置")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppConfigAddRequest acAppConfigAddRequest) {

       AcAppConfig acAppConfig = acAppConfigService.selectById(acAppConfigAddRequest.getGuid());
        if (acAppConfig == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        AcAppConfig acAppConfig1 = acAppConfigService.changeById(acAppConfigAddRequest.getGuid(),acAppConfigAddRequest.getGuidApp(),
                acAppConfigAddRequest.getConfigType(), acAppConfigAddRequest.getConfigName(), acAppConfigAddRequest.getConfigDict(),
                acAppConfigAddRequest.getConfigStyle(), acAppConfigAddRequest.getConfigValue(), acAppConfigAddRequest.getEnabled(),
                acAppConfigAddRequest.getDisplayOrder(),acAppConfigAddRequest.getConfigDesc());
        return ResultVO.success("修改成功！",acAppConfig1);
    }

    @OperateLog(type = OperateType.DELETE, desc = "删除个性化配置")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcAppConfig acAppConfig = acAppConfigService.selectById(id);
        if (acAppConfig == null ) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        acAppConfigService.moveAppConfig(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcAppConfig acAppConfig = acAppConfigService.selectById(id);
        if (acAppConfig == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acAppConfig);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcAppConfig> page) {
        return ResultVO.success("查询成功", acAppConfigService.selectPage(getPage(page), getCondition(page)));
    }

}

