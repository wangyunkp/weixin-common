package com.cloud.weixin.api;

import com.cloud.weixin.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信基础AccessToken服务
 */
@FeignClient(name = "${feign-client.weixin-core:weixin-core}", contextId = "access-token")
public interface WxAccessTokenService {
    /**
     * 获取access_token, 不强制刷新access_token.
     *
     * @return the access token
     */
    @GetMapping("/getAccessToken")
    Result<String> getAccessToken();

    /**
     * 获取token时强制刷新,不推荐使用。
     *
     * @param forceRefresh 强制刷新
     * @return access_token
     */
    @GetMapping("/getAccessToken2")
    Result<String> getAccessToken(boolean forceRefresh);
}
