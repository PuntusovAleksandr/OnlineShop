package com.aleksandrp.onlineshopping.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.activity.ShowSearchActivity;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class SearchFragment extends Fragment {

    private RelativeLayout rlCategory;
    private ExpandableRelativeLayout mExpandableCategory;
    private ImageView mOpenImage;
    private TextView mTextView;
    private Button btSubmit;
    private EditText etSearch;
    private List<String> catNames;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create list
        catNames = getCategoryList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_search, container, false);

        initUi(mView);

        return mView;
    }

    private void initUi(View mView) {
        mExpandableCategory = (ExpandableRelativeLayout) mView.findViewById(R.id.expandable_category);
        mOpenImage = (ImageView) mView.findViewById(R.id.iv_open);

        mTextView = (TextView) mView.findViewById(R.id.tv_select_category);

        rlCategory = (RelativeLayout) mView.findViewById(R.id.rl_category);
        rlCategory.setOnClickListener(mListener);

        btSubmit = (Button) mView.findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(mListener);

        initListView(mView);
    }

    private void initListView(View mView) {
        ListView listView = (ListView) mView.findViewById(R.id.list_view);

        // create ArrayAdapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, catNames);
        listView.setAdapter(adapter);

        etSearch = (EditText) mView.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = etSearch.getText().toString();
                adapter.getFilter().filter(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                closeExpand();
                mTextView.setText(catNames.get(position));
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UtilsApp.disableDoubleClick(v);
            switch (v.getId()) {
                case R.id.rl_category:
                    visibleInvisible();
                    break;
                case R.id.bt_submit:
                    String category = mTextView.getText().toString();
                    if (category.equals(getActivity().getString(R.string.select_category))) {
                        UtilsApp.showTopSnackBar(v, R.string.make_your_choice);
                    } else {
                        Intent mIntent = new Intent(getActivity(), ShowSearchActivity.class);
                        mIntent.putExtra(StaticParams.KEY_CATEGORY, category);
                        getActivity().startActivity(mIntent);
                    }
                    break;
            }
        }
    };

    private void visibleInvisible() {
        if (mExpandableCategory.isExpanded()) {
            closeExpand();
        } else {

            mExpandableCategory.expand();
            mOpenImage.setImageResource(R.drawable.down_48px);
        }
    }


    private void closeExpand() {
        mExpandableCategory.collapse();
        mOpenImage.setImageResource(R.drawable.up_48px);
    }


    public ArrayList<String> getCategoryList() {
        // TODO: 09.06.2016 load listCategory
        final ArrayList<String> mStrings = new ArrayList<>();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    mStrings.add("ds" + i + " as" + "\ns");
                }
            }
        });
        return mStrings;
    }

}
