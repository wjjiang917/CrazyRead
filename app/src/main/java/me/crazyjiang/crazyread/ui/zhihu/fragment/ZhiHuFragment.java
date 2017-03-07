package me.crazyjiang.crazyread.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.ui.SimpleFragment;
import me.crazyjiang.crazyread.ui.zhihu.adapter.ZhiHuAdapter;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public class ZhiHuFragment extends SimpleFragment {
    @BindView(R.id.tab_zhihu)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_zhihu)
    ViewPager mViewPager;

    private ZhiHuAdapter mAdapter;
    private String[] tabTitles = new String[]{"日报"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void init() {
        fragments.add(new DailyFragment());

        mAdapter = new ZhiHuAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitles[0]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitles[0]);
    }
}
