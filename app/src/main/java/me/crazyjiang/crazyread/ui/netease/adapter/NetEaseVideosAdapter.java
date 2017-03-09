package me.crazyjiang.crazyread.ui.netease.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import me.crazyjiang.crazyread.util.ImageUtil;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public class NetEaseVideosAdapter extends BaseQuickAdapter<NetEaseVideoBean, BaseViewHolder> {
    public NetEaseVideosAdapter(List<NetEaseVideoBean> data) {
        super(R.layout.item_netease_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetEaseVideoBean item) {
        helper.setText(R.id.video_title, item.getTitle());
        ImageUtil.load(mContext, item.getCover(), (ImageView) helper.getView(R.id.video_image));
    }
}
