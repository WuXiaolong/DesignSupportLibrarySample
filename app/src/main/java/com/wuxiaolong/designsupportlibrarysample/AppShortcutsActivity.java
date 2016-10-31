package com.wuxiaolong.designsupportlibrarysample;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppShortcutsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_shortcuts);
        initToolbar("AppShortcuts");
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
                ShortcutInfo shortcut = new ShortcutInfo.Builder(mActivity, "shortcutId3")
                        .setShortLabel("Web site")
                        .setLongLabel("Open the web site")
                        .setIcon(Icon.createWithResource(mActivity, R.drawable.ic_link_24dp))
                        .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://wuxiaolong.me/")))
                        .build();
                try {
                    shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
                List<ShortcutInfo> shortcutInfoList = shortcutManager.getDynamicShortcuts();//可以做个list管理App Shortcuts，这里略
                List<String> list = new ArrayList<>();
                list.add("shortcutId3");
                try {
                    shortcutManager.removeDynamicShortcuts(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
                List<String> list = new ArrayList<>();
                list.add("shortcutId3");
                try {
                    shortcutManager.disableShortcuts(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
