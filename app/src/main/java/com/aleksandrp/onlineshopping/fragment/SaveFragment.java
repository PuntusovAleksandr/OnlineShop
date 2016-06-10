package com.aleksandrp.onlineshopping.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.adapter.RecyclerAdapter;
import com.aleksandrp.onlineshopping.db.ImplDb;
import com.aleksandrp.onlineshopping.model.ItemProduct;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class SaveFragment extends Fragment {

    private RecyclerAdapter mAdapter;
    private ArrayList<ItemProduct> mTransactionList;
    private RecyclerView mRecyclerView;

    public SaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_save, container, false);

        SwipeRefreshLayout mSwipeRefreshLayout =
                (SwipeRefreshLayout) mView.findViewById(R.id.refreshLayout);
        mSwipeRefreshLayout.setEnabled(false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_last);
        GridLayoutManager mLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        update();
        return mView;
    }

    public ArrayList<ItemProduct> getTransaction() {

        return ImplDb.getInstanceDB(getActivity()).getAllProducts();
    }


    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public void update() {
        mTransactionList = getTransaction();
        if (mAdapter != null) {
            mAdapter = null;
        }
        mAdapter = new RecyclerAdapter(mTransactionList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
}
