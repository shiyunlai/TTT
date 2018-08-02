package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorShortcutRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorShortcut;
import org.tis.tools.abf.module.ac.service.IAcOperatorShortcutService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.model.common.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * acOperatorShortcut的Controller类
 *  操作员快捷菜单
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorShortcut")
@Validated
public class AcOperatorShortcutController extends BaseController<AcOperatorShortcut>  {

    @Autowired
    private IAcOperatorShortcutService acOperatorShortcutService;

    /**
     * 新增操作员快捷菜单
     * @param acOperatorShortcutRequest
     * @return
     */
    @OperateLog(type = OperateType.ADD,desc = "新增操作员快捷菜单")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorShortcutRequest acOperatorShortcutRequest) {
        acOperatorShortcutService.add(acOperatorShortcutRequest);
        return ResultVO.success("新增成功！");
    }

    /**
     * 修改操作员快捷菜单
     * @param acOperatorShortcutRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员快捷菜单")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorShortcutRequest acOperatorShortcutRequest) {
        AcOperatorShortcut acOperatorShortcut = acOperatorShortcutService.selectById(acOperatorShortcutRequest.getGuid());
        if (acOperatorShortcut == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorShortcut = acOperatorShortcutService.change(acOperatorShortcutRequest);
        return ResultVO.success("修改成功！",acOperatorShortcut);
    }

    /**
     * 删除操作员快捷菜单
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除操作员快捷菜单")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorShortcut acOperatorShortcut = acOperatorShortcutService.selectById(id);
        if (acOperatorShortcut == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorShortcutService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorShortcut acOperatorShortcut = acOperatorShortcutService.selectById(id);
        if (acOperatorShortcut == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorShortcut);
    }

    /**
     * 列表查询
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorShortcut> page) {
        return  ResultVO.success("查询成功", acOperatorShortcutService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 根据操作员id查询列表
     * @param page
     * @param id
     * @return
     */
    @PostMapping("/queryByOperator/{id}")
    public ResultVO queryByOperator(@RequestBody @Validated SmartPage<AcOperatorShortcut> page,@PathVariable @NotBlank(message = "id不能为空") String id) {
        return  ResultVO.success("查询成功", acOperatorShortcutService.queryByOperator(getPage(page), getCondition(page),id));
    }
}

