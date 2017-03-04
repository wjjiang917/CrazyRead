package me.crazyjiang.crazyread.util;

import android.app.Activity;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
public class TipUtil {
    public static void showSnackbar(Activity activity, String msg) {
        new SuperActivityToast(activity)
                .setText(msg)
                .setDuration(Style.DURATION_SHORT)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }

    public static void showToast(Activity activity, String msg) {
        new SuperActivityToast(activity)
                .setText(msg)
                .setDuration(Style.DURATION_SHORT)
                .setFrame(Style.FRAME_KITKAT)
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }
}
