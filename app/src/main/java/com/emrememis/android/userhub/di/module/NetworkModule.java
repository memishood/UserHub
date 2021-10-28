package com.emrememis.android.userhub.di.module;

import com.emrememis.android.userhub.data.service.GithubService;
import com.emrememis.android.userhub.di.scope.FragmentScope;
import com.emrememis.android.userhub.util.Utils;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @FragmentScope
    @Provides
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @FragmentScope
    @Provides
    public static GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
