package me.crazyjiang.crazyread.presenter.contract;

import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.crazyjiang.crazyread.ui.BaseView;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public interface DailyContract {
    interface View extends BaseView {
        void onNewsLoaded(DailyStoriesBean dailyStoriesBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getLatestNews();
    }
}
