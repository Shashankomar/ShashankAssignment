package com.shashankassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Count {

    @SerializedName("like_count")
    @Expose
    private String likeCount;

    @SerializedName("video_comment_count")
    @Expose
    private String videoCommentCount;

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getVideoCommentCount() {
        return videoCommentCount;
    }

    public void setVideoCommentCount(String videoCommentCount) {
        this.videoCommentCount = videoCommentCount;
    }

}
