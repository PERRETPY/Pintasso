package com.example.pintasso;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pintasso.event.EventBusManager;
import com.example.pintasso.event.SearchResultEvent;
import com.example.pintasso.ui.ImageAdapter;
import com.squareup.otto.Subscribe;

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

    @Override
    protected void onResume() {
        super.onResume();

        // Register to Event bus : now each time an event is posted, the activity will receive it if it is @Subscribed to this event
        EventBusManager.BUS.register(this);

        // Refresh search
        ImageSearchService.INSTANCE.searchImageFromQuery("test");
    }

    @Override
    protected void onPause() {
        // Unregister from Event bus : if event are posted now, the activity will not receive it
        EventBusManager.BUS.unregister(this);

        super.onPause();
    }

    @Subscribe
    public void searchResult(final SearchResultEvent event) {
        // Here someone has posted a SearchResultEvent
        runOnUiThread (() -> {
            // Step 1: Update adapter's model
            pImageAdapter.setUrlList(event.getItemList());
            pImageAdapter.notifyDataSetChanged();

            // Step 2: hide loader
            //TODO
        });

    }
}
