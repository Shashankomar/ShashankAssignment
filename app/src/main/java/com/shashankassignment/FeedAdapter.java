package com.shashankassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashankassignment.databinding.ItemFeedBinding;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private final List<Message> mMsgList;
    private Context mContext;

    public FeedAdapter(Context context, List<Message> msgList) {
        mContext = context;
        mMsgList = msgList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_feed, parent, false);
        return new FeedViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Glide.with(mContext).load(mMsgList.get(position).getUserInfo().getProfilePic()).into(holder.mBinding.ivUser);
        Glide.with(mContext).load(mMsgList.get(position).getThum()).into(holder.mBinding.ivThumbnail);
        holder.mBinding.tvCreatedDate.setText(mMsgList.get(position).getCreated());
        holder.mBinding.tvDescription.setText(mMsgList.get(position).getDescription());
        holder.mBinding.tvUserName.setText(String.format("%s %s", mMsgList.get(position).getUserInfo().getFirstName(), mMsgList.get(position).getUserInfo().getLastName()));
        holder.mBinding.tvCreatedDate.setText(mMsgList.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        private final ItemFeedBinding mBinding;

        public FeedViewHolder(@NonNull ItemFeedBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }
    }
}
