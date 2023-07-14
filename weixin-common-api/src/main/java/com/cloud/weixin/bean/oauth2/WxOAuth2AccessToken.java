package com.cloud.weixin.bean.oauth2;

import com.cloud.weixin.util.WxGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
 **/
@Data
public class WxOAuth2AccessToken extends WxBaseResult implements Serializable {
    private static final long serialVersionUID = -1345910558078620805L;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private int expiresIn = -1;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("openid")
    private String openId;

    @SerializedName("scope")
    private String scope;

    @SerializedName("is_snapshotuser")
    private Integer snapshotUser;



    @SerializedName("unionid")
    private String unionId;

    public static WxOAuth2AccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxOAuth2AccessToken.class);
    }

    @Override
    public String toString() {
        return WxGsonBuilder.create().toJson(this);
    }
}
