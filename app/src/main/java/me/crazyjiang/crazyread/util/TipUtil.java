package me.crazyjiang.crazyread.util;

import android.app.Activity;
import android.graphics.Color;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import me.crazyjiang.crazyread.App;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
public class TipUtil {
    public static void showSnackbar(Activity activity, String msg) {
        SuperActivityToast.create(activity, new Style(), Style.TYPE_BUTTON)
                .setProgressBarColor(Color.WHITE)
                .setText(msg)
                .setDuration(Style.DURATION_SHORT)
                .setFrame(Style.FRAME_KITKAT)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_INDIGO))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }

    public static void showToast(String msg) {
        SuperToast.create(App.getInstance(), msg, Style.DURATION_SHORT).show();
    }
}
