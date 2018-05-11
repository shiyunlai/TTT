package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.controller.request.AcAppAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcAppUpdateRequest;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcApp;

import java.util.List;

/**
 * acApp的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acApp")
@Validated
public class AcAppController extends BaseController<AcApp>  {

    @Autowired
    private IAcAppService acAppService;


    /**
     * 新增应用
     * @param request
     * @return ResultVO
     */
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"}) // 操作对象的关键值的键值名
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcAppAddRequest request){
        AcApp app;

        AcAppType appType = null;
        if ("local".equals(request.getAppType()) || "LOCAL".equals(request.getAppType())){
            appType = AcAppType.LOCAL;
        }else if("remote".equals(request.getAppType()) || "REMOTE".equals(request.getAppType())){
            appType = AcAppType.REMOTE;
        }
        app = acAppService.creatRootApp(request.getAppCode(),request.getAppName(),appType,request.getUrl(),
                request.getIpAddr(),request.getIpPort(),request.getAppDesc());

        return ResultVO.success("新增成功!",app);
    }

    /**
     * 根据ID修改应用
     * @param updateRequest
     * @return
     */
    @OperateLog(
            operateType = OperateType.UPDATE, //操作类型
            operateDesc = "修改应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"}) // 操作对象的关键值的键值名
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppUpdateRequest updateRequest) {
        AcApp app;

        AcAppType appType = null;
        if ("local".equals(updateRequest.getAppType()) || "LOCAL".equals(updateRequest.getAppType())){
            appType = AcAppType.LOCAL;
        }else if("remote".equals(updateRequest.getAppType()) || "REMOTE".equals(updateRequest.getAppType())){
            appType = AcAppType.REMOTE;
        }

        YON ispoen = null;
        if ("y".equals(updateRequest.getIsopen()) || "Y".equals(updateRequest.getIsopen())){
            ispoen = YON.YES;
        }else if("n".equals(updateRequest.getIsopen()) || "N".equals(updateRequest.getIsopen())){
            ispoen = YON.NO;
        }

        app = acAppService.changeById(updateRequest.getGuid(),updateRequest.getAppCode(),updateRequest.getAppName(),
                appType,ispoen,updateRequest.getOpenDate() ,updateRequest.getUrl(), updateRequest.getIpAddr(),
                updateRequest.getIpPort(), updateRequest.getAppDesc());
        return ResultVO.success("修改成功！",app);
    }


    /**
     * 根据ID删除应用
     * @param id
     * @return ResultVO
     */
    @OperateLog(
            operateType = OperateType.DELETE, //操作类型
            operateDesc = "删除应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"})
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {

        AcApp acApp = acAppService.selectById(id);

        YON isopen = acApp.getIsopen();

        if ("Y".equals(isopen.toString()) || "y".equals(isopen.toString())){
            return ResultVO.error("404","该应用已开通不能进行删除!");
        }else if("N".equals(isopen.toString()) || "n".equals(isopen.toString())){
            Boolean isDel = acAppService.deleteById(id);
            return ResultVO.success("删除成功",isDel);
        }else {
            return null;
        }
    }

    @OperateLog(
            operateType = OperateType.UPDATE, //操作类型
            operateDesc = "开通应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"})
    @PutMapping("/openApp/{id}")
    public ResultVO openApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acAppquery = acAppService.selectById(id);
        if (acAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        YON isopen = YON.YES;

        AcAppType appType = null;
        if ("local".equals(acAppquery.getAppType()) || "LOCAL".equals(acAppquery.getAppType())){
            appType = AcAppType.LOCAL;
        }else if("remote".equals(acAppquery.getAppType()) || "REMOTE".equals(acAppquery.getAppType())){
            appType = AcAppType.REMOTE;
        }


        AcApp acApp = acAppService.changeById(id,acAppquery.getAppCode(),acAppquery.getAppName(),appType,isopen,acAppquery.getOpenDate(),acAppquery.getUrl(),acAppquery.getIpAddr(),acAppquery.getIpPort(),acAppquery.getAppDesc());

        return ResultVO.success("应用已开通",acApp);
    }

    @OperateLog(
            operateType = OperateType.UPDATE, //操作类型
            operateDesc = "停用应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"})
    @PutMapping("/stopApp/{id}")
    public ResultVO stopApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acAppquery = acAppService.selectById(id);
        if (acAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        YON isopen = YON.NO;

        AcAppType appType = null;
        if ("local".equals(acAppquery.getAppType()) || "LOCAL".equals(acAppquery.getAppType())){
            appType = AcAppType.LOCAL;
        }else if("remote".equals(acAppquery.getAppType()) || "REMOTE".equals(acAppquery.getAppType())){
            appType = AcAppType.REMOTE;
        }

        AcApp acApp = acAppService.changeById(id,acAppquery.getAppCode(),acAppquery.getAppName(),appType,isopen,acAppquery.getOpenDate(),acAppquery.getUrl(),acAppquery.getIpAddr(),acAppquery.getIpPort(),acAppquery.getAppDesc());

        return ResultVO.success("应用已开通",acApp);
    }

    /**
     * 根据ID查询应用
     * @param id
     * @return
     */
    @OperateLog(
            operateType = OperateType.QUERY, //操作类型
            operateDesc = "查询应用", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"})
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcApp acApp = acAppService.selectById(id);
        if (acAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acApp);
    }

    /**
     * 根据应用列表
     * @return
     */
    @OperateLog(
            operateType = OperateType.QUERY, //操作类型
            operateDesc = "查询应用列表", // 操作描述
            retType = ReturnType.List, // 返回类型，对象或数组
            id = "appCode", // 操作对象标识
            name = "appName", // 操作对象名
            keys = {"appCode", "appName"})
    @PostMapping("/list")
    public ResultVO appList(){

        List<AcApp> appList = null;

        appList = acAppService.selectAppList();

        return ResultVO.success("查询成功",appList);
    }

    /*@PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcApp> page) {
        return  ResultVO.success("查询成功", acAppService.selectPage(getPage(page), getCondition(page)));
    }*/

}

