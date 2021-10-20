package com.example.pintasso;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pintasso.ui.ImageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView pRecyclerView;

    private ImageAdapter pImageAdapter;

    private int pNumberOfColumns = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        // Binding ButterKnife annotations now that content view has been set
        ButterKnife.bind(this);

        pImageAdapter = new ImageAdapter(this, new ArrayList<>());
        pRecyclerView.setAdapter(pImageAdapter);
        pRecyclerView.setLayoutManager(new GridLayoutManager(this, pNumberOfColumns));

    }
}
