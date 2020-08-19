package com.smm.newscoorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.smm.newscoorer.yindao.guuide_1;
import com.smm.newscoorer.yindao.guuide_2;
import com.smm.newscoorer.yindao.guuide_3;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class GuideActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        final Button button =findViewById(R.id.guide_button);
        ViewPager viewpager=findViewById(R.id.viewPager);

//        myViewPagerAdapter adapter = new myViewPagerAdapter();
        myfargmentPagerAdapter adapter;
        adapter = new myfargmentPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        initData();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==ImageId.length-1){
//                    按钮显示
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            跳转到主界面
                            Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    });
                }else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //    图片数据源
    private static int[] ImageId = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    //    初始化数据
    private void initData() {
        for (int i = 0; i < ImageId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(ImageId[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);
        }

    }
    class myViewPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return ImageId.length;
        }
//* 1. 根据position获取对应的view，并且返回
//         * 2. 将view添加到container中

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView=imageViewList.get(position);
            container.addView(imageView);
            return imageView;
        }
        /**
         * 将Object从container中移除
         */
        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }


    private static class myfargmentPagerAdapter extends FragmentPagerAdapter {


        public myfargmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        myfargmentPagerAdapter(Object fragmentManager) {
            super((FragmentManager) fragmentManager);
        }


        @NonNull
        @Override
        /**
         * 根据当前的position返回对应的fragment
         */
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position==0){
                fragment=new guuide_1();
            }if(position==1){
                fragment=new guuide_2();
            }if(position==2){
                fragment=new guuide_3();
            }
            assert fragment != null;
            return fragment;
        }

        @Override
        public int getCount() {
            return ImageId.length;
        }
    }
}
