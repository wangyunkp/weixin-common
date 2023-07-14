package com.cloud.weixin.bean;

import com.cloud.weixin.bean.oauth2.WxBaseResult;
import com.cloud.weixin.util.WxGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * access token.
 */
@Data
public class WxAccessToken extends WxBaseResult implements Serializable {
    private static final long serialVersionUID = 8709719312922168909L;
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private int expiresIn = -1;

    public static WxAccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxAccessToken.class);
    }

}
