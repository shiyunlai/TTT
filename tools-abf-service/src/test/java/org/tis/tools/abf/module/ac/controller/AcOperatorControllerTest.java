package org.tis.tools.abf.module.ac.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tis.tools.abf.base.BaseTest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AcOperatorControllerTest extends BaseTest {

    @Test
    public void add() throws Exception{
        RequestBuilder request ;
        request = post("/acOperators/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"userId\":\"1111\",\"password\": \"111\", \"operatorStatus\": \"login\", \"authMode\":\"password\", \"lockLimit\":\"2\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception{
        RequestBuilder request ;
        request = put("/acOperators")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"userId\":\"1111\",\"password\": \"111\", \"operatorStatus\": \"login\", \"authMode\":\"dynpassword\", \"lockLimit\":\"2\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception{
        RequestBuilder request ;
        request = MockMvcRequestBuilders.delete("/acOperators/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"userId\":\"1111\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void detail() throws Exception{
        RequestBuilder request ;
        request = get("/acOperators/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"userId\":\"1111\",\"password\": \"111\", \"operatorStatus\": \"login\", \"authMode\":\"dynpassword\", \"lockLimit\":\"2\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void list() throws Exception{
        RequestBuilder request ;
        request = post("/acOperators/list" +
                "")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page\":{\"current\":\"0\",\"size\": \"10\",\"orderByField\": \"password\"}," +
                        "\"condition\":{\"userId\":\"1111\"}}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }
}