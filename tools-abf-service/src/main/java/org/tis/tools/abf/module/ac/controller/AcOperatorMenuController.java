package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.controller.request.AcOperatorMenuRequest;
import org.tis.tools.abf.module.ac.service.IAcOperatorMenuService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.entity.AcOperatorMenu;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * 操作员重组菜单
 * acOperatorMenu的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorMenu")
public class AcOperatorMenuController extends BaseController<AcOperatorMenu>  {

    @Autowired
    private IAcOperatorMenuService acOperatorMenuService;

    @OperateLog(type = OperateType.ADD,desc = "新增父操作员重组菜单")
    @PostMapping("/addRoot")
    public ResultVO addRoot(@RequestBody @Validated AcOperatorMenuRequest acOperatorMenuRequest) {
        acOperatorMenuService.addRoot(acOperatorMenuRequest);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.ADD,desc = "新增子操作员重组菜单")
    @PostMapping("/addChild")
    public ResultVO addChild(@RequestBody @Validated AcOperatorMenuRequest acOperatorMenuRequest) {
        acOperatorMenuService.addChild(acOperatorMenuRequest);
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员重组菜单")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorMenuRequest acOperatorMenuRequest) {
        AcOperatorMenu acOperatorMenu = acOperatorMenuService.selectById(acOperatorMenuRequest.getGuid());
        if (acOperatorMenu == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorMenu = acOperatorMenuService.change(acOperatorMenuRequest);
        return ResultVO.success("修改成功！",acOperatorMenu);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除操作员重组菜单")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorMenu acOperatorMenu = acOperatorMenuService.selectById(id);
        if (acOperatorMenu == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorMenuService.deleteAllOperatorMenu(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorMenu acOperatorMenu = acOperatorMenuService.selectById(id);
        if (acOperatorMenu == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorMenu);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorMenu> page) {
        return  ResultVO.success("查询成功", acOperatorMenuService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/queryByOperatorId/{id}")
    public ResultVO queryByOperatorId(@RequestBody @Validated SmartPage<AcOperatorMenu> page,@PathVariable @NotBlank
            (message = "操作员GUID不能为空") String id) {
        return  ResultVO.success("查询成功", acOperatorMenuService.queryByOperatorId(getPage(page), getCondition(page), id));
    }
}

