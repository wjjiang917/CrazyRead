package me.crazyjiang.crazyread.ui.zhihu.fragment;

import android.graphics.Canvas;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.presenter.DailyPresenter;
import me.crazyjiang.crazyread.presenter.contract.DailyContract;
import me.crazyjiang.crazyread.ui.BaseFragment;
import me.crazyjiang.crazyread.ui.zhihu.adapter.DailyAdapter;
import me.crazyjiang.crazyread.util.DateUtil;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {
    @BindView(R.id.rv_daily_list)
    RecyclerView rvDailyList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;

    @BindDimen(R.dimen.slider_height)
    int sliderHeight;

    private SliderLayout sliderLayout;
    private TextView tvDailyDate;
    private LinearLayout parentLayout;

    private String currentDate;
    private DailyAdapter mAdapter;
    private List<DailyStoriesBean.StoryBean> mStories = new ArrayList<>();

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
        currentDate = DateUtil.getToday("yyyyMMdd");
        mAdapter = new DailyAdapter(mStories);

        // layout for header (slider and date)
        parentLayout = new LinearLayout(mContext);
        parentLayout.setOrientation(LinearLayout.VERTICAL);

        // slider
        sliderLayout = new SliderLayout(mContext);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
        sliderLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, sliderHeight));
        sliderLayout.setVisibility(View.GONE);
        parentLayout.addView(sliderLayout);

        // date
        tvDailyDate = new TextView(mContext);
        tvDailyDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvDailyDate.setBackgroundResource(R.color.colorBackground);
        tvDailyDate.setPadding(16, 16, 16, 16);
        tvDailyDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        parentLayout.addView(tvDailyDate);

        mAdapter.addHeaderView(parentLayout);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);

        // swipe and drag
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rvDailyList);

        // open drag
        mAdapter.enableDragItem(itemTouchHelper, R.id.iv_daily_item, true);
        mAdapter.setOnItemDragListener(onItemDragListener);

        // open slide to delete
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);

        // get today news
        mPresenter.getLatestNews();

        // refresh
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (DateUtil.getToday("yyyyMMdd").equals(currentDate)) {
                    mPresenter.getLatestNews();
                } else {

                }
            }
        });
    }

    @Override
    public void onLatestNewsLoaded(DailyStoriesBean dailyStoriesBean) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }

        // check if it's today
        if (DateUtil.getToday("yyyyMMdd").equals(dailyStoriesBean.getDate())) {
            // show slider
            sliderLayout.removeAllSliders();
            if (null != dailyStoriesBean.getTop_stories() && !dailyStoriesBean.getTop_stories().isEmpty()) {
                TextSliderView textSliderView;
                for (DailyStoriesBean.TopStoryBean topStoryBean : dailyStoriesBean.getTop_stories()) {
                    textSliderView = new TextSliderView(mContext);
                    textSliderView.description(topStoryBean.getTitle()).image(topStoryBean.getImage());
                    sliderLayout.addSlider(textSliderView);
                }

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderLayout.setDuration(4000);
                sliderLayout.setVisibility(View.VISIBLE);
            } else {
                sliderLayout.setVisibility(View.GONE);
            }

            tvDailyDate.setText("今日热闻");
        } else {
            sliderLayout.setVisibility(View.GONE);
            tvDailyDate.setText(dailyStoriesBean.getDate());
        }

        mAdapter.setNewData(dailyStoriesBean.getStories());
        mAdapter.notifyDataSetChanged();
    }

    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        }
    };

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {

        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };
}
