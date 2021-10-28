package com.emrememis.android.userhub.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Search {
    @SerializedName("items")
    public List<User> users;
}