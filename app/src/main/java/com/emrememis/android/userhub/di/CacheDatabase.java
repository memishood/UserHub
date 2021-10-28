package com.emrememis.android.userhub.di;

import android.content.SharedPreferences;
import com.emrememis.android.userhub.data.model.User;
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

    public List<User> getCachedUsers() {
        final String data = sharedPreferences.getString(Utils.CACHED_DB_NAME, null);
        final Gson gson = new Gson();
        final List<User> usersData = gson.fromJson(data, new TypeToken<List<User>>() {}.getType());
        return usersData != null ? usersData : new ArrayList<>();
    }

    public boolean isCached(User user) {
        final List<User> cachedUsers = getCachedUsers();
        for (User cachedUser : cachedUsers) {
            if (user.login.equals(cachedUser.login)) {
                return true;
            }
        }
        return false;
    }

    public void insertCache(User user) {
        final List<User> cachedUsers = getCachedUsers();
        if (!isCached(user)) {
            cachedUsers.add(user);
            final Gson gson = new Gson();
            final String usersText = gson.toJson(cachedUsers);
            sharedPreferences.edit().putString(Utils.CACHED_DB_NAME, usersText).apply();
        }
    }
}