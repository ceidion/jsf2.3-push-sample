package com.iioannou.push.sample;

import java.io.Serializable;

/**
 * @author iioannou
 */
public class PushMessage implements Serializable {

    private String payload;
    private String user;

    public PushMessage(String payload, String user) {
        super();
        this.payload = payload;
        this.user = user;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
