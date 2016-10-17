# Android Design Support Library Sample
Android Design Support Library使用
谷歌出Material design规范很久了，但是市面上的Material design风格的APP却很少，这个规范应该产品经理或UI设计师应该熟知于心，官网地址：[https://material.google.com/](https://material.google.com/)，不过我这里分享开发可能会使用的知识点和注意点，有详细sample哦。
# 样式
## 色彩
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary1.jpg)
如图，工具栏和大色块适合使用饱和度 500 的基础色，这也是你应用的主要颜色。状态栏适合使用更深一些的饱和度 700 的基础色。更多Color见：[Style– Color](https://material.google.com/style/color.html)

## 字体标准样式
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary2.jpg)
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
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary3.gif)
相关博客：[Android Design Support Library之TabLayout](http://wuxiaolong.me/2015/08/03/TabLayout/)

## Navigation drawer
![](http://7q5c2h.com1.z0.glb.clouddn.com/DesignSupportLibrary4.jpg)
相关博客：[Android Design Support Library之NavigationView](http://wuxiaolong.me/2015/11/17/NavigationView/)

## 效果预览
![](https://github.com/WuXiaolong/DesignSupportLibrarySample/raw/master/screenshots/designsupportlibrarysample.gif)

## 相关博客

[Android Support Library 之 夜间模式](http://wuxiaolong.me/2016/07/12/appcompatDayNight/)

[Android Design Support Library之NavigationView](http://wuxiaolong.me/2015/11/17/NavigationView/)

[Android Design Support Library之CoordinatorLayout](http://wuxiaolong.me/2015/11/17/CoordinatorLayout/)

[Android Design Support Library之CollapsingToolbarLayout](http://wuxiaolong.me/2015/11/17/CollapsingToolbarLayout/)

[Android Design Support Library之TabLayout](http://wuxiaolong.me/2015/08/03/TabLayout/)

[RecyclerView+CardView使用](http://wuxiaolong.me/2015/09/15/RecyclerViewCardView/)

[RecyclerView下拉刷新和上拉加载更多以及瀑布流效果](https://github.com/WuXiaolong/PullLoadMoreRecyclerView)

# 微信公众号
我的微信公众号：吴小龙同学

![](http://7q5c2h.com1.z0.glb.clouddn.com/qrcode_wuxiaolong.jpg)

# 关于作者
[点击查看](http://wuxiaolong.me/about/)

