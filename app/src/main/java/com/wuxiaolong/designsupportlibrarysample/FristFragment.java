package com.wuxiaolong.designsupportlibrarysample;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴小龙同學
 * on 2016/9/11.
 */

public class FristFragment extends Fragment {
    private Toolbar mToolbar;

    public static FristFragment newInstance() {

        Bundle args = new Bundle();
        FristFragment fragment = new FristFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frist, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        initTabLayout(view);
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_bottomsheetdialog:
                        ((MainActivity) getActivity()).showBottomSheetDialog();
                        break;
                    case R.id.action_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WuXiaolong/DesignSupportLibrarySample"));
                        getActivity().startActivity(intent);
                        break;
                }
                return true;
            }
        });
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

    private void initTabLayout(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
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

}
