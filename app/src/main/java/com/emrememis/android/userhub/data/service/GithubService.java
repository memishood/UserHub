package com.emrememis.android.userhub.data.service;

import com.emrememis.android.userhub.data.model.Search;
import com.emrememis.android.userhub.data.model.User;
import java.util.List;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("users")
    Single<List<User>> getUsers();

    @GET("users/{user}")
    Single<User> getUser(@Path("user") String user);

    @GET("search/users")
    Single<Search> searchUsers(@Query("q") String username);
}