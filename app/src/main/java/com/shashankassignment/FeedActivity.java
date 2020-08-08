package com.shashankassignment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonObject;
import com.shashankassignment.databinding.ActivityFeedBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedActivity extends AppCompatActivity {

    private ActivityFeedBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_feed);

        setToolbar();
        Utils.showProgressDialog(this);
        fetchDataFromAPI();
    }

    private void setToolbar() {
        mBind.includedToolbar.tvTitle.setText(getString(R.string.feed));
        mBind.includedToolbar.rlToolbarContainer.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_bg));
    }

    private void fetchDataFromAPI() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        JsonObject params = new JsonObject();
        params.addProperty(Constants.FB_ID, "0");
        params.addProperty(Constants.TOKEN, "token");

        Call<FeedDataModel> call = apiInterface.getFeedData(params);
        call.enqueue(new Callback<FeedDataModel>() {
            @Override
            public void onResponse(@NonNull Call<FeedDataModel> call, @NonNull Response<FeedDataModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode().equals(Constants.SUCCESS_CODE)) {
                        setRecyclerView(response.body().getMsg());
                    }
                }
                Utils.hideProgressDialog();
            }

            @Override
            public void onFailure(@NonNull Call<FeedDataModel> call, @NonNull Throwable t) {
                Utils.hideProgressDialog();
                Toast.makeText(FeedActivity.this, R.string.seems_glitch, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(List<Message> list) {
        FeedAdapter feedAdapter = new FeedAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBind.rvFeed.setLayoutManager(layoutManager);
        mBind.rvFeed.setAdapter(feedAdapter);
    }
}