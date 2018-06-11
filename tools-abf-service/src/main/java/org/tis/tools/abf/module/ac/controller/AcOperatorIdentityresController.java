package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityresRequest;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentityres;

/**
 * acOperatorIdentityres的Controller类
 * 身份权限集
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorIdentityres")
public class AcOperatorIdentityresController extends BaseController<AcOperatorIdentityres>  {

    @Autowired
    private IAcOperatorIdentityresService acOperatorIdentityresService;

    @OperateLog(type = OperateType.ADD,desc = "新增身份权限集")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorIdentityresRequest acOperatorIdentityres) {
        acOperatorIdentityresService.add(acOperatorIdentityres);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改身份权限集")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorIdentityresRequest acOperatorIdentityres) {
        AcOperatorIdentityres acOperatorIdentityresQue = acOperatorIdentityresService.selectById(acOperatorIdentityres.getGuid());
        if (acOperatorIdentityresQue == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        AcOperatorIdentityres acOperatorIdentityresUpdate = acOperatorIdentityresService.change(acOperatorIdentityres);
        return ResultVO.success("修改成功！",acOperatorIdentityresUpdate);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除身份权限集")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorIdentityres acOperatorIdentityres = acOperatorIdentityresService.selectById(id);
        if (acOperatorIdentityres == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorIdentityresService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorIdentityres acOperatorIdentityres = acOperatorIdentityresService.selectById(id);
        if (acOperatorIdentityres == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorIdentityres);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorIdentityres> page) {
        return  ResultVO.success("查询成功", acOperatorIdentityresService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/queryByIdentity/{id}")
    public ResultVO queryByIdentity(@RequestBody @Validated SmartPage<AcOperatorIdentityres> page,@PathVariable
    @NotBlank(message = "身份GUID不能为空") String id) {
        return  ResultVO.success("查询成功", acOperatorIdentityresService.queryByIdentity(getPage(page), getCondition
                (page),id));
    }
    
}

