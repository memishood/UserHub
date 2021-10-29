package com.emrememis.android.userhub.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.emrememis.android.userhub.data.model.UserDetail;
import com.emrememis.android.userhub.repository.UserDetailRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class UserDetailViewModel extends ViewModel {
    public final MutableLiveData<UserDetail> userData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loadingData = new MutableLiveData<>();
    public final MutableLiveData<Throwable> errorData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> cachedInfo = new MutableLiveData<>();

    @Inject
    public UserDetailViewModel() { }

    @Inject
    UserDetailRepository repository;

    public void getUser(String login) {
        if (repository.isCached(login)) {
            UserDetail user = repository.fetchUserFromCache(login);
            userData.setValue(user);
        } else {
            loadingData.setValue(true);
            repository.fetchUserFromRemote(login, getUserObserver());
        }
        cachedInfo.setValue(repository.isCached(login));
    }

    private DisposableSingleObserver<UserDetail> getUserObserver() {
        return new DisposableSingleObserver<UserDetail>() {
            @Override
            public void onSuccess(@NonNull UserDetail user) {
                userData.setValue(user);
                loadingData.setValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                errorData.setValue(e);
                loadingData.setValue(false);
            }
        };
    }

    @Override
    protected void onCleared() {
        repository.clear();
        super.onCleared();
    }
}