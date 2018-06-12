package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.controller.request.AcOperatorConfigRequest;
import org.tis.tools.abf.module.ac.service.IAcOperatorConfigService;
import org.tis.tools.abf.module.ac.entity.AcOperatorConfig;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acOperatorConfig的Controller类
 * 操作员个性配置
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorConfig")
public class AcOperatorConfigController extends BaseController<AcOperatorConfig>  {

    @Autowired
    private IAcOperatorConfigService acOperatorConfigService;

    @OperateLog(type = OperateType.ADD,desc = "新增操作员个性配置")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorConfigRequest acOperatorConfig) {
        acOperatorConfigService.add(acOperatorConfig);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员个性配置")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorConfigRequest acOperatorConfigRequest) {
        AcOperatorConfig acOperatorConfig = acOperatorConfigService.selectById(acOperatorConfigRequest.getGuid());
        if (acOperatorConfig == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorConfig = acOperatorConfigService.change(acOperatorConfigRequest);
        return ResultVO.success("修改成功！",acOperatorConfig);
    }

    @OperateLog(type =OperateType.DELETE,desc = "删除操作员个性配置")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorConfig acOperatorConfig = acOperatorConfigService.selectById(id);
        if (acOperatorConfig == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorConfigService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorConfig acOperatorConfig = acOperatorConfigService.selectById(id);
        if (acOperatorConfig == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorConfig);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorConfig> page) {
        return  ResultVO.success("查询成功", acOperatorConfigService.selectPage(getPage(page), getCondition(page)));
    }
    
}

