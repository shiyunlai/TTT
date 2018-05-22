package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.controller.request.AcFuncAttrRequest;
import org.tis.tools.abf.module.ac.controller.request.AcFuncAttrUpdateRequest;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;

import java.util.List;

/**
 * acFuncAttr的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acFuncAttr")
@Validated
public class AcFuncAttrController extends BaseController<AcFuncAttr>  {

    @Autowired
    private IAcFuncAttrService acFuncAttrService;

    /**
     * 新增功能属性
     * @param acFuncAttr
     * @return ResultVO
     */
    @OperateLog(type = OperateType.ADD,desc = "新增功能属性")
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcFuncAttrRequest acFuncAttr) {

        AcFuncAttr acFuncAttr1;
        acFuncAttr1 = acFuncAttrService.creatFuncAttr(acFuncAttr.getGuidFunc(),acFuncAttr.getAttrType(),acFuncAttr.getAttrKey(),
                acFuncAttr.getAttrValue(),acFuncAttr.getMemo());
        return ResultVO.success("新增成功！",acFuncAttr1);
    }

    /**
     * 修改功能属性
     * @param acFuncAttr
     * @return ResultVO
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改功能属性")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcFuncAttrUpdateRequest acFuncAttr) {
        AcFuncAttr acFuncAttr1 = acFuncAttrService.selectById(acFuncAttr.getGuid());
        if (acFuncAttr1 == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        AcFuncAttr acFuncAttr2 = acFuncAttrService.changeFuncAttr(acFuncAttr.getGuid(),acFuncAttr.getGuidFunc(),acFuncAttr.getAttrType(),acFuncAttr.getAttrKey(),
                acFuncAttr.getAttrValue(),acFuncAttr.getMemo());
        return ResultVO.success("修改成功！",acFuncAttr2);
    }

    /**
     * 删除功能属性
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除功能属性")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {

        AcFuncAttr acFuncAttr = acFuncAttrService.selectById(id);
        if (acFuncAttr == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        Boolean isDel = acFuncAttrService.deleteById(id);
        return ResultVO.success("删除成功!",isDel);
    }

    /**
     * 根据条件查询功能属性
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.QUERY,desc = "根据条件查询功能属性")
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFuncAttr acFuncAttr = acFuncAttrService.selectById(id);
        if (acFuncAttr == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acFuncAttr);
    }

    /**
     * 根据功能GUID查询功能属性列表
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.QUERY,desc = "根据功能GUID查询功能属性列表")
    @GetMapping("/detailList/{id}")
    public ResultVO detailList(@PathVariable @NotBlank(message = "id不能为空") String id) {
        List<AcFuncAttr> attrList = acFuncAttrService.queryList(id);
        if (attrList == null || 0 == attrList.size()) {
            return ResultVO.error("404", "该功能下没有查到功能属性！");
        }
        return ResultVO.success("查询成功", attrList);
    }


    /**
     * 分页查询功能属性
     * @param page
     * @return ResultVO
     */
    @OperateLog(type = OperateType.QUERY,desc = "分页查询功能属性")
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcFuncAttr> page) {
        return  ResultVO.success("查询成功", acFuncAttrService.selectPage(getPage(page), getCondition(page)));
    }

}

