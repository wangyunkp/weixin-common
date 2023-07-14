package com.cloud.weixin.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
@Data
public class WxDefaultConfigImpl implements WxConfig {
    //过期时间
    private volatile long expiresTime;
    private volatile String accessToken;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public boolean isAccessTokenExpired() {
        return isExpired(expiresTime);
    }


    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        setAccessToken(accessToken);
        setExpiresTime(expiresAheadInMillis(expiresInSeconds));
    }


    /**
     * 会过期的数据提前过期时间，默认预留200秒的时间
     */
    protected long expiresAheadInMillis(int expiresInSeconds) {
        return System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    /**
     * 判断 expiresTime 是否已经过期
     */
    protected boolean isExpired(long expiresTime) {
        return System.currentTimeMillis() > expiresTime;
    }
}
