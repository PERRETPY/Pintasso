package com.example.pintasso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_toPicasso_button)
    public void onClickToPicassoButton() {
        Intent toPicassoIntent = new Intent(this, PicassoActivity.class);
        startActivity(toPicassoIntent);
    }

    @OnClick(R.id.main_toClassic_button)
    public void onClickToClassicButton() {
        Intent toClassicIntent = new Intent(this, ClassicActivity.class);
        startActivity(toClassicIntent);
    }

}