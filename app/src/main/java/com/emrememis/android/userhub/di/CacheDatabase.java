package com.emrememis.android.userhub.di;

import android.content.SharedPreferences;

import com.emrememis.android.userhub.data.model.UserDetail;
import com.emrememis.android.userhub.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CacheDatabase {
    @Inject
    public CacheDatabase() { }

    @Inject
    SharedPreferences sharedPreferences;

    public List<UserDetail> getUsers() {
        final String data = sharedPreferences.getString(Utils.CACHED_DB_NAME, null);
        final Gson gson = new Gson();
        final List<UserDetail> usersData = gson.fromJson(data, new TypeToken<List<UserDetail>>() {}.getType());
        return usersData != null ? usersData : new ArrayList<>();
    }

    public UserDetail getUser(String login) {
        final List<UserDetail> users = getUsers();
        for (UserDetail user : users) {
            if (login.equals(user.login)) {
                return user;
            }
        }
        return null;
    }

    public boolean isCached(String login) {
        final List<UserDetail> cachedUsers = getUsers();
        for (UserDetail cachedUser : cachedUsers) {
            if (login.equals(cachedUser.login)) {
                return true;
            }
        }
        return false;
    }

    public void insert(UserDetail user) {
        final List<UserDetail> cachedUsers = getUsers();
        if (!isCached(user.login)) {
            cachedUsers.add(user);
            final Gson gson = new Gson();
            final String usersText = gson.toJson(cachedUsers);
            sharedPreferences.edit().putString(Utils.CACHED_DB_NAME, usersText).apply();
        }
    }
}