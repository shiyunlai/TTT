package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.exception.AcMenuManagementException;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.ac.service.IAcMenuService;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * acMenu的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acMenus")
public class AcMenuController extends BaseController<AcMenu>  {

    @Autowired
    private IAcMenuService acMenuService;

    /**
     * 新增根菜单
     * @param acMenu 新增菜单对象
     * @return 新增结果
     */
    @OperateLog(
            type = OperateType.ADD,  // 操作类型
            desc = "新增根菜单") // 操作对象的关键值的键值名
    @ResponseBody
    @PostMapping("/addAcMenu")
    public ResultVO addAcMenu(@RequestBody @Validated AcMenu acMenu) {
        AcMenu result = acMenuService.createRootMenu(acMenu);
        if(result == null){
            return ResultVO.error("422","新增菜单失败！");
        }
        return ResultVO.success("新增成功！",result);
    }


    @ResponseBody
    @PostMapping("/addMenu")
    public ResultVO add(@RequestBody @Validated AcMenu acMenu) {
        boolean result = acMenuService.insert(acMenu);
        if(!result){
            return ResultVO.error("422","新增菜单失败！");
        }
        return ResultVO.success("新增成功！",result);
    }

    /**
     * 新增子菜单
     * @param acMenu 新增子菜单对象
     * @return 新增结果
     */
    @OperateLog(
            type = OperateType.ADD,
            desc = "添加子菜单")
    @ResponseBody
    @PostMapping("/addSubAcmenu")
    public ResultVO addSubAcmenu(@RequestBody @Validated AcMenu acMenu) {
        AcMenu result = acMenuService.createChildMenu(acMenu);
        if(result == null){
            return ResultVO.error("422","新增菜单失败！");
        }
        return ResultVO.success("新增成功！",result);
    }

    /**
     * 修改菜单
     * @param acMenu 修改菜单对象
     * @return 修改结果
     */
    @OperateLog(
            type = OperateType.UPDATE,
            desc = "修改菜单")
    @ResponseBody
    @PutMapping("/updateAcMenu")
    public ResultVO update(@RequestBody @Validated AcMenu acMenu) {
        AcMenu result = acMenuService.updateAcMenu(acMenu);
        if(result == null){
            return ResultVO.error("422","修改菜单失败！");
        }
        return ResultVO.success("修改成功！",result);
    }

    /**
     * 删除菜单
     * @param id 要删除菜单的GUID
     * @return 删除结果
     */
    @OperateLog(
            type = OperateType.DELETE,
            desc = "删除菜单")
    @ResponseBody
    @DeleteMapping("/deleteAcMenu/{id}")
    public ResultVO deleteAcMenu(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcMenu result = acMenuService.deleteAllSubAcMenu(id);
        if(result == null){
            return ResultVO.error("422","删除菜单失败！");
        }
        return ResultVO.success("删除成功" ,result);
    }

    /**
     * 查询单条记录
     * @param id 要查询菜单的GUID
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/querySingAcMenu/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcMenu acMenu = acMenuService.selectById(id);
        if (acMenuService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acMenu);
    }

    @ResponseBody
    @PostMapping("/queryPageAllList")
    public ResultVO list(@RequestBody @Validated SmartPage<AcMenu> page) {
        return  ResultVO.success("查询成功", acMenuService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 查询应用下根菜单数据
     * @param id 应用的GUID
     * @return 所有子菜单数据
     */
    @ResponseBody
    @GetMapping("/queryAcMenuLists/{id}")
    public ResultVO getMenu(@PathVariable @NotBlank(message = "id不能为空") String id){
        List lists = acMenuService.queryRootMenu(id);
        if(lists.size() == 0){
           return ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }

    /**
     * 查询子菜单数据
     * @param id 父菜单的GUID
     * @return 所有子菜单数据
     */
    @ResponseBody
    @GetMapping("/querySubAcMenuLists/{id}")
    public ResultVO getSubMenu(@PathVariable @NotBlank(message = "id不能为空") String id){
        List lists = acMenuService.selectSubMenu(id);
        if(lists == null){
            ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }

    /**
     * 查询分页数据
     * @param offset 当前页数
     * @param limit 每页记录数
     * @return 翻页对象
     */
    @ResponseBody
    @GetMapping("/queryPageAcMenuLists/{offset}/{limit}")
    public ResultVO getPageAcMenu(@PathVariable @NotBlank(message = "id不能为空") int offset
            ,@PathVariable @NotBlank(message = "limit不能为空") int limit){
        Page lists = acMenuService.queryPageAcMenu(offset,limit);
        if(lists == null){
            ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }

    /**
     * 菜单移动
     *
     * @param targetGuid 目标菜单GUID
     * @param moveGuid   移动的菜单GUID
     * @param order      排序
     * @throws AcMenuManagementException
     */
    @OperateLog(
            type = OperateType.UPDATE,
            desc = "修改菜单（移动）")
    @ResponseBody
    @GetMapping("/queryMoveMenuLists/{targetGuid}/{moveGuid}")
    public ResultVO getMoveMenu(@PathVariable @NotBlank(message = "targetGuid不能为空") String targetGuid
            , @PathVariable @NotBlank(message = "moveGuid不能为空")String moveGuid
            ,@PathVariable @NotBlank(message = "order不能为空")BigDecimal order){
        AcMenu lists = acMenuService.moveMenu(targetGuid,moveGuid,order);
        if(lists == null){
            ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }

}

