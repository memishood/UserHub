package com.emrememis.android.userhub.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.emrememis.android.userhub.databinding.FragmentUsersBinding;

public class UsersFragment extends Fragment {
    private FragmentUsersBinding binding;
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
}
