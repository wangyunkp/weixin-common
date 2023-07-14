package com.cloud.weixin.api;

import com.cloud.weixin.bean.oauth2.WxOAuth2UserInfo;
import com.cloud.weixin.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信授权相关接口
 */
@FeignClient(name = "${feign-client.weixin-core:weixin-core}", contextId = "wx-oath2")
public interface WxOAuth2Service {
    /**
     * 通过code换取网页授权access_token
     * 详情请见: <a href="https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html">网页授权</a>
     *
     * @return
     */
    @GetMapping("/getAccessToken")
    Result<String> getAccessToken(String code);

    /**
     * <pre>
     * 刷新oauth2的access token.
     * </pre>
     *
     * @param refreshToken 刷新token
     * @return 新的token对象
     */
    @GetMapping("/refreshAccessToken")
    Result<String> refreshAccessToken(String refreshToken);

    /**
     * <pre>
     * 用oauth2获取用户信息, 当前面引导授权时的scope是snsapi_userinfo的时候才可以.
     * </pre>
     *
     * @param accessToken token对象
     * @param lang              返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return 用户对象
     */
    @GetMapping("/getUserInfo")
    Result<WxOAuth2UserInfo> getUserInfo(String accessToken, String lang);

    /**
     * <pre>
     * 验证oauth2的access token是否有效.
     * </pre>
     *
     * @param accessToken token对象
     * @return 是否有效
     */
    @GetMapping("/validateAccessToken")
    Result<Boolean> validateAccessToken(String accessToken);
}
