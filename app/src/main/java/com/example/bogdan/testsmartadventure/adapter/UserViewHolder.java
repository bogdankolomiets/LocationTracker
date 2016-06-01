package com.example.bogdan.testsmartadventure.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bogdan.testsmartadventure.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 31.05.16
 */
public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView mNickName;
    public TextView mEmail;

    public UserViewHolder(View itemView) {
        super(itemView);
        mNickName = (TextView) itemView.findViewById(R.id.txtNickName);
        mEmail = (TextView) itemView.findViewById(R.id.txtEmail);
    }
}
