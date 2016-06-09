package com.aleksandrp.onlineshopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.utilss.StaticParams;

public class FullDetaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detais);

        String category = getIntent().getStringExtra(StaticParams.KEY_ID_PRODUCT);
    }
}
