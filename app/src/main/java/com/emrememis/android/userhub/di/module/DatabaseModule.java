package com.emrememis.android.userhub.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.emrememis.android.userhub.di.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @FragmentScope
    @Provides
    public static SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName() + "-caching", Context.MODE_PRIVATE);
    }
}
