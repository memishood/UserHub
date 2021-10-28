package com.emrememis.android.userhub.view.users;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.emrememis.android.userhub.data.model.User;
import com.emrememis.android.userhub.databinding.ItemUserRowBinding;
import java.util.ArrayList;
import javax.inject.Inject;

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {
    @Inject
    public UsersAdapter() { }
    private final ArrayList<User> users = new ArrayList<>();
    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserRowBinding binding = ItemUserRowBinding.inflate(inflater, parent, false);
        return new UsersHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        holder.bind(users.get(position));
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
    public void update(ArrayList<User> users) {
        final DiffCallback diffCallback = new DiffCallback(this.users, users);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, false);
        this.users.clear();
        this.users.addAll(users);
        result.dispatchUpdatesTo(UsersAdapter.this);
    }
    protected static class UsersHolder extends RecyclerView.ViewHolder {
        private final ItemUserRowBinding binding;
        public UsersHolder(ItemUserRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(User user) {
            binding.setUser(user);
            binding.getRoot().setOnClickListener(v -> {
                final UsersFragmentDirections.ActionUsersFragmentToUserDetailFragment action
                        = UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(user.login);
                Navigation.findNavController(v).navigate(action);
            });
        }
    }
}