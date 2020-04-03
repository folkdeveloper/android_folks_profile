package com.creation.android.folkapp2019.feeds;

public class Feed {

    public static final int TYPE_STATUS = 0;
    public static final int TYPE_FEED = 1;
    private String channelImageUrl;
    private String channelName;
    private String feedTimeStamp;
    private String feedText;
    private String feedImageUrl;
    private String likeCount;
    private Comments[] comments;
    private int feedType;
    private String documentId;

    public Feed() {
    }

    public Feed(String channelImageUrl, String channelName, String feedTimeStamp, String feedText, String feedImageUrl, String likeCount, Comments[] comments, int feedType, String documentId) {
        this.channelImageUrl = channelImageUrl;
        this.channelName = channelName;
        this.feedTimeStamp = feedTimeStamp;
        this.feedText = feedText;
        this.feedImageUrl = feedImageUrl;
        this.likeCount = likeCount;
        this.comments = comments;
        this.feedType = feedType;
        this.documentId = documentId;
    }

    public static int getTypeStatus() {
        return TYPE_STATUS;
    }

    public static int getTypeFeed() {
        return TYPE_FEED;
    }

    public String getChannelImageUrl() {
        return channelImageUrl;
    }

    public void setChannelImageUrl(String channelImageUrl) {
        this.channelImageUrl = channelImageUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getFeedTimeStamp() {
        return feedTimeStamp;
    }

    public void setFeedTimeStamp(String feedTimeStamp) {
        this.feedTimeStamp = feedTimeStamp;
    }

    public String getFeedText() {
        return feedText;
    }

    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    public String getFeedImageUrl() {
        return feedImageUrl;
    }

    public void setFeedImageUrl(String feedImageUrl) {
        this.feedImageUrl = feedImageUrl;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public Comments[] getComments() {
        return comments;
    }

    public void setComments(Comments[] comments) {
        this.comments = comments;
    }

    public int getFeedType() {
        return feedType;
    }

    public void setFeedType(int feedType) {
        this.feedType = feedType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
