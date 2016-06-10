package com.aleksandrp.onlineshopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;
import com.squareup.picasso.Picasso;

public class FullDetaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detais);

        initUi();
    }

    private void initUi() {

        String title = getIntent().getStringExtra(StaticParams.KEY_TITLE);
        String description = getIntent().getStringExtra(StaticParams.KEY_DESCRIPTION);
        String price = getIntent().getStringExtra(StaticParams.KEY_PRICE);
        String urlIcon = getIntent().getStringExtra(StaticParams.KEY_URL_ICON_BIG);
        boolean isSave = getIntent().getBooleanExtra(StaticParams.KEY_SAVE, false);


        TextView tv_title = (TextView) findViewById(R.id.title_product);
        TextView tv_description = (TextView) findViewById(R.id.description_product);
        TextView tv_price = (TextView) findViewById(R.id.price_product);

        tv_title.setText(title);
        tv_description.setText(description);
        tv_price.setText("Prise " + price);

        ImageView icon = (ImageView) findViewById(R.id.icon_product);
        Picasso.with(FullDetaisActivity.this)
                .load(urlIcon)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(icon);

        Button save = (Button) findViewById(R.id.bt_save);
        if (isSave) {
            save.setText(R.string.delete);
            save.setBackgroundResource(R.color.green);
        } else {
            save.setText(R.string.save);
            save.setBackgroundResource(R.color.red);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsApp.disableDoubleClick(v);
                // TODO: 10.06.2016 Save or not in DB
            }
        });
    }
}
