package com.example.video_iotstar;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private VideosAdapter videoAdapter;
    private List<VideoModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        list = new ArrayList<>();

        getVideos();
    }

    private void getVideos() {
        ApiService.apiService.getVideos().enqueue(new Callback<MessageVideoModel>() {
            @Override
            public void onResponse(Call<MessageVideoModel> call, Response<MessageVideoModel> response) {
                list = response.body().getResult();
                videoAdapter = new VideosAdapter(MainActivity.this, list);
                viewPager.setAdapter(videoAdapter);
                viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
            }

            @Override
            public void onFailure(Call<MessageVideoModel> call, Throwable t) {
                Log.d("TAG", "getMessage: " + t.getMessage());
            }
        });
    }
}