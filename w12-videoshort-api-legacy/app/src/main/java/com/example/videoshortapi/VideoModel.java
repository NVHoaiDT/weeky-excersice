package com.example.videoshortapi;

import java.io.Serializable;
import java.util.List;

public class VideoModel {
    private String id;
    private String title;
    private String thumbnailUrl;
    private String duration;
    private String uploadTime;
    private String views;
    private String author;
    private String videoUrl;
    private String description;
    private String subscriber;
    private boolean isLive;

    // Các getter và setter
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public String getDuration() { return duration; }
    public String getUploadTime() { return uploadTime; }
    public String getViews() { return views; }
    public String getAuthor() { return author; }
    public String getVideoUrl() { return videoUrl; }
    public String getDescription() { return description; }
    public String getSubscriber() { return subscriber; }
    public boolean isLive() { return isLive; }
}