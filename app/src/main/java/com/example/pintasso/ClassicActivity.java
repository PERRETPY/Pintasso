package com.example.pintasso;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pintasso.event.EventBusManager;
import com.example.pintasso.event.SearchResultEvent;
import com.example.pintasso.ui.ImageAdapterClassic;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassicActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView pRecyclerView;

    @BindView(R.id.searchButton)
    Button pSearchButton;

    @BindView(R.id.searchText)
    EditText pSearchText;

    private ImageAdapterClassic pImageAdapter;

    private int pNumberOfColumns = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic);

        // Binding ButterKnife annotations now that content view has been set
        ButterKnife.bind(this);

        pImageAdapter = new ImageAdapterClassic(this, new ArrayList<>());
        pRecyclerView.setAdapter(pImageAdapter);
        pRecyclerView.setLayoutManager(new GridLayoutManager(this, pNumberOfColumns));

        pSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()==0){
                    pSearchButton.setEnabled(false);
                } else {
                    pSearchButton.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // While text is changing, hide list and show loader
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @OnClick(R.id.searchButton)
    public void onButtonSearchClick() {
        String query = this.pSearchText.getText().toString();
        Log.d("SEARCH QUERY : ", query);
        ImageSearchService.INSTANCE.searchImageFromQuery(query);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register to Event bus : now each time an event is posted, the activity will receive it if it is @Subscribed to this event
        EventBusManager.BUS.register(this);

        // Refresh search
        //ImageSearchService.INSTANCE.searchImageFromQuery("test");
        //Log.d("PICASSOACTIVITY", "Hello from here");
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
            //TODO hide loader
        });

    }
}
