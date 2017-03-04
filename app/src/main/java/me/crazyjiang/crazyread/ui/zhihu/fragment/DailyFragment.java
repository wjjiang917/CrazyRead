package me.crazyjiang.crazyread.ui.zhihu.fragment;

import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.presenter.DailyPresenter;
import me.crazyjiang.crazyread.presenter.contract.DailyContract;
import me.crazyjiang.crazyread.ui.BaseFragment;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void init() {
        mPresenter.getLatestNews();
    }

    @Override
    public void onLatestNewsLoaded(DailyStoriesBean dailyStoriesBean) {

    }
}
