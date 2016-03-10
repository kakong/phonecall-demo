package com.zjk.phonecall.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zjk.phonecall.R;
import com.zjk.phonecall.entity.CoustomerEntity;
import com.zjk.phonecall.entity.RecordEntity;
import com.zjk.phonecall.fragment.CallFragment;
import com.zjk.phonecall.fragment.ContactFragment;
import com.zjk.phonecall.fragment.FragmentAdapter;
import com.zjk.phonecall.fragment.ListFragment;
import com.zjk.phonecall.provider.DBhandle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public List<RecordEntity> mRecordList;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getBaseContext();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();
        setCoustomer(this.getBaseContext());

    }


//    protected void onStop() {
//        super.onStop();
//        GetRecord getrecord = new GetRecord();
//        getrecord.setCount(2);
//        mRecordList = getrecord.getRe(mContext);
//        DBhandle dbhandle = new DBhandle();
//        dbhandle.insertContact(mContext, mRecordList.get(0));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("客户");
        titles.add("拨号");
        titles.add("记录");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new CallFragment());
        fragments.add(new ContactFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    public static void setCoustomer(Context mContext){
        List<CoustomerEntity> mCoustomerList;
        String[][] coustomer = {
                {"13631224465", "钟家康", "先生"},
                {"10086", "移动", "先生"},
                {"10010", "联通", "女士"},
        };
        mCoustomerList = new ArrayList<CoustomerEntity>();
        for(int i=0;i<coustomer.length;i++){
            CoustomerEntity coustomerEntity = new CoustomerEntity();
            coustomerEntity.setPhoneNumber(coustomer[i][0]);
            coustomerEntity.setName(coustomer[i][1]);
            coustomerEntity.setSex(coustomer[i][2]);
            mCoustomerList.add(coustomerEntity);
        }

        DBhandle dBhandle = new DBhandle();
        dBhandle.insertCoustomer(mContext, mCoustomerList);
        String sql = "delete from customers where name in (select name from customers group by name having count(*)>1)" +
                "and _id not in (select min(_id) from customers group by name having count(*)>1) ";
        dBhandle.handle(mContext, sql);
        String sql3 = "delete from contacts;";
        dBhandle.handle(mContext, sql3);


    }
//    protected void onRestart(){
//        super.onRestart();
//        GetRecord getrecord = new GetRecord();
//        getrecord.setCount(2);
//        mRecordList = getrecord.getRe(mContext);
//        DBhandle dbhandle = new DBhandle();
//        dbhandle.insertContact(mContext, mRecordList.get(0));
//    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
