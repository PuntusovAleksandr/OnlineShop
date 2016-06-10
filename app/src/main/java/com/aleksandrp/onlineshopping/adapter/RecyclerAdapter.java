package com.aleksandrp.onlineshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.onlineshopping.R;
import com.aleksandrp.onlineshopping.activity.FullDetaisActivity;
import com.aleksandrp.onlineshopping.model.ImageEtsy;
import com.aleksandrp.onlineshopping.model.ItemProduct;
import com.aleksandrp.onlineshopping.utilss.StaticParams;
import com.aleksandrp.onlineshopping.utilss.UtilsApp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class RecyclerAdapter extends
        RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private List<ItemProduct> mProducts;
    private Context mContext;

    public RecyclerAdapter(ArrayList<ItemProduct> mProducts, Context mContext) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ItemProduct mProduct = mProducts.get(position);

        holder.save.setVisibility(View.GONE);

        holder.title.setText(mProduct.getTitle());
        holder.price.setText("$" + mProduct.getPrice());
        holder.description.setText(mProduct.getDescription());


        Picasso.with(mContext)
                .load(mProduct.getIcon_url_small())
                .placeholder( R.drawable.progress_animation )
                .error(R.mipmap.ic_launcher)
                .into(holder.icon);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsApp.disableDoubleClick(v);
                Intent mIntent = new Intent(mContext, FullDetaisActivity.class);
                mIntent.putExtra(StaticParams.KEY_TITLE, mProduct.getTitle());
                mIntent.putExtra(StaticParams.KEY_DESCRIPTION, mProduct.getDescription());
                mIntent.putExtra(StaticParams.KEY_PRICE, mProduct.getPrice());
                mIntent.putExtra(StaticParams.KEY_URL_ICON_BIG, mProduct.getIcon_url_big_size());
                mIntent.putExtra(StaticParams.KEY_SAVE, mProduct.isSaved());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mProducts == null) return 0;
        return mProducts.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView title, description, price;
        ImageView icon;
        Button save;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_product);
            title = (TextView) itemView.findViewById(R.id.title_product);
            description = (TextView) itemView.findViewById(R.id.description_product);
            price = (TextView) itemView.findViewById(R.id.price_product);

            icon = (ImageView) itemView.findViewById(R.id.icon_product);

            save = (Button) itemView.findViewById(R.id.bt_save);
        }
    }


}
