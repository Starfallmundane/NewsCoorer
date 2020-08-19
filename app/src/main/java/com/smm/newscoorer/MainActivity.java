package com.smm.newscoorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.smm.newscoorer.adapter.LeftAdapter;
import com.smm.newscoorer.fragment.FinanceFragment;
import com.smm.newscoorer.fragment.HomeFragment;
import com.smm.newscoorer.fragment.NewsFragment;
import com.smm.newscoorer.fragment.ServerFragment;
import com.smm.newscoorer.fragment.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments= new ArrayList();//fragment的集合
    private int lastShowFragment = 0;   //表示最后一个显示的Fragment
//    private RadioButton mRadio = findViewById(R.id.main_radio);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        点击切换
        initView();
//        初始化fragment
        initFragments();
        initLeft(); // 初始化左侧列表数据

    }
    public void initLeft(){
        //准备数据，有可能是写死数据，有可能是网络获取数据，如下面备注说明
        ArrayList<String>  menuList=new ArrayList<String>();
        menuList.add("1111111111");
        menuList.add("2222222222");
        menuList.add("3333333333");
        menuList.add("4444444444");
        menuList.add("555555555");

        /**
         * 这里创建Adapter填入数据又两种情况
         * 1.是本地就有数据，有固定的死数据，你可以先创建号数据这样直接传入  new MenuLeftAdapter(数据)
         *
         * 2.这种情况常用（可以包含上一种）
         * 大部分通过网络获取数据，就需要先创建一个空的数据Adapter，最后再去传入数据
         * 先  new MenuLeftAdapter(null)
         * 后  adapter.setNewData(数据);
         */
        LeftAdapter left_menu = new LeftAdapter(null);
        RecyclerView rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(this));
        rv_menu.setAdapter(left_menu);
        left_menu.setNewData(menuList);

    }
    public void initView(){
        RadioGroup mRadio=findViewById(R.id.main_radio);
        //点击RadioGroup按钮进行切换fragment
        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb_function:
                        if(lastShowFragment!=0){
                            switchFrament(lastShowFragment, 0);
                            lastShowFragment = 0;
                        }
                        break;
                    case R.id.rb_news_center:
                        if(lastShowFragment!=1){
                            switchFrament(lastShowFragment, 1);
                            lastShowFragment = 1;
                        }
                        break;
                    case R.id.rb_smart_service:
                        if(lastShowFragment!=2){
                            switchFrament(lastShowFragment, 2);
                            lastShowFragment = 2;
                        }
                        break;
                    case R.id.rb_gov_affairs:
                        if(lastShowFragment!=3){
                            switchFrament(lastShowFragment, 3);
                            lastShowFragment = 3;
                        }
                        break;
                    case R.id.rb_setting:
                        if(lastShowFragment!=4){
                            switchFrament(lastShowFragment, 4);
                            lastShowFragment = 4;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void switchFrament(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastIndex));
        if (!fragments.get(index).isAdded()) {
            transaction.add(R.id.framelayout_shou, fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }

    public void initFragments(){
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new ServerFragment());
        fragments.add(new FinanceFragment());
        fragments.add(new SettingFragment());
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout_shou, (Fragment) fragments.get(0))
                .show(fragments.get(0))
                .commit();
    }
}
