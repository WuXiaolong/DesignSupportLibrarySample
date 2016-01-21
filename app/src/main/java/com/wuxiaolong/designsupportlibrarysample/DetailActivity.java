package com.wuxiaolong.designsupportlibrarysample;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {
    public static String TRANSITION_PIC = "transitionPic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        ViewCompat.setTransitionName(backdrop, DetailActivity.TRANSITION_PIC);
        if (position % 2 == 0) {
            backdrop.setBackgroundResource(R.mipmap.show_img1);
        } else {
            backdrop.setBackgroundResource(R.mipmap.show_img2);
        }
    }

}
