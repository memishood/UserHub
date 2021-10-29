package com.emrememis.android.userhub.view.user_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.emrememis.android.userhub.databinding.FragmentUserDetailBinding;
import com.emrememis.android.userhub.di.component.DaggerFragmentComponent;
import com.emrememis.android.userhub.viewmodel.UserDetailViewModel;
import javax.inject.Inject;

public class UserDetailFragment extends Fragment {
    private static final String TAG = "UserDetailFragment";
    private FragmentUserDetailBinding binding;

    @Inject
    UserDetailViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentUserDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerFragmentComponent.builder()
                .context(view.getContext())
                .build()
                .inject(this);
        final String login = UserDetailFragmentArgs.fromBundle(getArguments()).getLogin();
        viewModel.userData.observe(getViewLifecycleOwner(), binding::setUser);
        viewModel.loadingData.observe(getViewLifecycleOwner(), binding::setLoading);
        viewModel.errorData.observe(getViewLifecycleOwner(), error -> Log.e(TAG, "User error: ", error));
        // show cached status message
        viewModel.cachedInfo.observe(getViewLifecycleOwner(), cached ->
            Toast.makeText(
                    view.getContext(),
                    cached ? "fetched from cache" : "fetching from network..",
                    Toast.LENGTH_SHORT
            ).show()
        );
        viewModel.getUser(login);
    }
}