package com.cloud.weixin.nacos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信相关配置
 */
@Data
@Component
public class WxNacosConfig {
    /**
     * 调用微信接口超时配置ms 2000 = 2秒
     */
    @Value("${wx.timeout}")
    private Integer timeout;

    /**
     *
     */
    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;
}
