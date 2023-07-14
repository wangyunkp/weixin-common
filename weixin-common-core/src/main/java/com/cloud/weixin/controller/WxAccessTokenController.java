package com.cloud.weixin.controller;

import com.cloud.weixin.api.WxAccessTokenService;
import com.cloud.weixin.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wx")
@AllArgsConstructor
public class WxAccessTokenController {
    private final WxAccessTokenService wxAccessTokenService;
    @GetMapping("access-token")
    public Result<String> getToken() {
        return wxAccessTokenService.getAccessToken();
    }

}
