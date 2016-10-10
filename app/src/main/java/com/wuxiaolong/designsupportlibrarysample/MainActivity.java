package com.wuxiaolong.designsupportlibrarysample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wuxiaolong.androidutils.library.SharedPreferencesUtil;

/**
 * Created by 吴小龙同學
 * on 2015/11/16
 * api文档：https://developer.android.com/reference/android/support/design/widget/package-summary.html
 * 个人博客：http://wuxiaolong.me/
 * 公众号：AndroidProgrammer
 */

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    //    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private Fragment currentFragment;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initNavigationViewHeader();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        Log.d("wxl", "initFragment=" + savedInstanceState);
        if (savedInstanceState == null) {
            currentFragment = new FristFragment();
            switchContent(currentFragment);
        } else {
            //activity销毁后记住销毁前所在页面
            currentIndex = savedInstanceState.getInt("currentIndex");
            switch (this.currentIndex) {
                case 0:
                    currentFragment = new FristFragment();
                    switchContent(currentFragment);
                    break;
                case 1:
                    currentFragment = new SecondFragment();
                    switchContent(currentFragment);
                    break;
            }
        }
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
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
            mDrawerLayout.closeDrawers();
            switch (menuItem.getItemId()) {
                case R.id.navigation_item_1:
                    currentIndex = 0;
                    menuItem.setChecked(true);
                    currentFragment = new FristFragment();
                    switchContent(currentFragment);
                    return true;
                case R.id.navigation_item_2:
                    currentIndex = 1;
                    menuItem.setChecked(true);
                    currentFragment = new SecondFragment();
                    switchContent(currentFragment);
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
                    return true;
            }
        }
    }

    public void switchContent(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment).commit();
        invalidateOptionsMenu();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("wxl", "onSaveInstanceState=" + currentIndex);
        outState.putInt("currentIndex", currentIndex);
        super.onSaveInstanceState(outState);
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

    public void showBottomSheetDialog() {
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
