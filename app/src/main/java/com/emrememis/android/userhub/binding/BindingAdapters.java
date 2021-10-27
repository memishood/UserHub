package com.emrememis.android.userhub.binding;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.emrememis.android.userhub.R;

public class BindingAdapters {
    @BindingAdapter("android:setImage")
    public static void setImage(AppCompatImageView appCompatImageView, String url) {
        Glide.with(appCompatImageView.getContext())
                .load(url)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(appCompatImageView);
    }
}