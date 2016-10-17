title: Android Design Support Library使用
date: 2015-11-06 13:08:01
tags: DesignSupportLibrary
category: SupportLibrary
---

作为Android开发者，谷歌的Material design规范文档读过吗？
谷歌出Material design规范很久了，但是市面上的Material design风格的APP却很少（Android的杯具），这个规范应该产品经理或UI设计师应该熟知于心，官网地址：[https://material.google.com/](https://material.google.com/)，这里分享开发可能会使用的知识点和注意点，有详细sample哦。

<!--more-->

# 样式
## 色彩
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary1.jpg?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

如图，工具栏和大色块适合使用饱和度 500 的基础色，这也是你应用的主要颜色。状态栏适合使用更深一些的饱和度 700 的基础色。更多Color见：[Style– Color](https://material.google.com/style/color.html)

## 字体标准样式
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary2.jpg?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

```
<dimen name="button">14sp</dimen>
<!--给（图片、照片等）加说明文字-->
<dimen name="caption">12sp</dimen>
<dimen name="body">14sp</dimen>
<!--副标题-->
<dimen name="subheading">16sp</dimen>
<dimen name="title">20sp</dimen>
<dimen name="headline">24sp</dimen>
<dimen name="display1">34sp</dimen>
<dimen name="display2">45sp</dimen>
<dimen name="display3">56sp</dimen>
<dimen name="display4">112sp</dimen>
```
最基本的样式集合就是基于 12、14、16、20 和 34 号的字体排版缩放。

# 使用
## 添加依赖
app/build.gradle
```
 compile 'com.android.support:appcompat-v7:24.2.1'
 compile 'com.android.support:support-v4:24.2.1'
 compile 'com.android.support:design:24.2.1'
```

## TabLayout
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary3.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：[Android Design Support Library之TabLayout](http://wuxiaolong.me/2015/08/03/TabLayout/)

## Navigation drawer
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary4.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：[Android Design Support Library之NavigationView](http://wuxiaolong.me/2015/11/17/NavigationView/)

## CoordinatorLayout
![Android Design Support Library之CoordinatorLayout](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary5.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：[http://wuxiaolong.me/2015/11/17/CoordinatorLayout/](http://wuxiaolong.me/2015/11/17/CoordinatorLayout/)

## SearchView
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary11.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

Toolbar+SearchView使用：
```
private void initSearchView() {
    final SearchView searchView = (SearchView) mToolbar.getMenu()
            .findItem(R.id.menu_search).getActionView();
    searchView.setQueryHint("搜索…");
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            showToast("query=" + query);
            return false;
        }
        @Override
        public boolean onQueryTextChange(String s) {
            LogUtil.d("onQueryTextChange=" + s);
            // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
            return false;
        }
    });
}
```

## 过度动画
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary6.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

需要跳到的地方，写在DetailActivity
```
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
```
需要在DetailActivity写：
```
//设置过渡动画，第一参数就是DetailActivity的共享的元素
ViewCompat.setTransitionName(backdrop, AppConstants.TRANSITION_PIC);
```

## CollapsingToolbarLayout
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary7.gif?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：[Android Design Support Library之CollapsingToolbarLayout](http://wuxiaolong.me/2015/11/17/CollapsingToolbarLayout/)

## TextInputLayout
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary8.jpg?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)
```
 emailTextInput.setError("请输入邮箱");
```

## 夜间模式
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary9.jpg?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：[Android Support Library 之 夜间模式](http://wuxiaolong.me/2016/07/12/appcompatDayNight/)

## 其他组件
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary10.jpg?watermark/2/text/5ZC05bCP6b6Z5ZCM5a24/font/5qW35L2T/fontsize/500/fill/I0VGRUZFRg==/dissolve/100/gravity/SouthEast/dx/10/dy/10)

使用方法：见[源码](https://github.com/WuXiaolong/DesignSupportLibrarySample)

# 源码地址
[https://github.com/WuXiaolong/DesignSupportLibrarySample](https://github.com/WuXiaolong/DesignSupportLibrarySample)，Material design规范文档内容很多，很多东西是要细致推敲，值得产品经理或UI设计师，乃至开发人员好好学习，目前这个sample，Material design风格的效果都有了，相当一个空壳子，您只需在实际开发中塞真实数据就是一个perfect app，个人能力和精力有限，只能分享这么多了，over。

# 更新日志
* 2016/10/17 修改
* 2015/11/06 撰笔

# 微信公众号
我的微信公众号：吴小龙同学。
不止于技术分享，还有诗和远方~
欢迎微信扫一扫关注。
![](http://7q5c2h.com1.z0.glb.clouddn.com/qrcode_wuxiaolong.jpg)
