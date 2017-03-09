package me.crazyjiang.crazyread.ui.netease.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.common.Constant;
import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import me.crazyjiang.crazyread.presenter.NetEaseVideosPresenter;
import me.crazyjiang.crazyread.presenter.contract.NetEaseVideosContract;
import me.crazyjiang.crazyread.ui.BaseFragment;
import me.crazyjiang.crazyread.ui.zhihu.adapter.NetEaseVideosAdapter;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public class NetEaseVideosFragment extends BaseFragment<NetEaseVideosPresenter> implements NetEaseVideosContract.View {
    @BindView(R.id.rv_video_list)
    RecyclerView rvVideoList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private NetEaseVideosAdapter mAdapter;
    private List<NetEaseVideoBean> mVideos = new ArrayList<>();
    private String categoryId;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_netease_videos;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            categoryId = getArguments().getString(Constant.BUNDLE_CATEGORY_ID);
        }

        mAdapter = new NetEaseVideosAdapter(mVideos);
        mPresenter.getVideoList(categoryId, 0);
    }

    @Override
    public void onVideosLoaded(Map<String, List<NetEaseVideoBean>> videos) {
        mVideos = videos.get(categoryId);
        mAdapter.setNewData(mVideos);
        mAdapter.notifyDataSetChanged();
    }
}
