package com.emrememis.android.userhub.view.users;

import androidx.recyclerview.widget.DiffUtil;
import com.emrememis.android.userhub.data.model.User;
import java.util.ArrayList;

class DiffCallback extends DiffUtil.Callback {
    private final ArrayList<User> oldUsers, newUsers;
    public DiffCallback(ArrayList<User> oldUsers, ArrayList<User> newUsers) {
        this.oldUsers = oldUsers;
        this.newUsers = newUsers;
    }
    @Override
    public int getOldListSize() {
        return oldUsers.size();
    }
    @Override
    public int getNewListSize() {
        return newUsers.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).login.equals(newUsers.get(newItemPosition).login);
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
    }
}