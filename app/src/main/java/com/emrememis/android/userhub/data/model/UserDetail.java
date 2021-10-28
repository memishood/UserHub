package com.emrememis.android.userhub.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class UserDetail {
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
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public Object hireable;
    public String bio;
    @SerializedName("twitter_username")
    public String twitterUsername;
    @SerializedName("public_repos")
    public int publicRepos;
    @SerializedName("public_gists")
    public int publicGists;
    public int followers;
    public int following;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}

