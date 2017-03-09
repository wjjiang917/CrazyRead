package me.crazyjiang.crazyread.presenter.contract;

import java.util.List;
import java.util.Map;

import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.crazyjiang.crazyread.ui.BaseView;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public interface NetEaseVideosContract {
    interface View extends BaseView {
        void onVideosLoaded(Map<String, List<NetEaseVideoBean>> videos);
    }

    interface Presenter extends BasePresenter<View> {
        void getVideoList(String categoryId, int startPage);
    }
}
