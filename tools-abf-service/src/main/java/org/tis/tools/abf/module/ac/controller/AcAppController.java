package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcAppAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcAppUpdateRequest;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

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
    @OperateLog(type = OperateType.ADD, desc = "新增应用")
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcAppAddRequest request){
        AcApp app;

        app = acAppService.creatRootApp(request.getAppCode(),request.getAppName(),request.getAppType(),request.getUrl(),
                request.getIpAddr(),request.getIpPort(),request.getAppDesc(),request.getIsopen(),request.getOpenDate());

        return ResultVO.success("新增成功!",app);
    }

    /**
     * 根据ID修改应用
     * @param updateRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,  desc = "修改应用")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppUpdateRequest updateRequest) {
        AcApp app;

        AcApp acApp = acAppService.selectById(updateRequest.getGuid());
        if (acAppService == null || (!"0".equals(acApp.getDataStatus()))) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        app = acAppService.changeById(updateRequest.getGuid(),updateRequest.getAppCode(),updateRequest.getAppName(),
                updateRequest.getAppType(),updateRequest.getIsopen(),updateRequest.getOpenDate() ,updateRequest.getUrl(), updateRequest.getIpAddr(),
                updateRequest.getIpPort(), updateRequest.getAppDesc());
        return ResultVO.success("修改成功！",app);
    }


    /**
     * 根据ID删除应用
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE, desc = "删除应用")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {

        Boolean isDel = acAppService.deleteById(id);
        return ResultVO.success("删除成功",isDel);

    }

    @OperateLog(type = OperateType.UPDATE, desc = "开通应用")
    @PutMapping("/openApp/{id}")
    public ResultVO openApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acApp = acAppService.selectById(id);
        acApp.setIsopen(YON.YES);
        acAppService.updateById(acApp);
        return ResultVO.success("应用已开通",acApp);
    }

    @OperateLog(type = OperateType.UPDATE, desc = "停用应用")
    @PutMapping("/stopApp/{id}")
    public ResultVO stopApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acApp = acAppService.selectById(id);
        acApp.setIsopen(YON.NO);
        acAppService.updateById(acApp);
        return ResultVO.success("应用已停用",acApp);
    }

    /**
     * 根据ID查询应用
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.QUERY, desc = "查询应用")
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcApp acApp = acAppService.selectById(id);
        if (acAppService == null  || (!"0".equals(acApp.getDataStatus()))) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acApp);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcApp> page) {
        return  ResultVO.success("查询成功", acAppService.selectPage(getPage(page), getCondition(page)));
    }

}

