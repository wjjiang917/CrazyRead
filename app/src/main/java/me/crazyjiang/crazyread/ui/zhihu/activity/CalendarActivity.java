package me.crazyjiang.crazyread.ui.zhihu.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.common.RxBus;
import me.crazyjiang.crazyread.ui.SimpleActivity;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public class CalendarActivity extends SimpleActivity {
    @BindView(R.id.view_calender)
    MaterialCalendarView mCalender;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CalendarDay mDate;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_calender;
    }

    @Override
    protected void init() {
        setToolBar(mToolbar, "选择日期");
        mCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.today())
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @OnClick(R.id.tv_calender_enter)
    void clickConfirm() {
        RxBus.getInstance().post(mDate);
        finish();
    }
}
