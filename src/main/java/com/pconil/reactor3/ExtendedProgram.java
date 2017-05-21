package com.pconil.reactor3;

/**
 * Created by patrice on 19/05/2017.
 */
public class ExtendedProgram {
    private String externalId;
    private String channelId;
    private String summary;

    public ExtendedProgram() {
    }

    public ExtendedProgram(String externalId, String channelId, String summary) {
        this.externalId = externalId;
        this.channelId = channelId;
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtendedProgram that = (ExtendedProgram) o;

        if (externalId != null ? !externalId.equals(that.externalId) : that.externalId != null) return false;
        if (channelId != null ? !channelId.equals(that.channelId) : that.channelId != null) return false;
        return summary != null ? summary.equals(that.summary) : that.summary == null;
    }

    @Override
    public int hashCode() {
        int result = externalId != null ? externalId.hashCode() : 0;
        result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        return result;
    }
}
