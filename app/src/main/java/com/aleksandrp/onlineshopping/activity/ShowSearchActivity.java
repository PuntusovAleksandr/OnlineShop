package com.aleksandrp.onlineshopping.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.adapter.RecyclerAdapter;
import com.aleksandrp.onlineshopping.model.ItemProduct;
import com.aleksandrp.onlineshopping.utilss.StaticParams;

import java.util.ArrayList;

public class ShowSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String category = getIntent().getStringExtra(StaticParams.KEY_CATEGORY);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_last);
        GridLayoutManager mLayoutManager =
                new GridLayoutManager(ShowSearchActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        ArrayList<ItemProduct> mTransactionList = getTransaction();
        RecyclerAdapter mAdapter =
                new RecyclerAdapter(mTransactionList, ShowSearchActivity.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    public ArrayList<ItemProduct> getTransaction() {

        // TODO: 09.06.2016 need from db
        ArrayList<ItemProduct> mProducts = new ArrayList<>();
        ItemProduct mProduct;
        for (int i = 0; i < 150; i++) {
            mProduct = new ItemProduct(
                    "id " + i,
                    "category " + i,
                    "titel " + i,
                    "description " + i,
                    "price = " + i,
                    "url " + i,
                    false
            );
            mProducts.add(mProduct);
        }

        return mProducts;
    }

}
