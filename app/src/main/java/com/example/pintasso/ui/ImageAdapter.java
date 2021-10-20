package com.example.pintasso.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pintasso.R;
import com.example.pintasso.model.ImageUrl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private Activity context;
    private LayoutInflater inflater;

    public void setUrlList(List<ImageUrl> urlList) {
        this.urlList = urlList;
    }

    private List<ImageUrl> urlList;

    public ImageAdapter(Activity context, List<ImageUrl> urlList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_item, parent, false);
        ImageAdapter.ViewHolder holder = new ImageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_item)
        private ImageView pImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
