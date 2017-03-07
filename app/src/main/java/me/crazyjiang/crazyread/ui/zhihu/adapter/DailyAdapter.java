package me.crazyjiang.crazyread.ui.zhihu.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.util.ImageUtil;

/**
 * Created by Jiangwenjin on 2017/3/7.
 */
public class DailyAdapter extends BaseItemDraggableAdapter<DailyStoriesBean.StoryBean, BaseViewHolder> {
    public DailyAdapter(List<DailyStoriesBean.StoryBean> data) {
        super(R.layout.item_daily, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyStoriesBean.StoryBean item) {
        helper.setText(R.id.tv_daily_item_title, item.getTitle());
        helper.setTextColor(R.id.tv_daily_item_title, ContextCompat.getColor(mContext, R.color.news_unread));
        ImageUtil.load(mContext, item.getImages().get(0), (ImageView) helper.getView(R.id.iv_daily_item_image));
    }
}
