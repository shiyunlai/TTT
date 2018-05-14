package org.tis.tools.abf.module.sys.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.abf.module.sys.controller.request.SysDictItemRequest;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.service.ISysDictItemService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.core.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * sysDictItem的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysDictItem")
@Validated
@Api(description = "业务字典项")
public class SysDictItemController  extends BaseController {
    @Autowired
    private ISysDictItemService iSysDictItemService;
    @Autowired
    private ISysDictService iSysDictService;
    /**
     * 新增业务字典项
     * @param request
     */
    @OperateLog(
            operateType = OperateType.ADD,
            operateDesc = "新增业务字典项",
            retType = ReturnType.Object,
            id = "guid",
            name = "itemName",
            keys = {"guidDict", "itemType"}
    )
    @ApiOperation(value = "新增业务字典项", notes = "实际参数以下面DataType为准")
    @PostMapping("/add")
    public ResultVO addSysDictItem(@RequestBody @Validated SysDictItemRequest request) {
        SysDictItem sysDictItem = new SysDictItem();
        SysDictItem sysDictItem1 = iSysDictItemService.addDictItem(request.getGuidDict(),request.getItemName(),request.getItemType(),request.getSendValue(),request.getSendValue(),request.getSeqNo().toString(),request.getItemDesc());
        return ResultVO.success("添加成功",sysDictItem1);
    }
    /**
     * 修改业务字典项
     * @param request
     */
    @OperateLog(
            operateType = OperateType.UPDATE,
            operateDesc = "修改业务字典项",
            retType = ReturnType.Object,
            id = "guid",
            name = "itemName",
            keys = {"guidDict", "itemType"}
    )
    @ApiOperation(value = "修改业务字典项", notes = "实际参数以下面DataType为准")
    @PutMapping
    public ResultVO updateSysDictItem(@RequestBody @Validated SysDictItemRequest request) {
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem = iSysDictItemService.editSysDictItem(request.getGuid(),request.getGuidDict(),request.getItemName(),request.getItemType(),request.getItemValue(),
                request.getSendValue(),request.getSeqNo().toString(),request.getItemDesc());
        return ResultVO.success("修改成功",sysDictItem);
    }
    /**
     * 删除业务字典项
     * @param id
     */
    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除业务字典项",
            retType = ReturnType.Object,
            id = "guid",
            name = "itemName",
            keys = {"guidDict", "itemType"}
    )
    @ApiOperation(value = "删除业务字典项", notes = "根据guid删除对应的数据信息")
    @DeleteMapping("/{id}")
    public ResultVO editSysDictItem(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem = iSysDictItemService.deleteDictItem(id);
        return ResultVO.success("删除成功",sysDictItem);
    }

    /**
     * 查询单个业务字典项
     * @param id
     */
    @ApiOperation(value = "查询业务字典项", notes = "根据guidDict查询对应的业务字典信息")
    @PostMapping("/queryGuidDict")
    public ResultVO querySysDictItem(@RequestBody @Validated @NotBlank(message = "ID不能为空") String id){
        SysDictItem sysDictItem = iSysDictItemService.guidQueryOneSysDic(id);
        SysDict sysDict = iSysDictService.queryOneSysDictByGuid(sysDictItem.getGuidDict());
        return ResultVO.success("查询成功",sysDict);
    }

    /**
     * 根据业务字典的guid查询对应所有字典项
     * @param id
     */
    @ApiOperation(value = "查询单条业务字典项", notes = "根据guid查询出对应的数据信息")
    @GetMapping("/{id}")
    public ResultVO querySysDictItemList(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem = iSysDictItemService.guidQueryOneSysDic(id);
        return ResultVO.success("查询成功",sysDictItem);

    }

    /**
     * 查询所有字典项
     */
    @ApiOperation(value = "查询所有业务字典项", notes = "无需输入参数")
    @PostMapping("/queryAllSysItem")
    public ResultVO queryAllDictItem() {
        return ResultVO.success("查询成功", iSysDictItemService.querySysDictItemList());
    }

//    /**
//     * 修改字典项默认值
//     *
//     */
//    @OperateLog(
//            operateType = OperateType.UPDATE,
//            operateDesc = "设置字典默认字典项",
//            retType = ReturnType.Object,
//            id = "dictKey",
//            name = "dictName",
//            keys = "defaultValue"
//    )
//
//    @RequestMapping("/setDefaultDictValue")
//    public ResultVO setDefaultDictValue(@RequestBody @Validated SysDictItemRequest request) {
//        SysDictItem sysDictItem = new SysDictItem();
//        return ResultVO.success("添加成功",sysDictItem);
//    }

}
