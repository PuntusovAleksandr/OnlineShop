package com.aleksandrp.onlineshopping.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.adapter.RecyclerAdapter;
import com.aleksandrp.onlineshopping.model.Categories;
import com.aleksandrp.onlineshopping.model.ImageEtsy;
import com.aleksandrp.onlineshopping.model.ItemProduct;
import com.aleksandrp.onlineshopping.model.Products;
import com.aleksandrp.onlineshopping.retrofit.EtsyService;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowSearchActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private ArrayList<ItemProduct> mItemProducts;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);

        initToolBar();
        initUi();

        loadProducts(getIntent().getStringExtra(StaticParams.KEY_CATEGORY));
    }

    private void loadProducts(String mCategory) {

        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = StaticParams.getRetrofit();

        EtsyService mService = retrofit.create(EtsyService.class);
        Call mCall = mService.getAllProducts(StaticParams.KEY_API, mCategory, "Images");
        mCall.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) {
                mProgressBar.setVisibility(View.GONE);
                Products mProducts = (Products) response.body();
                if (mProducts == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {
                    //200 ok
                    List<ItemProduct> mList = mProducts.getProducts();
                    for (int i = 0; i < mList.size(); i++) {
                        ArrayList<ImageEtsy> mImages = mList.get(i).getImages();
                        ImageEtsy  mImageEtsy = mImages.get(0);
                        mItemProducts.add(new ItemProduct(
                                mList.get(i).getListing_id(),
                                mList.get(i).getCategory_id(),
                                mList.get(i).getTitle(),
                                mList.get(i).getDescription(),
                                mList.get(i).getPrice(),
                                mImageEtsy.getUrl_170x135(),
                                false,
                                mImageEtsy.getUrl_fullxfull()
                        ));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                if (t instanceof UnknownHostException) {
                    UtilsApp.showTopSnackBar(mProgressBar, R.string.check_internet);
                }
            }
        });

    }

    private void initUi() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_last);
        GridLayoutManager mLayoutManager =
                new GridLayoutManager(ShowSearchActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mItemProducts = new ArrayList<>();
        mAdapter = new RecyclerAdapter(mItemProducts, ShowSearchActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_show);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
