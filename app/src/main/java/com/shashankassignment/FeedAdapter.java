package com.shashankassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashankassignment.databinding.ItemFeedBinding;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private final List<Message> mList;
    private Context mContext;

    public FeedAdapter(Context context, List<Message> msgList) {
        mContext = context;
        mList = msgList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_feed, parent, false);
        return new FeedViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Message message = mList.get(position);
        if (message.getUserInfo().getProfilePic() != null) {
            Glide.with(mContext).load(message.getUserInfo().getProfilePic()).into(holder.mBinding.ivUser);
        } else {
            Glide.with(mContext).load(R.drawable.ic_bg).into(holder.mBinding.ivUser);
        }
        if (message.getThum() != null) {
            Glide.with(mContext).load(message.getThum()).into(holder.mBinding.ivThumbnail);
        } else {
            Glide.with(mContext).load(R.drawable.ic_bg).into(holder.mBinding.ivThumbnail);
        }
        if (!message.getDescription().isEmpty()) {
            holder.mBinding.tvDescription.setVisibility(View.VISIBLE);
            holder.mBinding.tvDescription.setText(message.getDescription());
        } else {
            holder.mBinding.tvDescription.setVisibility(View.INVISIBLE);
        }
        holder.mBinding.tvUserName.setText(String.format("%s %s", message.getUserInfo().getFirstName(), mList.get(position).getUserInfo().getLastName()));
        holder.mBinding.tvCreatedDate.setText(message.getCreated());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        private final ItemFeedBinding mBinding;

        public FeedViewHolder(@NonNull ItemFeedBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
