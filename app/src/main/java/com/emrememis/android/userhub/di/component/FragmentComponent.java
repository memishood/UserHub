package com.emrememis.android.userhub.di.component;

import android.content.Context;
import com.emrememis.android.userhub.di.module.DatabaseModule;
import com.emrememis.android.userhub.di.module.NetworkModule;
import com.emrememis.android.userhub.di.scope.FragmentScope;
import com.emrememis.android.userhub.view.user_detail.UserDetailFragment;
import com.emrememis.android.userhub.view.users.UsersFragment;

import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface FragmentComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        FragmentComponent build();
    }
    void inject(UsersFragment usersFragment);
    void inject(UserDetailFragment userDetailFragment);
}
