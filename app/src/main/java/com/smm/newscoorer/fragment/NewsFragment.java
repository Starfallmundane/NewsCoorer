package com.smm.newscoorer.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
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
        getList(true);
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
        initLoadMore();
//        getList();


    }
    private void initLoadMore() {
        newsAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getList(false);
            }
        });
        newsAdapter.getLoadMoreModule().setAutoLoadMore(true);
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        newsAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }
    private void initRecv(){
        srl_home=view.findViewById(R.id.srl_home);
        srl_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                请求网络数据
                getList(true);
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
    public void getList(boolean isRefresh) {
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
                //记的请求结束关闭刷新
                srl_home.setRefreshing(false);
                //支持加载更多
                newsAdapter.getLoadMoreModule().setEnableLoadMore(true);
                parseJson(isRefresh,response);

            }
        });
    }

    //    解析json字符串
    public void parseJson(boolean isRefresh,String jsonString) {
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
            initViewData(isRefresh, (List<NewsBean>) mNewsBean);

        }


//        BaseBean<List<NewsBean>> baseBean = new Gson().fromJson(jsonString, new TypeToken<BaseBean<List<NewsBean>>>() {}.getType());
//        if(baseBean.isSuccess()){
//            List<NewsBean> list=baseBean.data;
//            newsAdapter.setList(list);
//        }
    }
//    界面和数据
    public void initViewData(boolean isRefresh, List<NewsBean> mNewsBean){
        if (isRefresh){     //第一次或者刷新需要先清空数据，重新添加
            if (mNewsBean!=null&&mNewsBean.size()>0){
                newsAdapter.setList(mNewsBean);
            }else{
                newsAdapter.setEmptyView(getEmptyDataView());  //数据为空，显示空界面
            }
        }else{     //加载更多，直接在原有的数据集合基础上，添加新数据
            if (mNewsBean!=null&&mNewsBean.size()>0){
                newsAdapter.addData(mNewsBean);
                newsAdapter.getLoadMoreModule().loadMoreComplete();    //已经得到数据，加载更多执行完成
            }else{
                //当后台加载更多无数据返回的时候，停止加载更多
                newsAdapter.getLoadMoreModule().loadMoreEnd();
            }
        }
    }
//    没有数据
    private View getEmptyDataView() {
        View notDataView = getLayoutInflater().inflate(R.layout.empty_news, mRecyclerView, false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList(true);       //点击重新请求数据
            }
        });
        return notDataView;
    }
//错误的数据
    private View getErrorView() {
        View errorView = getLayoutInflater().inflate(R.layout.error_news, mRecyclerView, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList(true);      //点击重新请求数据
            }
        });
        return errorView;
    }
}
