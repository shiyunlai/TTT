package org.tis.tools.security.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/13
 **/
@FeignClient("abf-service")
public interface IAuthFeignService {

    @GetMapping("/acOperators/{id}")
    ResultVO loadUserById(@PathVariable(value = "id") String id);

    @GetMapping("/acApp/{id}")
    ResultVO loadClientById(@PathVariable(value = "id") String id);
}
