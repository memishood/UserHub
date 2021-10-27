package com.emrememis.android.userhub.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.emrememis.android.userhub.databinding.FragmentUserDetailBinding;

public class UserDetailFragment extends Fragment {
    private FragmentUserDetailBinding binding;
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
}
