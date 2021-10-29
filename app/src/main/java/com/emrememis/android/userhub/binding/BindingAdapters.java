package com.emrememis.android.userhub.binding;

import android.annotation.SuppressLint;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.emrememis.android.userhub.R;
import com.google.android.material.textview.MaterialTextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class BindingAdapters {
    @BindingAdapter("android:setImage")
    public static void setImage(AppCompatImageView appCompatImageView, String url) {
        Glide.with(appCompatImageView.getContext())
                .load(url)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(appCompatImageView);
    }

    @BindingAdapter("android:formatDate")
    public static void formatDate(MaterialTextView textView, String date) {
        final Date createdAt = toDate(date);
        if (createdAt != null) {
            textView.setText(new SimpleDateFormat("MM-dd-yyyy").format(createdAt));
        }
    }

    private static Date toDate(String source) {
        if (source == null) {
            return null;
        }
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}