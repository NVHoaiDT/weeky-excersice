package com.example.viewpager_cricleindicator;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Images> imagesList1;


    // autorun
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }

            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        imagesList1 = getListImages();
        ImagesViewPager2Adapter adapter = new ImagesViewPager2Adapter(imagesList1);
        viewPager2.setAdapter(adapter);

        circleIndicator3.setViewPager(viewPager2);
    }

    private List<Images> getListImages() {
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.image1));
        list.add(new Images(R.drawable.image2));
        list.add(new Images(R.drawable.image3));
        list.add(new Images(R.drawable.image4));
        return list;
    }
}
