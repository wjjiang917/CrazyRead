package me.crazyjiang.crazyread.ui.netease.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.common.Constant;
import me.crazyjiang.crazyread.ui.SimpleFragment;
import me.crazyjiang.crazyread.ui.ViewPagerAdapter;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */
public class NetEaseFragment extends SimpleFragment {
    private final String[] VIDEO_TITLE = new String[]{"热点", "搞笑", "娱乐", "精品"};
    private final String[] CATEGORY_ID = new String[]{"V9LG4B3A0", "V9LG4E6VR", "V9LG4CHOR", "00850FRB"};

    @BindView(R.id.tab_netease)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_netease)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_netease;
    }

    @Override
    protected void init() {
        // init pages
        NetEaseVideosFragment fragment;
        Bundle bundle;
        for (int i = 0; i < CATEGORY_ID.length; i++) {
            fragment = new NetEaseVideosFragment();
            bundle = new Bundle();
            bundle.putString(Constant.BUNDLE_CATEGORY_ID, CATEGORY_ID[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(VIDEO_TITLE[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(VIDEO_TITLE[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(VIDEO_TITLE[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(VIDEO_TITLE[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(VIDEO_TITLE[0]);
        mTabLayout.getTabAt(1).setText(VIDEO_TITLE[1]);
        mTabLayout.getTabAt(2).setText(VIDEO_TITLE[2]);
        mTabLayout.getTabAt(3).setText(VIDEO_TITLE[3]);
    }
}
