package com.aleksandrp.onlineshopping.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.aleksandrp.onlineshopping.fragment.SaveFragment;
import com.aleksandrp.onlineshopping.fragment.SearchFragment;
import com.aleksandrp.onlineshopping.model.Category;

import java.util.List;


/**
 * Created by zloj on 04.11.15.
 *
 */
public class TabAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;
    private Context mContext;

    public TabAdapter(FragmentManager fm, int numberOfTabs, Context mContext) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.mContext = mContext;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SearchFragment(mContext);
            case 1:
                return new SaveFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
