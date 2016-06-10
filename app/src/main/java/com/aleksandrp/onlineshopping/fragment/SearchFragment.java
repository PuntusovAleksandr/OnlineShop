package com.aleksandrp.onlineshopping.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.aleksandrp.onlineshopping.MainActivity;
import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.activity.ShowSearchActivity;
import com.aleksandrp.onlineshopping.model.Categories;
import com.aleksandrp.onlineshopping.model.Category;
import com.aleksandrp.onlineshopping.retrofit.EtsyService;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private List<Category> mCategoryList;
    private ArrayAdapter<String> adapter;

    private MainActivity mActivity;

    public SearchFragment(Context mContext) {
        this.mActivity = (MainActivity) mContext;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create list
        catNames = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_search, container, false);
        initUi(mView);
        loadCategoriesList();
        return mView;
    }


    private void loadCategoriesList() {

        mActivity.mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = StaticParams.getRetrofit();

        EtsyService mService = retrofit.create(EtsyService.class);
        Call mCall = mService.getAllCategories(StaticParams.KEY_API);
        mCall.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) {
                mActivity.mProgressBar.setVisibility(View.GONE);
                Categories mCategories = (Categories) response.body();
                if (mCategories == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {
                    //200 ok
                    mCategoryList = mCategories.getCategories();
                    for (int i = 0; i < mCategoryList.size(); i++) {
                        catNames.add(mCategoryList.get(i).getName());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mActivity.mProgressBar.setVisibility(View.GONE);
                if (t instanceof UnknownHostException) {
                    UtilsApp.showTopSnackBar(rlCategory, R.string.check_internet);
                }
            }
        });
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
        adapter = new ArrayAdapter<String>(getActivity(),
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
            if ( UtilsApp.checkInternet(getActivity())) {
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
        }
    };

    private void visibleInvisible() {
        if (mExpandableCategory.isExpanded()) {
            closeExpand();
        } else {

            mExpandableCategory.expand();
            mOpenImage.setImageResource(R.drawable.up_48px);
        }
    }


    private void closeExpand() {
        mExpandableCategory.collapse();
        mOpenImage.setImageResource(R.drawable.down_48px);
    }

}
