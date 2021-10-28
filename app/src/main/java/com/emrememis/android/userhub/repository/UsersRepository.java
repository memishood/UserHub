package com.emrememis.android.userhub.repository;

import com.emrememis.android.userhub.data.model.Search;
import com.emrememis.android.userhub.data.model.User;
import com.emrememis.android.userhub.data.service.GithubService;
import com.emrememis.android.userhub.di.CacheDatabase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UsersRepository {
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public UsersRepository() { }

    @Inject
    GithubService service;

    @Inject
    CacheDatabase cacheDatabase;

    public void getUsers(DisposableSingleObserver<List<User>> observer) {
        disposable.clear();
        disposable.add(
                service.getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(this::mapUsers)
                    .subscribeWith(observer)
        );
    }

    public List<User> mapUsers(List<User> users) {
        for (User user : users) {
            user.setCached(cacheDatabase.isCached(user));
        }
        return users;
    }

    public void searchUsers(String text, DisposableSingleObserver<Search> observer) {
        disposable.clear();
        disposable.add(
                service.searchUsers(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(this::mapSearchedUsers)
                    .subscribeWith(observer)
        );
    }

    public Search mapSearchedUsers(Search result) {
        for (User user : result.users) {
            user.setCached(cacheDatabase.isCached(user));
        }
        return result;
    }

    public void clear() { disposable.clear(); }
}