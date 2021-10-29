package com.emrememis.android.userhub.repository;

import com.emrememis.android.userhub.data.model.UserDetail;
import com.emrememis.android.userhub.data.service.GithubService;
import com.emrememis.android.userhub.di.CacheDatabase;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserDetailRepository {
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public UserDetailRepository() { }

    @Inject
    GithubService service;

    @Inject
    CacheDatabase cacheDatabase;

    public void fetchUserFromRemote(String login, DisposableSingleObserver<UserDetail> observer) {
        disposable.clear();
        disposable.add(
                service.getUser(login)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(user -> cacheDatabase.insert(user))
                        .subscribeWith(observer)
        );
    }
    public boolean isCached(String login) {
        return cacheDatabase.isCached(login);
    }

    public UserDetail fetchUserFromCache(String login) { return cacheDatabase.getUser(login); }

    public void clear() {
        disposable.clear();
    }
}