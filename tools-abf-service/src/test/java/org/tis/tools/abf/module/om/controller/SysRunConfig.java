package org.tis.tools.abf.module.om.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class SysRunConfig extends BaseTest{
        @Test
        public void update() throws Exception {
            RequestBuilder requesSt = null;
            requesSt = post("/SysConfig/update_Config")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{ \"guid\":\"986960932238368769\",\"guidApp\":\"zxc\",\"groupName\": \"zxc\",\"keyName\": \"zxc\",\"valueFrom\":\"123\",\"value\":\"123\",\"description\":\"123\"}")
                    .accept(MediaType.APPLICATION_JSON);
            mvc.perform(requesSt)
                    .andExpect(content().string(startsWith("{\"code\":\"200\",")));
        }
    @Test
    public void query() throws Exception {
        RequestBuilder requesSt = null;
        requesSt = post("/SysConfig/query_Config")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guid\":\"986974019326697474\",\"guidApp\":\"CSsd1\",\"groupName\": \"测试sd\",\"keyName\": \"测试sd\",\"valueFrom\":\"sdsd\",\"value\":\"sdsd\",\"description\":\"sdsd\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requesSt)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    @Test
    public void add() throws Exception {
        RequestBuilder requesSt = null;
        requesSt = post("/SysConfig/add_Config")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidApp\":\"CSsd1\",\"groupName\":\"测试sd\",\"keyName\":\"测试sd\",\"valueFrom\":\"sdsd\",\"value\":\"sdsd\",\"description\":\"sdsd\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requesSt)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }
}
