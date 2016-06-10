package com.aleksandrp.onlineshopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.db.ImplDb;
import com.aleksandrp.onlineshopping.model.ItemProduct;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;
import com.squareup.picasso.Picasso;

public class FullDetaisActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detais);

        initUi();
    }

    private void initUi() {

        id = getIntent().getIntExtra(StaticParams.KEY_ID_PRODUCT, 0);
        final String title = getIntent().getStringExtra(StaticParams.KEY_TITLE);
        final String description = getIntent().getStringExtra(StaticParams.KEY_DESCRIPTION);
        final String price = getIntent().getStringExtra(StaticParams.KEY_PRICE);
        final String urlIcon = getIntent().getStringExtra(StaticParams.KEY_URL_ICON);
        final String urlIcon_big = getIntent().getStringExtra(StaticParams.KEY_URL_ICON_BIG);
        final boolean isSave = getIntent().getBooleanExtra(StaticParams.KEY_SAVE, false);


        TextView tv_title = (TextView) findViewById(R.id.title_product);
        TextView tv_description = (TextView) findViewById(R.id.description_product);
        TextView tv_price = (TextView) findViewById(R.id.price_product);

        tv_title.setText(title);
        tv_description.setText(description);
        tv_price.setText("Prise " + price);

        ImageView icon = (ImageView) findViewById(R.id.icon_product);

        UtilsApp.checkInternet(FullDetaisActivity.this);
        Picasso.with(FullDetaisActivity.this)
                .load(urlIcon_big)
                .placeholder(R.drawable.progress_animation)
                .error(R.mipmap.ic_launcher)
                .into(icon);

        final Button save = (Button) findViewById(R.id.bt_save);
        if (isSave) {
            setStatusButton(save, R.string.delete, R.color.red);
        } else {
            setStatusButton(save, R.string.save, R.color.green);
        }
        if (save != null) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilsApp.disableDoubleClick(v);
                    if (isSave) {
                        ImplDb.getInstanceDB(FullDetaisActivity.this).deleteProduct(id);
                        setStatusButton(save, R.string.save, R.color.green);
                    } else {
                        if (isInDb()) {
                            UtilsApp.showTopSnackBar(save, R.string.you_have_product);
                        }else {
                            ImplDb.getInstanceDB(FullDetaisActivity.this).putItemProduct(new ItemProduct(
                                    id,
                                    title,
                                    description,
                                    price,
                                    urlIcon,
                                    isSave,
                                    urlIcon_big
                            ));
                            setStatusButton(save, R.string.delete, R.color.red);
                        }
                    }
                    finish();
                }
            });
        }
    }

    public boolean isInDb() {
        return ImplDb.getInstanceDB(FullDetaisActivity.this).isHaveProduct(id);
    }


    private void setStatusButton(Button mSave, int text, int color) {
        mSave.setText(text);
        mSave.setBackgroundResource(color);
    }


}
