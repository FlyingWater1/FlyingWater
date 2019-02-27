# FlyingWater
自己写的一个小demo，使用到的技术 aop（aspectj）+ apt + mvp + rxjava + dagger2 + retrofit + javapoet 
### 1.实现功能
#### 1.一行注解申请权限
#### 2.使用代理模式实现网络图片加载框架的转换（如Glide变picasso），网络请求框架的修改（进行中）
#### 3.通过注解实现输入列表（左边一个属性名，右边是输入框或者选择器）的快速搭建（基本完成，暂时不再优化）
#### 3.1 目前缺陷：只能是一种布局方式，不能自定义其他布局方式，以后可能考虑SuperTextView
#### 3.2 暂未完成：item显示类型，是否有粗/细线，自定义粗细线布局，自定义item布局（只能是一种布局方式）
#### 4.RecyclerView 与 ListView ，GridView 等 AbsListView （凡是可以设置adapter为BaseAdapter的控件）通用的CommonAdapter 以及CommonViewHolder
#### 5.将T_MVP工程中的javassist相关代码（ OkBus方面的）整合到自己的工程中，代码完全复制的[T-MVP](https://github.com/north2016/T-MVP)，没有改动，其中发现的问题
##### 5.1 必须依赖上realm，否则会报错，不知是什么原因 ， [点这里](https://github.com/north2016/T-MVP/issues/39)
##### 5.2 OkBus.getInstance().onEvent()方法写在onCreate方法中无效，原因是因为生成的class文件中register方法在onEvent方法之后，改变BusHelper的代码使onEvent在register之后调用即可，[点这里](https://github.com/north2016/T-MVP/issues/40)，可以将BusHelper的第56行insertAfter改为insertBefore，这里我这里没有进行修改
### 2.后期计划加入的功能
#### 1.生成mvp代码的工具（idea开发的插件或者Template）
#### 2.自己写的数据库框架
#### 3.jni（计划中）
#### 4.换肤
  - [参考资料1文章](https://www.jianshu.com/p/29ae23b13808)
  - [参考资料1源码](https://github.com/inPeige/skin)
  - [参考资料2源码](https://github.com/EdisonChang33/SkinManager)
#### 5.安卓进程间通信，进程保活
#### 6.音视频
#### 7.React Native
#### 8.一些高级ui自定义View
#### 9.插件化
#### 10.md风格
### 3.参考的工程
- [T-MVP](https://github.com/north2016/T-MVP)
- [Android-ZBLibrary](https://github.com/TommyLemon/Android-ZBLibrary)
- [butterknife](https://github.com/JakeWharton/butterknife)
