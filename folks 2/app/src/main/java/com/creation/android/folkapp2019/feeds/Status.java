package com.creation.android.folkapp2019.feeds;

public class Status {
    private String statusImageUrl;
    private String statusTitle;

    public Status() {
    }

    public Status(String statusImageUrl, String statusTitle) {
        this.statusImageUrl = statusImageUrl;
        this.statusTitle = statusTitle;
    }

    public String getStatusImageUrl() {
        return statusImageUrl;
    }

    public void setStatusImageUrl(String statusImageUrl) {
        this.statusImageUrl = statusImageUrl;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }
}
