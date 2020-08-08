package com.shashankassignment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        fetchDataFromAPI();
    }

    private void fetchDataFromAPI() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        JsonObject params = new JsonObject();
        params.addProperty("fb_id", "0");
        params.addProperty("token", "abc");

        Call<FeedDataModel> call = apiInterface.getFeedData(params);

        call.enqueue(new Callback<FeedDataModel>() {
            @Override
            public void onResponse(@NonNull Call<FeedDataModel> call, @NonNull Response<FeedDataModel> response) {

                if (response.body() != null) {
                    Toast.makeText(FeedActivity.this, response.body().getCode(), Toast.LENGTH_SHORT).show();
                    if (response.body().getCode().equals("200")) {
                        setRecyclerView(response.body().getMsg());

                        /*String date="Mar 10, 2016 6:30:00 PM";
                        SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
                        Date newDate=spf.parse(date);
                        spf= new SimpleDateFormat("dd MMM yyyy");
                        date = spf.format(newDate);
                        System.out.println(date);
                    */
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedDataModel> call, @NonNull Throwable t) {
                Toast.makeText(FeedActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(List<Message> msg) {
        FeedAdapter feedAdapter = new FeedAdapter(this, msg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBind.rvFeed.setLayoutManager(layoutManager);
        mBind.rvFeed.setAdapter(feedAdapter);

    }
}