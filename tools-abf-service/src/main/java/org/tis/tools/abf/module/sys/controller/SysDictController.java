package org.tis.tools.abf.module.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.abf.module.sys.controller.request.SysDictQueryRequest;
import org.tis.tools.abf.module.sys.controller.request.SysDictRequest;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.entity.enums.DictType;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/sysDict")
@Validated
@Api(description = "业务字典")
public class SysDictController  extends BaseController {
    @Autowired
    private ISysDictService iSysDictService;
    /**
     * 新增业务字典
     * @param request
     * @return
     */
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增业务字典", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guidApp", // 操作对象标识
            name = "groupName", // 操作对象名
            keys = {"guidApp","groupName"}
    )
    @ApiOperation(value = "新增业务字典", notes = "实际参数以下面DataType为准，guid不需要输入")
    @PostMapping("/add")
    public ResultVO createSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(DictType.valueOf(request.getDictType()));
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(DictFromType.valueOf(request.getFromType()));
        sysDict =  iSysDictService.addDict(sysDict);
        return ResultVO.success("添加成功",sysDict);
    }

    /**
     * 删除业务字典
     * @param id
     * @return
     * @throws SysManagementException
     * @throws ParseException
     */
    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除业务字典",
            retType = ReturnType.Object,
            id = "guid",
            name = "dictName",
            keys = {"dictKey", "dictType"}
    )
    @ApiOperation(value = "删除业务字典", notes = "根据guid删除对应的数据信息")
    @DeleteMapping("/{id}")
    public ResultVO deleteSysDict(@PathVariable @NotBlank String id) {
        SysDict sysDict = iSysDictService.deleteDict(id);
        return ResultVO.success("删除成功",sysDict);
    }

    /**
     * 修改业务字典
     * @param request
     */
    @OperateLog(
            operateType = OperateType.UPDATE,
            operateDesc = "修改业务字典",
            retType = ReturnType.Object,
            id = "guid",
            name = "dictName",
            keys = {"dictKey", "dictType"}
    )
    @ApiOperation(value = "修改业务字典", notes = "根据guid查询查询出需要修改的数据并进行更新，参数参考DataType内容")
    @PutMapping
    public ResultVO updateSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setGuid(request.getGuid());
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(DictType.valueOf(request.getDictType()));
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(DictFromType.valueOf(request.getFromType()));
        iSysDictService.editSysDict(sysDict);
        return ResultVO.success("修改成功",sysDict);
    }

    /**
     * 查询单个业务字典
     * @param id
     * @return List
     */
    @ApiOperation(value = "查询业务字典", notes = "根据guid查询出对应的数据信息")
    @GetMapping("/{id}")
    public ResultVO querySysDict(@PathVariable @Validated  @NotBlank String id) {
        SysDict list = iSysDictService.querySysDictByGuid(id);
        return ResultVO.success("查询成功",list);
    }

    /**
     * 查询所有业务字典
     */
    @ApiOperation(value = "查询所有的业务字典", notes = "不需要参数传入")
    @PostMapping ("/querySysDictList")
    public ResultVO querySysDictList(){
        List<SysDict> sysDict = iSysDictService.querySysDicts();
        return ResultVO.success("查询成功",sysDict);
    }
    @ApiOperation(value = "dictKey和dictName查询", notes = "只需要传入dictKey跟dictName参数进行筛选")
    @PostMapping("/dictKeyOrQuery")
    public ResultVO dictKeyOrQuery(@RequestBody @Validated SysDictQueryRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictName(request.getDictName());
        return ResultVO.success("查询成功",iSysDictService.dictKeyQuery(sysDict));
    }
}
