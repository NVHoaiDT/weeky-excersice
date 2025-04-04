package com.example.upload_image;

import static android.content.ContentValues.TAG;

import com.example.upload_image.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.RequiresApi;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.Manifest;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // UI Components
    Button btnChoose, btnUpload;
    ImageView imageViewChoose, imageViewUpload;
    EditText editTextUserName;
    TextView textViewUsername;

    // Variables
    private Uri mUri;
    private ProgressDialog mProgressDialog;

    // Constants
    public static final int MY_REQUEST_CODE = 100;
    public static final String TAG = MainActivity.class.getName();


    public static String[] storagePermissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storagePermissions33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };


    public static String[] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = storagePermissions33;
        } else {
            p = storagePermissions;
        }
        return p;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            requestPermissions(permissions(), MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private void AnhXa() {
        // Initialize buttons
        btnChoose = findViewById(R.id.chooseFileButton);
        btnUpload = findViewById(R.id.uploadButton);

        imageViewChoose = findViewById(R.id.currentAvatar);  // Sửa thành currentAvatar
        imageViewUpload = findViewById(R.id.imgLoaded);

        // Debug
        Log.d("DEBUG", "btnChoose: " + (btnChoose != null));
        Log.d("DEBUG", "btnUpload: " + (btnUpload != null));
        Log.d("DEBUG", "imageViewChoose: " + (imageViewChoose != null));
        Log.d("DEBUG", "imageViewUpload: " + (imageViewUpload != null));
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }

                        Uri uri = data.getData();
                        mUri = uri;

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageViewChoose.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );


    public void UploadImage1() {
        mProgressDialog.show();

        // Lấy username từ EditText
        //String username = editTextUserName.getText().toString().trim();
        RequestBody requestUsername = RequestBody.create(MediaType.parse("multipart/form-data"), "nvhoai");

        // Tạo RequestBody từ file ảnh
        String IMAGE_PATH = RealPathUtil.getRealPath(MainActivity.this, mUri);
        Log.e("UploadImage", IMAGE_PATH);
        File file = new File(IMAGE_PATH);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // Tạo MultipartBody.Part
        MultipartBody.Part partBodyAvatar = MultipartBody.Part.createFormData(Const.MY_IMAGES, file.getName(), requestFile);

        // Gọi API bằng Retrofit
        ServiceAPI.serviceApi.upload(requestUsername, partBodyAvatar).enqueue(new Callback<List<ImageUpload>>() {
            @Override
            public void onResponse(Call<List<ImageUpload>> call, Response<List<ImageUpload>> response) {
                mProgressDialog.dismiss();
                List<ImageUpload> imageUploads = response.body();

                if (imageUploads != null && !imageUploads.isEmpty()) {
                    for (ImageUpload image : imageUploads) {
                        textViewUsername.setText(image.getUsername());
                        Glide.with(MainActivity.this)
                                .load(image.getAvatar())
                                .into(imageViewUpload);
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageUpload>> call, Throwable t) {
                mProgressDialog.dismiss();
                // In lỗi đầy đủ ra LogCat
                Log.e("API_ERROR", "Upload failed", t);
                Log.e("API_ERROR", "Error message: " + t.getMessage());
                Log.e("API_ERROR", "Request URL: " + call.request().url());
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        AnhXa();

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Please wait upload...");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        // Sự kiện nút upload ảnh
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    UploadImage1();
                }
            }
        });
    }
}