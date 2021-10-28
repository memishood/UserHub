package com.emrememis.android.userhub.data.model;

public class User {
    public String login, avatar_url;
    public User(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }
    private boolean cached;
    public boolean getCached() { return this.cached; }
    public void setCached(boolean cached) { this.cached = cached; }
}