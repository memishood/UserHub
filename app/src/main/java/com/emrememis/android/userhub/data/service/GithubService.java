package com.emrememis.android.userhub.data.service;

import com.emrememis.android.userhub.data.model.User;
import java.util.List;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users")
    Single<List<User>> getUsers();

    @GET("users/{user}")
    Single<User> getUser(@Path("user") String user);
}