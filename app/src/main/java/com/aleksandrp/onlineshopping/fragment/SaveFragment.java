package com.aleksandrp.onlineshopping.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.adapter.RecyclerAdapter;
import com.aleksandrp.onlineshopping.model.ItemProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class SaveFragment extends Fragment {

    public SaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_save, container, false);


        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_last);
        GridLayoutManager mLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        ArrayList<ItemProduct> mTransactionList = getTransaction();
        RecyclerAdapter mAdapter =
                new RecyclerAdapter(mTransactionList, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        return mView;
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
                    true
            );
            mProducts.add(mProduct);
        }

        return mProducts;
    }

}
