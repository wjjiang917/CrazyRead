package me.crazyjiang.crazyread.ui.netease.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

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
import me.crazyjiang.crazyread.ui.netease.activity.VideoPlayerActivity;
import me.crazyjiang.crazyread.ui.netease.adapter.NetEaseVideosAdapter;

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
    private boolean loadMore = false;
    private int startPage = 0;

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
        rvVideoList.setLayoutManager(new LinearLayoutManager(mContext));
        rvVideoList.setAdapter(mAdapter);
        rvVideoList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                intent.putExtra(Constant.INTENT_EXTRA_VIDEO, mVideos.get(position));
                mContext.startActivity(intent);
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore = true;
                mPresenter.getVideoList(categoryId, startPage);
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMore = false;
                startPage = 0;
                mPresenter.getVideoList(categoryId, startPage);
            }
        });

        mPresenter.getVideoList(categoryId, startPage);
    }

    @Override
    public void onVideosLoaded(Map<String, List<NetEaseVideoBean>> videos) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }

        mVideos = videos.get(categoryId);
        startPage = mVideos.size();
        if (loadMore) {
            mAdapter.addData(videos.get(categoryId));
        } else {
            mAdapter.setNewData(videos.get(categoryId));
        }
    }
}
