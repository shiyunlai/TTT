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

public class OmGroupControllerTest extends BaseTest {

    @Test
    public void addRoot() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/root")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupNam\":\"tip小组\",\"groupType\":\"project\",\"orgCode\":\"ORG北京总行00007\",\"groupDesc\":\"开发\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void addChild() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/child")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupNam\":\"RCT小组2\",\"groupType\":\"project\",\"orgCode\":\"ORG北京总行00007\"},\"groupDesc\":\"测试\",\"guidParents\":\"1006710540321443842\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void list() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void detail() throws Exception {
        RequestBuilder request = null;
        request = get("/omGroups/GROUP0100000009")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void delete() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/GROUP0100000009")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void enable() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups/GROUP0100000009/enable")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void reEnable() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups/GROUP0100000009/reenable/ture")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void update() throws Exception {
        RequestBuilder request = null;
        request = put("/omGroups")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupCode\":\"GROUP0100000009\",\"groupNam\":\"tip小组\",\"orgCode\":\"ORG北京总行00007\",\"groupDesc\":\"开发\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void loadEmpIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000005/emp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{\"current\":1,\"size\":2}}")   //第一页，每页2条
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void loadEmpNotIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000005/empOrgNotin/1006425335719268354")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{\"current\":1,\"size\":2}}")   //第一页，每页2条
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void addEmp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/empGroup")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidGroup\":\"1007179295669379073\",\"guidEmp\":\"1007526131466887170\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void deleteEmp() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/1006735296508723202/empGroup")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void loadPositionIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/positionIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void loadPositionNotIn() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/positionNotIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void addPosition() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/groupPosition")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupGuid\":\"1007179295669379073\",\"posGuidlist\":[\"1006910028172492802\",\"1006907434033217538\"]}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void deletePosition() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/groupPosition")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("[\"1006853519543746562\"]")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void addAcApp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/groupApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"groupGuid\":\"1006728472862670850\",\"appGuid\":\"997406250109054977\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void queryApp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/inApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void queryNotInApp() throws Exception {
        RequestBuilder request = null;
        request = post("/omGroups/GROUP0200000007/NotInApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void deleteApp() throws Exception {
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/omGroups/groupApp")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guid\":\"1006865716260904962\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }
}
