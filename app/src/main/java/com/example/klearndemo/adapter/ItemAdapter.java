package com.example.klearndemo.adapter;

import android.media.Image;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.klearndemo.R;
import com.example.klearndemo.entity.Item;

import java.util.List;

public class ItemAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public ItemAdapter(@Nullable List<Item> data) {
        super(R.layout.layout_rv_item,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Item item) {

        Glide.with(mContext)
                .load(item.getCover())
                .centerCrop()
                .into((ImageView) helper.getView(R.id.item_iv));

        helper.setText(R.id.item_tv,item.getDesc());
    }
}
