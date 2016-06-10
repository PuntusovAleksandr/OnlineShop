package com.aleksandrp.onlineshopping;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.aleksandrp.onlineshopping.adapter.TabAdapter;
import com.aleksandrp.onlineshopping.db.ImplDb;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabAdapter mTabAdapter;

    public ProgressBar mProgressBar;

    private ImplDb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ImplDb.getInstanceDB(MainActivity.this);

        initUi();

    }

    private void initUi() {
        mProgressBar = (ProgressBar) findViewById(R.id.main_progress);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.search));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.saved));


        FragmentManager fragmentManager = getFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabAdapter = new TabAdapter(fragmentManager, 2, MainActivity.this);
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(mListenerTabLayout);
    }


    private TabLayout.OnTabSelectedListener mListenerTabLayout = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };
}
