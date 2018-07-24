package org.tis.tools.abf.module.om.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * omGroup的测试类
 *
 * @author ljhua
 * @date 2018/06/21
 */
public class OmGroupControllerTest extends BaseTest {

    /**
     * 添加根工作组
     *
     * @throws Exception
     */
    @Test
    public void addRoot() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/root")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupNam\":\"tip小组\",\"groupType\":\"project\",\"orgCode\":\"ORG北京总行00007\",\"groupDesc\":\"开发\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 添加子工作组
     *
     * @throws Exception
     */
    @Test
    public void addChild() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/child")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupNam\":\"RCT小组2\",\"groupType\":\"project\",\"orgCode\":\"ORG北京总行00007\"},\"groupDesc\":\"测试\",\"guidParents\":\"1006710540321443842\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询所有工作组列表
     *
     * @throws Exception
     */
    @Test
    public void list() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询工作组详情
     * @throws Exception
     */
    @Test
    public void detail() throws Exception {
        RequestBuilder request = null;
        request = get("/omGroups/GROUP0100000009")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 删除工作组
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/GROUP0100000009")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 关闭工作组
     * @throws Exception
     */
    @Test
    public void enable() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups/GROUP0100000009/enable")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 重启工作组
     * @throws Exception
     */
    @Test
    public void reEnable() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups/GROUP0100000009/reenable/ture")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 修改工作组
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupCode\":\"GROUP0100000009\",\"groupNam\":\"tip小组\",\"orgCode\":\"ORG北京总行00007\",\"groupDesc\":\"开发\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询在此工作组的员工
     * @throws Exception
     */
    @Test
    public void loadEmpIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000005/emp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{\"current\":1,\"size\":2}}")   //第一页，每页2条
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 加载不在此工作组的人员列表(同属同一机构)
     * @throws Exception
     */
    @Test
    public void loadEmpNotIn() throws Exception {
        RequestBuilder request = null;
        request = get("/omGroups/GROUP0200000005/empOrgNotin/1006425335719268354")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")   //第一页，每页2条
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 为工作组添加员工
     * @throws Exception
     */
    @Test
    public void addEmp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/empGroup")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidGroup\":\"1007179295669379073\",\"guidEmp\":\"1007526131466887170\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 删除人员-工作组关联
     * @throws Exception
     */
    @Test
    public void deleteEmp() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/1006735296508723202/empGroup/1006347529454911490")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询工作组下岗位列表
     * @throws Exception
     */
    @Test
    public void loadPositionIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/positionIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询工作组下岗位列表
     * @throws Exception
     */
    @Test
    public void loadPositionInNotPage() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/positionNotPage")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询不在此工作组的岗位
     * @throws Exception
     */
    @Test
    public void loadPositionNotIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/positionNotIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 为工作组新增岗位
     * @throws Exception
     */
    @Test
    public void addPosition() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/position")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupCode\":\"GROUP0200000007\",\"omPositionRequest\":{\"guidOrg\":\"1006425335719268354\",\"positionName\":\"A岗位\"," +
                        "\"positionStatus\":\"running\",\"positionCode\":\"pos123456\",\"isleaf\":\"YES\",\"subCount\":1}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 删除工作组岗位
     * @throws Exception
     */
    @Test
    public void deletePosition() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/GROUP0200000005/position/1009404592691101698")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 为工作组添加应用
     * @throws Exception
     */
    @Test
    public void addAcApp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/app")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupCode\":\"GROUP0200000007\",\"appGuidList\":[\"997406250109054977\"]}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询工作组下的应用
     * @throws Exception
     */
    @Test
    public void queryApp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/app")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询可以为工作组添加的应用
     * @throws Exception
     */
    @Test
    public void queryNotInApp() throws Exception {
        RequestBuilder request = null;
        request = get("/omGroups/GROUP0200000007/availableApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 删除工作组下的应用
     * @throws Exception
     */
    @Test
    public void deleteApp() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/groupApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"group\":\"GROUP0200000007\",\"guidApp\":\"997405585693552641\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }

    /**
     * 查询工作组的树
     * @throws Exception
     */
    @Test
    public void selectTree() throws Exception {
        RequestBuilder request = null;
        request = get("/omGroups/null/tree")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\n\t\"code\":\"200\",")));
    }
}
