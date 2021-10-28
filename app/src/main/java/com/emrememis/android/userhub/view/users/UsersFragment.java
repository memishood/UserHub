package com.emrememis.android.userhub.view.users;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.emrememis.android.userhub.data.model.User;
import com.emrememis.android.userhub.databinding.FragmentUsersBinding;
import com.emrememis.android.userhub.di.component.DaggerFragmentComponent;
import com.emrememis.android.userhub.viewmodel.UsersViewModel;
import java.util.ArrayList;
import javax.inject.Inject;

public class UsersFragment extends Fragment {
    private static final String TAG = "UsersFragment";
    private FragmentUsersBinding binding;

    @Inject
    UsersViewModel viewModel;

    @Inject
    UsersAdapter usersAdapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentUsersBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // bind dependency injection
        DaggerFragmentComponent.builder()
                .context(view.getContext())
                .build()
                .inject(this);
        binding.fragmentUsersRecyclerView.setAdapter(usersAdapter);
        binding.fragmentUsersSearchInputLayout.setEndIconOnClickListener(v ->
                viewModel.searchUsers(binding.fragmentUsersSearchEditText.getText()));
        // fetch and observe data
        viewModel.usersData.observe(getViewLifecycleOwner(), users -> usersAdapter.update((ArrayList<User>) users));
        viewModel.loadingData.observe(getViewLifecycleOwner(), binding::setLoading);
        viewModel.errorData.observe(getViewLifecycleOwner(), error -> Log.e(TAG, "Users error", error));
        viewModel.getUsers();
    }
}