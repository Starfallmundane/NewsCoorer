package com.smm.newscoorer.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.smm.newscoorer.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter<CategoryBean> extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {
    public NewsAdapter(List<CategoryBean> data) {
        super(R.layout.news_item);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CategoryBean categoryBean) {
        int position = baseViewHolder.getLayoutPosition();
//        Log.e("liuxing",position+"=========="+item.getTitle());
        /**
         * BaseQuickAdapter  基本用法参考文档
         * https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/1-BaseQuickAdapter.md
         *
         * 这里控件有两种用法，注意我的item里控件命名，多规范啊
         */
        //写法一。框架自己通用的，如果像图片其他控件，没有提供，就得用第二种了


        //写法二,先获取控件，在用安卓自己的方法去做
        ImageView iv_item_homecate_pic= baseViewHolder.getView(R.id.iv_item_homecate_pic);
        String  picUrl="https://p0.ssl.qhimgs1.com/sdr/400__/t011852691d4d1d23ca.jpg";
        Glide.with(this.getContext())
                .load(picUrl)
                .into(iv_item_homecate_pic);
    }
}
