package com.example.viewpager_cricleindicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.ImageView;

public class ImagesViewPager2Adapter extends RecyclerView.Adapter<ImagesViewPager2Adapter.ImagesViewHolder> {
    private List<Images> imagesList;

    public ImagesViewPager2Adapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        // Set dữ liệu lên ảnh
        Images images = imagesList.get(position);
        if (images == null) {
            return;
        }
        holder.imageView.setImageResource(images.getImagesId());
    }

    @Override
    public int getItemCount() {
        if (imagesList != null) {
            return imagesList.size();
        }
        return 0;
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
