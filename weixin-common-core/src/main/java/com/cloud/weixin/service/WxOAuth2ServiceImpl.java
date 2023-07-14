package com.cloud.weixin.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.weixin.api.WxOAuth2Service;
import com.cloud.weixin.bean.oauth2.WxBaseResult;
import com.cloud.weixin.bean.oauth2.WxOAuth2AccessToken;
import com.cloud.weixin.bean.oauth2.WxOAuth2UserInfo;
import com.cloud.weixin.enums.OAuth2;
import com.cloud.weixin.nacos.WxNacosConfig;
import com.cloud.weixin.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Service
@AllArgsConstructor
public class WxOAuth2ServiceImpl implements WxOAuth2Service {
    private final WxNacosConfig wxNacosConfig;


    @Override
    public Result<String> getAccessToken(@NotBlank(message = "获取token的Code必填") String code) {
        String url = String.format(OAuth2.OAUTH2_ACCESS_TOKEN_URL.getPath(), wxNacosConfig.getAppid(), wxNacosConfig.getSecret(), code);
        String body = HttpUtil.get(url, wxNacosConfig.getTimeout());
        WxOAuth2AccessToken wxOAuth2AccessToken = WxOAuth2AccessToken.fromJson(body);
        if (ObjectUtil.isNull(wxOAuth2AccessToken.getErrCode())) {
            return Result.ofSuccess(wxOAuth2AccessToken.getAccessToken());
        }
        return Result.ofFail(wxOAuth2AccessToken.getErrCode(), wxOAuth2AccessToken.getErrMsg());
    }

    @Override
    public Result<String> refreshAccessToken(@NotBlank(message = "refreshToken必填") String refreshToken) {
        String url = String.format(OAuth2.OAUTH2_REFRESH_TOKEN_URL.getPath(), wxNacosConfig.getAppid(), refreshToken);
        String body = HttpUtil.get(url, wxNacosConfig.getTimeout());
        WxOAuth2AccessToken wxOAuth2AccessToken = WxOAuth2AccessToken.fromJson(body);
        if (ObjectUtil.isNull(wxOAuth2AccessToken.getErrCode())) {
            return Result.ofSuccess(wxOAuth2AccessToken.getAccessToken());
        }
        return Result.ofFail(wxOAuth2AccessToken.getErrCode(), wxOAuth2AccessToken.getErrMsg());
    }

    @Override
    public Result<WxOAuth2UserInfo> getUserInfo(@NotBlank(message = "accessToken必填") String accessToken,
                                                String lang) {
        if (StrUtil.isBlank(lang)) {
            lang = "zh_CN";
        }
        String url = String.format(OAuth2.OAUTH2_USERINFO_URL.getPath(), accessToken, wxNacosConfig.getAppid(), lang);
        String body = HttpUtil.get(url, wxNacosConfig.getTimeout());
        WxOAuth2UserInfo wxOAuth2UserInfo = WxOAuth2UserInfo.fromJson(body);
        if (ObjectUtil.isNull(wxOAuth2UserInfo.getErrCode())) {
            return Result.ofSuccess(wxOAuth2UserInfo);
        }
        return Result.ofFail(wxOAuth2UserInfo.getErrCode(), wxOAuth2UserInfo.getErrMsg());
    }

    @Override
    public Result<Boolean> validateAccessToken(@NotBlank(message = "accessToken必填") String accessToken) {
        String url = String.format(OAuth2.OAUTH2_VALIDATE_TOKEN_URL.getPath(), accessToken, wxNacosConfig.getAppid());
        String body = HttpUtil.get(url, wxNacosConfig.getTimeout());
        WxBaseResult wxBaseResult = WxBaseResult.fromJson(body);
        if (wxBaseResult.getErrCode().equals(0)) {
            return Result.ofSuccess(true);
        }
        return Result.ofFail(wxBaseResult.getErrCode(), wxBaseResult.getErrMsg());

    }
}
