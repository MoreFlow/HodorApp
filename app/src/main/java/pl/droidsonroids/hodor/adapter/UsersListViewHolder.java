package pl.droidsonroids.hodor.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.hodor.HodorApplication;
import pl.droidsonroids.hodor.HodorPreferences;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.util.DatabaseHelper;

public class UsersListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_username) TextView mTextViewUsername;

    private DatabaseHelper mDatabaseHelper;
    final View itemView;

    public UsersListViewHolder(final View itemView, final DatabaseHelper databaseHelper) {
        super(itemView);
        this.itemView = itemView;
        mDatabaseHelper = databaseHelper;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final String username, final int backgroundColorRes) {
        mTextViewUsername.setText(username);
        mTextViewUsername.setBackgroundColor(ContextCompat.getColor(mTextViewUsername.getContext(),
                backgroundColorRes));

        mDatabaseHelper.getUserFromDatabase(username, friend -> {
            mTextViewUsername.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Sending to " + friend.getUsername(), Toast.LENGTH_SHORT).show();
                HodorApplication.getInstance().getRestAdapter().sendPush(friend.getToken(), new HodorPreferences(itemView.getContext()).getUsername());
            });
        });
    }

}
