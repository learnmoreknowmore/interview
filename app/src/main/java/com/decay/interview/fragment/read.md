1.getSupportFragmentManager ， getParentFragmentManager 和 getChildFragmentManager
    在activity使用viewPager以及bottomSheetFragment和dialogFragment使用getSupportFragment,
    在fragment中使用viewPager使用getChildFragment.
    错误的在fragment中使用activity的fragmentManager会导致内存泄漏?
    假如你的fragment中有一些依赖viewPager管理的fragment,这些fragment都在activity,错误使用activity的fragment,关闭父fragment，
    它将被关闭但是不会被销毁因为子都处于活动状态，仍存在于内存中导致泄漏，无法被清除
2.FragmentStateAdapter 和 FragmentPagerAdapter
    fragmentStateAdapter只会保存片段，在失去焦点时会销毁所有的fragment,fragmentPagerAdapter会保存viewPager里面所有fragment的状态，会增加内存开销
    一般fragment少于5个时使用fragmentPagerAdapter，存在很多的fragment时最好使用fragmentStateAdapter
3.add 和 replace
4.observe LiveData时传入 this 还是 viewLifecycleOwner
5.使用 simpleName 作为 fragment 的 tag 有何风险？
6.在 BottomBarNavigation 和 drawer 中如何使用Fragment多次添加？
7、返回栈
//
Fragment 有一个非常强大的功能——就是可以在 Activity 重新创建时可以不完 全销毁 Fragment，以便 Fragment 可以恢复。
在 onCreate()方法中调用 setRetainInstance(true/false)方法是最佳位置。
当 Fragment 恢复时的生命周 期如上图所示，注意图中的红色箭头。当在 onCreate()方法中调用了 setRetainInstance(true)后，
Fragment 恢复时会跳过 onCreate()和 onDestroy() 方法，因此不能在 onCreate()中放置一些初始化逻辑，切忌