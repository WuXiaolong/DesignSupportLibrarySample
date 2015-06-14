package com.example.wuxiaolongl.designsupportlibrarysample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab3"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabLayoutFragment(getResources().getColor(R.color.accent_material_light)), "CAT");
        adapter.addFrag(new TabLayoutFragment(getResources().getColor(R.color.ripple_material_light)), "DOG");
        adapter.addFrag(new TabLayoutFragment(getResources().getColor(R.color.button_material_dark)), "MOUSE");
        viewPager.setAdapter(adapter);
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

    public class TabLayoutFragment extends Fragment {
        int color;

        public TabLayoutFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.bg);
            frameLayout.setBackgroundColor(color);
            return view;
        }
    }

}
