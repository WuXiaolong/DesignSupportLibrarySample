package com.wuxiaolong.designsupportlibrarysample;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 吴小龙同學
 * on 2015/11/16
 * 官网文档：https://material.google.com/
 * 个人博客：http://wuxiaolong.me/
 * 公众号：吴小龙同学
 */
public class SecondFragment extends Fragment {
    private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("吴小龙");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        view.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWebView("http://weixin.sogou.com/weixin?type=1&query=吴小龙同学");
            }
        });
        view.findViewById(R.id.blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWebView("http://wuxiaolong.me/");
            }
        });
        view.findViewById(R.id.github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWebView("https://github.com/WuXiaolong/");
            }
        });
    }

    private void toWebView(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
