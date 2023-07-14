package com.cloud.weixin.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.weixin.api.WxAccessTokenService;
import com.cloud.weixin.bean.WxAccessToken;
import com.cloud.weixin.config.WxConfig;
import com.cloud.weixin.error.WxErrorException;
import com.cloud.weixin.nacos.WxNacosConfig;
import com.cloud.weixin.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WxAccessTokenServiceImpl implements WxAccessTokenService {
    private final WxConfig wxConfig;
    private final WxNacosConfig wxNacosConfig;
    private final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


    @Override
    public Result<String> getAccessToken() {
        return getAccessToken(false);
    }

    @Override
    public Result<String> getAccessToken(boolean forceRefresh) {
        /*
            如果不是强制刷新且accessToken没有过期那么直接返回当前accessToken
         */
        if (!forceRefresh && !wxConfig.isAccessTokenExpired()) {
            return Result.ofSuccess(wxConfig.getAccessToken());
        }
        synchronized (this) {
            if (!forceRefresh && !wxConfig.isAccessTokenExpired()) {
                return Result.ofSuccess(wxConfig.getAccessToken());
            }
            String resultContent = doGetAccessToken();
            try {
                return Result.ofSuccess(extractAccessToken(resultContent));
            } catch (WxErrorException e) {
                return Result.ofFail(e.getErrorCode(), e.getErrorMsg());
            }
        }
    }

    private String doGetAccessToken() {
        String url = String.format(GET_ACCESS_TOKEN_URL, wxNacosConfig.getAppid(), wxNacosConfig.getSecret());
        return HttpUtil.get(url, wxNacosConfig.getTimeout());

    }

    /**
     * 设置当前的AccessToken
     *
     * @param resultContent 响应内容
     * @return access token
     * @throws WxErrorException 异常
     */
    private String extractAccessToken(String resultContent) throws WxErrorException {
        log.info("resultContent: " + resultContent);
        WxAccessToken wxAccessToken = WxAccessToken.fromJson(resultContent);
        if (ObjectUtil.isNotNull(wxAccessToken.getErrCode())) {
            log.error("获取微信access_token失败:errCode:{},errMsg:{}", wxAccessToken.getErrCode(), wxAccessToken.getErrMsg());
            throw new WxErrorException(wxAccessToken.getErrCode(), wxAccessToken.getErrMsg());
        }
        wxConfig.updateAccessToken(wxAccessToken.getAccessToken(), wxAccessToken.getExpiresIn());
        return wxAccessToken.getAccessToken();
    }

}
