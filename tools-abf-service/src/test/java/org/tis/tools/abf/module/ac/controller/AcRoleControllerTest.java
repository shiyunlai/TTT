package org.tis.tools.abf.module.ac.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class AcRoleControllerTest extends BaseTest {


    @Test
    public void add() throws Exception{
        RequestBuilder request = null;
        request = post("/acRoles/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"roleCode\":\"1111\",\"roleName\": \"角色1\", \"enabled\": \"Y\", \"roleDesc\":\"测试用数据\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void queryRoleWithPage() throws Exception{
        RequestBuilder request = null;
        request = post("/acRoles/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{\"current\":\"0\",\"size\": \"10\",\"orderByField\": \"roleCode\"}," +
                        "\"condition\":{\"roleCode\":\"1111\"}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void selectByRoleCode() throws Exception{
        RequestBuilder request = null;
        request = get("/acRoles/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }



    @Test
    public void update() throws Exception{
        RequestBuilder request = null;
        request = put("/acRoles/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"roleCode\":\"1111\",\"roleName\": \"角色2\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void deleteRoleByRoleCode() throws Exception{
        RequestBuilder request = null;
        request = delete("/acRoles/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"roleCode\":\"1111\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

}