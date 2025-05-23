package com.example.videoshortapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("MMM d, yyyy")
            .create();

    APIService serviceapi = new Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("poudyalanil/ca84582cbeb4fc123a13290a586da925/raw")
    Call<List<VideoModel>> getVideos();
}