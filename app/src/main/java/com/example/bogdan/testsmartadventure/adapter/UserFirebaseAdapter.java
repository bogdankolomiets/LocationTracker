package com.example.bogdan.testsmartadventure.adapter;

import com.example.bogdan.testsmartadventure.R;
import com.example.bogdan.testsmartadventure.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 31.05.16
 */
public class UserFirebaseAdapter extends FirebaseRecyclerAdapter<User, UserViewHolder> {

    public UserFirebaseAdapter(Query query) {
        super(User.class, R.layout.row_user, UserViewHolder.class, query);
    }

    @Override
    protected void populateViewHolder(UserViewHolder viewHolder, User model, int position) {
        viewHolder.mNickName.setText(model.getName());
        viewHolder.mEmail.setText(model.getEmail());
    }

}
