package com.cloud.weixin.bean.oauth2;

import com.cloud.weixin.util.WxGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WxBaseResult {
    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    public static WxBaseResult fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxBaseResult.class);
    }
}
