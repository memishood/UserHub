package com.emrememis.android.userhub.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public String login;
    public int id;
    @SerializedName("node_id")
    public String nodeId;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("gravatar_id")
    public String gravatarId;
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("followers_url")
    public String followersUrl;
    @SerializedName("following_url")
    public String followingUrl;
    @SerializedName("gists_url")
    public String gistsUrl;
    @SerializedName("starred_url")
    public String starredUrl;
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    public String organizationsUrl;
    @SerializedName("repos_url")
    public String reposUrl;
    @SerializedName("events_url")
    public String eventsUrl;
    @SerializedName("received_events_url")
    public String receivedEventsUrl;
    public String type;
    @SerializedName("site_admin")
    public boolean siteAdmin;

    private boolean cached;
    public boolean getCached() { return this.cached; }
    public void setCached(boolean cached) { this.cached = cached; }
}