package me.crazyjiang.crazyread.presenter.contract;

import me.crazyjiang.crazyread.model.bean.ZhiHuNewsBean;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.crazyjiang.crazyread.ui.BaseView;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public interface ZhiHuNewsContract {
    interface View extends BaseView {
        void onNewsDetailLoaded(ZhiHuNewsBean zhiHuNewsBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsDetail(Integer id);
    }
}
