package me.crazyjiang.crazyread.ui.live.fragment;

import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.ui.SimpleFragment;
import me.crazyjiang.crazyread.util.Logger;
import me.crazyjiang.crazyread.widget.media.PlayerManager;

/**
 * Created by Jiangwenjin on 2017/3/11.
 */

public class LiveStreamFragment extends SimpleFragment implements PlayerManager.PlayerStateListener {
//    @BindView(R.id.video_view)
//    IjkVideoView mVideoView;

    private static final String URI = "http://tppull.kktv8.com/livekktv/2015040.flv";
    private PlayerManager player;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_live_stream;
    }

    @Override
    protected void init() {
//        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView.setVideoURI(Uri.parse("http://tppull.kktv8.com/livekktv/2015040.flv"));
//        mVideoView.start();
        player = new PlayerManager(mActivity);
        player.live(true);
        player.setFullScreenOnly(false);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(false);
        player.setPlayerStateListener(this);
        player.play(URI);
    }

    @Override
    public void onComplete() {
        Logger.d("onComplete...");
    }

    @Override
    public void onError() {
        Logger.d("onError...");
    }

    @Override
    public void onLoading() {
        Logger.d("onLoading...");
    }

    @Override
    public void onPlay() {
        Logger.d("onPlay...");
    }
}
