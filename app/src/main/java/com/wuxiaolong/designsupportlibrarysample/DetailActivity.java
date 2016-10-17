package com.wuxiaolong.designsupportlibrarysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 吴小龙同學
 * on 2015/11/16
 * 官网文档：https://material.google.com/
 * 个人博客：http://wuxiaolong.me/
 * 公众号：吴小龙同学
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.detail_title));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        int position = this.getIntent().getIntExtra("position", 0);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        //设置过渡动画
        ViewCompat.setTransitionName(backdrop, AppConstants.TRANSITION_PIC);
        if (position % 2 == 0) {
            backdrop.setBackgroundResource(R.mipmap.show_img1);
        } else {
            backdrop.setBackgroundResource(R.mipmap.show_img2);
        }
    }

    /**
     * @param showImage 共享的元素
     */
    public static void startActivity(Activity activity, int position, ImageView showImage) {
        Intent intent = new Intent();
        intent.setClass(activity, DetailActivity.class);
        intent.putExtra("position", position);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, showImage, AppConstants.TRANSITION_PIC);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
