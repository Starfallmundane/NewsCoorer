package com.smm.newscoorer.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.smm.newscoorer.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeftAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public LeftAdapter(List<String> data) {
        super(R.layout.left_item_menu);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @NotNull String item) {
        TextView tv_item_menu_name=baseViewHolder.getView(R.id.left_item_name);
        tv_item_menu_name.setText(item);
    }


}
