package com.pconil.reactor3;

/**
 * Created by patrice on 19/05/2017.
 */
public class Program {
    private String externalId;
    private String channelId;

    public Program() {
    }

    public Program(String externalId, String channelId) {
        this.externalId = externalId;
        this.channelId = channelId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
