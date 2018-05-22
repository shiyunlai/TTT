package org.tis.tools.abf.module.ac.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * 功能的Controller测试类
 * Created by chenchao
 * Created on 2018/5/14 9:24
 */
public class AcFuncControllerrTest extends BaseTest {

    /**
     * 新增根功能
     * @throws Exception
     */
    @Test
    public void addRoot()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = post("/acFunc/addRoot")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidApp\":\"989411000669696001\",\"funcType\": \"PAGEPROCESS\", \"funcCode\": \"002\", \"funcName\":\"002功能\", \"displayOrder\":\"2\", \"funcDesc\":\"功能002的描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 新增子功能
     * @throws Exception
     */
    @Test
    public void add() throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = post("/acFunc/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidApp\":\"989411000669696001\",\"funcType\": \"PAGEPROCESS\", \"funcCode\": \"004\", \"funcName\":\"004功能\", \"displayOrder\":\"4\", \"funcDesc\":\"功能004的描述\", \"guidFunc\":\"996310501456080897\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 修改子功能
     * @throws Exception
     */
    @Test
    public void update() throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder =put("/acFunc")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guid\":\"996313135529201665\",\"guidApp\":\"994494537240281090\",\"funcType\": \"PAGEPROCESS\", \"funcCode\": \"005\", \"funcName\":\"005功能\", \"displayOrder\":\"5\", \"funcDesc\":\"功能005的修改描述\", \"guidFunc\":\"996310501456080897\", \"isopen\":\"S\", \"ischeck\":\"N\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 修改根功能
     * @throws Exception
     */
    @Test
    public void updateRoot() throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder =put("/acFunc/updateRoot")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guid\":\"996310501456080897\",\"guidApp\":\"994494537240281090\",\"funcType\": \"PAGEPROCESS\", \"funcCode\": \"005\", \"funcName\":\"005功能\", \"displayOrder\":\"5\", \"funcDesc\":\"功能005的修改描述\",\"isopen\":\"S\", \"ischeck\":\"N\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 删除子功能
     * @throws Exception
     */
    @Test
    public void delete() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/acFunc/996313135529201665")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 删除根功能
     * @throws Exception
     */
    @Test
    public void deleteRoot() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/acFunc/deleteRoot/996310501456080897")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 查询功能
     * @throws Exception
     */
    @Test
    public void get() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("/acFunc/996301986758868993")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 查询根功能的子列表
     * @throws Exception
     */
    @Test
    public void oneList() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("/acFunc/oneList/996295466776137729")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }


}
