package org.tis.tools.gateway.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 路由失败回退处理
 *
 * @author zhaoch
 * @date 2018/7/25
 **/
@Component
public class ServiceFallbackProvider implements FallbackProvider {

    private static final String MICRO_SERVICE_UNAVAILABLE = "40004";

    @Override
    public String getRoute() {
        //api服务id，如果需要所有调用都支持回退，则return "*"或return null
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        cause.printStackTrace();
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                //响应体
                String body = "{\n" +
                        "    \"code\": \"40004\",\n" +
                        "    \"msg\": \"服务不可用，请稍后再试！\",\n" +
                        "    \"result\": \"\"\n" +
                        "}";
                return new ByteArrayInputStream(body.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }
        };
    }



    @Override
    public ClientHttpResponse fallbackResponse() {
        return null;
    }
}
