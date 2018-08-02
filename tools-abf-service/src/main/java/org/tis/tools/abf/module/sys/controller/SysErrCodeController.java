package org.tis.tools.abf.module.sys.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.sys.controller.request.SysErrCodeRequest;
import org.tis.tools.abf.module.sys.entity.SysErrCode;
import org.tis.tools.abf.module.sys.service.ISysErrCodeService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.model.common.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * sysErrCode的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysErrCode")
@Validated
public class SysErrCodeController extends BaseController<SysErrCode>  {

    @Autowired
    private ISysErrCodeService sysErrCodeService;

    @OperateLog(type = OperateType.UPDATE,desc = "修改错误码")
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysErrCodeRequest sysErrCodeRequest) {

        SysErrCode sysErrCode = sysErrCodeService.selectById(sysErrCodeRequest.getGuid());
        if (sysErrCode == null){
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        SysErrCode sysErrCodeChange = sysErrCodeService.change(sysErrCodeRequest);
        return ResultVO.success("修改成功！",sysErrCodeChange);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除错误码")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysErrCode sysErrCode = sysErrCodeService.selectById(id);
        if (sysErrCode == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        sysErrCodeService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysErrCode sysErrCode = sysErrCodeService.selectById(id);
        if (sysErrCode == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysErrCode);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysErrCode> page) {
        return  ResultVO.success("查询成功", sysErrCodeService.selectPage(getPage(page), getCondition(page)));
    }
    
}

