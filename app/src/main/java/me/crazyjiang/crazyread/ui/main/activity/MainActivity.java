package me.crazyjiang.crazyread.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import javax.inject.Inject;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.common.Constant;
import me.crazyjiang.crazyread.presenter.MainPresenter;
import me.crazyjiang.crazyread.presenter.contract.MainContract;
import me.crazyjiang.crazyread.ui.BaseActivity;
import me.crazyjiang.crazyread.ui.zhihu.fragment.ZhiHuFragment;
import me.crazyjiang.crazyread.util.SPUtil;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.view_search)
    MaterialSearchView mSearchView;

    @Inject
    ZhiHuFragment zhiHuFragment;

    private int hideFragment = Constant.TYPE_ZHIHU;
    private int showFragment = Constant.TYPE_ZHIHU;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SPUtil.setNightModeState(false);
        } else {
            showFragment = SPUtil.getCurrentPage();
            hideFragment = Constant.TYPE_ZHIHU;
            showHideFragment(getFragment(showFragment), getFragment(hideFragment));
            mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
            mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentResId(showFragment)).getTitle().toString());
            hideFragment = showFragment;
        }
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void init() {
        setToolBar(mToolbar, "知乎日报");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // load fragments
        loadMultipleRootFragment(R.id.layout_main_content, 0, zhiHuFragment);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_zhihu:
                        break;
                    case R.id.drawer_netease_video:
                        break;
                }
                return false;
            }
        });
    }

    private SupportFragment getFragment(int pageType) {
        switch (pageType) {
            case Constant.TYPE_ZHIHU:
                return zhiHuFragment;
        }
        return zhiHuFragment;
    }

    private int getCurrentResId(int pageType) {
        switch (pageType) {
            case Constant.TYPE_ZHIHU:
                return R.id.drawer_zhihu;
        }
        return R.id.drawer_zhihu;
    }
}
