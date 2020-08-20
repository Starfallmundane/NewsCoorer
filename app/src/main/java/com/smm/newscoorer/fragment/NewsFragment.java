package com.smm.newscoorer.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.newscoorer.adapter.HomeAdapter;
import com.smm.newscoorer.adapter.NewsAdapter;
import com.smm.newscoorer.bean.BaseBean;
import com.smm.newscoorer.bean.NewsBean;
import com.smm.newscoorer.R;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;

public class NewsFragment extends Fragment{
    private View view;
    String url = "http://192.168.43.28:8080/zhbj/categories.json";
    ArrayList<String> bannerList ;
    List<NewsBean> list;
    NewsAdapter newsAdapter;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout srl_home;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getList();
////        initNews();
//    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.newscourr, container, false);
        Log.e("liuxing", "创建--NewsFragment");
//        数据
        getList();
//        轮播图
        initNews();
        return view;
    }
//    轮播图
    public void initNews(){
        initBannerData();//获取数据
//        轮播图控件设置
        Banner banner_home= view.findViewById(R.id.banner_home);
        HomeAdapter BannerAdapter = new HomeAdapter(getActivity(),bannerList);
        banner_home.setAdapter(BannerAdapter);
        //设置轮播图底部小圆点
        banner_home.setIndicator(new CircleIndicator(getActivity()));

//        设置下面新闻列表
//列表控件
        newsAdapter = new NewsAdapter(null);
        mRecyclerView = view.findViewById(R.id.rv_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(newsAdapter);
        newsAdapter.setAnimationEnable(true);
        initRecv();
//        getList();


    }
    private void initRecv(){
        srl_home=view.findViewById(R.id.srl_home);
        srl_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                请求网络数据
                getList();
            }
        });
    }
    //轮播图数据
    private void initBannerData() {
        bannerList = new ArrayList<String>();
        bannerList.add("http://www.g12e.com/upload/html/2007/8/31/liqion947200783110451392826.jpg");
        bannerList.add("http://p4.so.qhmsg.com/t01fba97e00c6a49a89.jpg");
        bannerList.add("http://up.enterdesk.com/edpic_source/df/66/f6/df66f62a97c8e6488ded53ce326b3cb2.jpg");
        bannerList.add("http://pic39.nipic.com/20140308/251960_174116725000_2.jpg");
        bannerList.add("http://p0.so.qhmsg.com/t01fc496abc036fab17.jpg");
    }
    public void getList() {
        OkHttpUtils.get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                Log.e("liuxing", "数据呢?它可能不喜欢我");
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("liuxing","成功"+response);
                parseJson(response);

            }
        });
    }

    //    解析json字符串
    public void parseJson(String jsonString) {
        Gson gson = new Gson();
        /**
         * 1. 要解析的json字符串
         * 2. 安装哪个Javabean解析
         */
        BaseBean baseBean = gson.fromJson(jsonString, BaseBean.class);
        if (baseBean.isSuccess()) {
            BaseBean<List<NewsBean>> data = new Gson().fromJson(jsonString, new TypeToken<BaseBean<List<NewsBean>>>() {}.getType());
            Log.e("liuxing", data.toString());
            NewsBean mNewsBean= (NewsBean) data.data.get(0);
            Log.e("liuxing", mNewsBean.title);
            newsAdapter.setList(mNewsBean.children);

        }


//        BaseBean<List<NewsBean>> baseBean = new Gson().fromJson(jsonString, new TypeToken<BaseBean<List<NewsBean>>>() {}.getType());
//        if(baseBean.isSuccess()){
//            List<NewsBean> list=baseBean.data;
//            newsAdapter.setList(list);
//        }
    }

}
