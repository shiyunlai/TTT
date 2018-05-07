package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.ac.service.IAcMenuService;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

import java.util.List;

/**
 * acMenu的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acMenu")
public class AcMenuController extends BaseController<AcMenu>  {

    @Autowired
    private IAcMenuService acMenuService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcMenu acMenu) {
        acMenuService.insert(acMenu);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcMenu acMenu) {
        acMenuService.updateById(acMenu);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acMenuService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcMenu acMenu = acMenuService.selectById(id);
        if (acMenuService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acMenu);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcMenu> page) {
        return  ResultVO.success("查询成功", acMenuService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 查询所有菜单数据
     * @return 所有菜单数据
     */
    @GetMapping("/allList")
    public ResultVO getlist(){
        EntityWrapper wrapper = new EntityWrapper();
        List<AcMenu> lists = acMenuService.selectList(wrapper);
        if(lists == null){
            ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }

    /**
     * 查询子菜单数据
     * @return 所有菜单数据
     */
    @GetMapping("/subMenuLists/{id}")
    public ResultVO getSubMenu(@PathVariable @NotBlank(message = "id不能为空") String id){
        List lists = acMenuService.selectSubMenu(id);
        if(lists == null){
            ResultVO.error("404","查询无数据！");
        }
        return ResultVO.success("查询成功",lists);
    }
}

