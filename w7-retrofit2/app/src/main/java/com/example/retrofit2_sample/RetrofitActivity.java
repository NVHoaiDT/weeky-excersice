package com.example.retrofit2_sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitActivity extends AppCompatActivity {
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        AnhXa();
        GetCategory();//load dữ liệu cho category

    }
    private void AnhXa() {
        rcCate = (RecyclerView) findViewById(R.id.rc_category);
    }
    private void GetCategory() {
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categoryList = response.body(); // Nhận mảng

                    // Kiểm tra dữ liệu trả về
                    if (categoryList == null || categoryList.isEmpty()) {
                        Log.d("Logg", "Dữ liệu trả về rỗng");
                        return;
                    }

                    // Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(RetrofitActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(getApplicationContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false);

                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                    Log.d("Logg", "Adapter đã được gán cho RecyclerView");
                } else {
                    Log.d("Logg", "Lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Logg", "Lỗi kết nốiiiiiiiiiiiiiiiii: " + t.getMessage());
            }
        });
    }

}