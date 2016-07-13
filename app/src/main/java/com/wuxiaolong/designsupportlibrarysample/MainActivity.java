package com.wuxiaolong.designsupportlibrarysample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wuxiaolong.androidutils.library.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴小龙同學
 * on 2015/11/16
 * api文档：https://developer.android.com/reference/android/support/design/widget/package-summary.html
 * 个人博客：http://wuxiaolong.me/
 * 公众号：AndroidProgrammer
 */

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = initToolbar();
        mActivity = this;
        initDrawer();
        initNavigationViewHeader();
        initTabLayout();
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initNavigationViewHeader() {
        navigationView = (NavigationView) findViewById(R.id.navigation);
        //设置头像，布局app:headerLayout="@layout/drawer_header"所指定的头布局
        View view = navigationView.inflateHeaderView(R.layout.drawer_header);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        userName.setText(R.string.author);
        //View mNavigationViewHeader = View.inflate(MainActivity.this, R.layout.drawer_header, null);
        //navigationView.addHeaderView(mNavigationViewHeader);//此方法在魅族note 1，头像显示不全
        //菜单点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelected());
    }

    class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            //mToolbar.setTitle(menuItem.getTitle());
            switch (menuItem.getItemId()) {
                case R.id.navigation_item_1:
                    menuItem.setChecked(true);
                    return true;
                case R.id.navigation_item_2:
                    menuItem.setChecked(true);
                    return true;
                case R.id.navigation_item_night:
                    SharedPreferencesUtil.setBoolean(mActivity, AppConstant.ISNIGHT, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                    return true;
                case R.id.navigation_item_day:
                    SharedPreferencesUtil.setBoolean(mActivity, AppConstant.ISNIGHT, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                    return true;
                default:
                    mDrawerLayout.closeDrawers();
                    return true;
            }
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void initTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(viewPager);
        //适合很多tab
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tab均分,适合少的tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tab均分,适合少的tab,TabLayout.GRAVITY_CENTER
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment newfragment = new ContentFragment();
        Bundle data = new Bundle();
        data.putInt("id", 0);
        data.putString("title", getString(R.string.page1));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page1));

        newfragment = new ContentFragment();
        data = new Bundle();
        data.putInt("id", 1);
        data.putString("title", getString(R.string.page2));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page2));


        newfragment = new ContentFragment();
        data = new Bundle();
        data.putInt("id", 2);
        data.putString("title", getString(R.string.page3));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page3));


        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_bottomsheetdialog:
                showBottomSheetDialog();
                break;
            case R.id.action_about:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WuXiaolong/DesignSupportLibrarySample"));
                mActivity.startActivity(intent);
                break;
            default:
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);

        }

        return true;
    }

    private BottomSheetDialog mBottomSheetDialog;

    private void showBottomSheetDialog() {
        View sheetDialogView = getLayoutInflater().inflate(R.layout.sheet_dialog, null);
        mBottomSheetDialog = new BottomSheetDialog(mActivity);
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        mBottomSheetDialog.show();
    }
}
