package com.emrememis.android.userhub.viewmodel;

import android.text.Editable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.emrememis.android.userhub.data.model.Search;
import com.emrememis.android.userhub.data.model.User;
import com.emrememis.android.userhub.repository.UsersRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class UsersViewModel extends ViewModel {
    public final MutableLiveData<List<User>> usersData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loadingData = new MutableLiveData<>();
    public final MutableLiveData<Throwable> errorData = new MutableLiveData<>();

    @Inject
    public UsersViewModel() { }

    @Inject
    UsersRepository repository;

    public void getUsers() {
        loadingData.setValue(true);
        repository.getUsers(getUsersObserver());
    }

    private DisposableSingleObserver<List<User>> getUsersObserver() {
        return new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(@NonNull List<User> users) {
                usersData.setValue(users);
                loadingData.setValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                loadingData.setValue(false);
                errorData.setValue(e);
            }
        };
    }

    public void searchUsers(@Nullable  Editable search) {
        if (search != null && !search.toString().trim().isEmpty()) {
            loadingData.setValue(true);
            repository.searchUsers(search.toString().trim(), getSearchObserver());
        }
    }

    private DisposableSingleObserver<Search> getSearchObserver() {
        return new DisposableSingleObserver<Search>() {
            @Override
            public void onSuccess(@NonNull Search search) {
                usersData.setValue(search.users);
                loadingData.setValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                loadingData.setValue(false);
                errorData.setValue(e);
            }
        };
    }

    @Override
    protected void onCleared() {
        repository.clear();
        super.onCleared();
    }
}