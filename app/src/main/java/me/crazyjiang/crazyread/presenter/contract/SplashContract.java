package me.crazyjiang.crazyread.presenter.contract;

import me.crazyjiang.crazyread.model.bean.StartImageBean;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.crazyjiang.crazyread.ui.BaseView;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public interface SplashContract {
    interface View extends BaseView {
        void onSuccess(StartImageBean startImageBean);

        void onFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void getWelcomeData();
    }
}
