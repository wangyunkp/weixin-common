package com.cloud.weixin.config;

public interface WxConfig {
    /**
     * 获取accessToken
     *
     * @return
     */
    String getAccessToken();

    /**
     * access token 是否已过期
     *
     * @return the boolean
     */
    boolean isAccessTokenExpired();


    /**
     * 更新access token信息
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateAccessToken(String accessToken, int expiresInSeconds);
}
