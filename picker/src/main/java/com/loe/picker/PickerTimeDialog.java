package com.loe.picker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.loe.picker.util.XTime;

import java.util.Date;

public class PickerTimeDialog
{
    private TimePickerView pickerView;

    private TextView textTitle;

    private PickerTimeCallback callback;

    public PickerTimeDialog(Context context, final PickerTimeCallback callback)
    {
        this(context, Type.DATE, "", callback);
    }

    public PickerTimeDialog(Context context, String title, PickerTimeCallback callback)
    {
        this(context, Type.DATE, title, callback);
    }

    public PickerTimeDialog(Context context, Type type, PickerTimeCallback callback)
    {
        this(context, type, "", callback);
    }

    public PickerTimeDialog(Context context, final Type type, final String title,
                            PickerTimeCallback callback)
    {
        this.callback = callback;
        pickerView = new TimePickerView(new TimePickerView.Builder(context,
                new TimePickerView.OnTimeSelectListener()
        {
            @Override
            public void onTimeSelect(Date date, View v)
            {
                PickerTimeDialog.this.callback.ok(new XTime(date.getTime()));
            }
        }).setLayoutRes(type.layoutId, new CustomListener()
        {
            @Override
            public void customLayout(View v)
            {
                v.findViewById(R.id.text_ok).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        pickerView.returnData();
                        pickerView.dismiss();
                    }
                });

                v.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        pickerView.dismiss();
                    }
                });
                textTitle = (TextView) v.findViewById(R.id.text_title);
                textTitle.setText(title == null ? "" : title);
            }
        })
                .setContentSize(16)
                .setType(type.types)
                .setLabel("", "", "", "", "", "")
                .setTextColorCenter(context.getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(0xffbbbbbb));

    }

    public void setTitle(String title)
    {
        textTitle.setText(title);
    }

    public void refreshTime()
    {
        setTime(new XTime());
    }

    public void clearTime()
    {
        setTime(new XTime().setDate(1).setHour(0).setMinute(0).setSecond(0));
    }

    public void setTime(XTime time)
    {
        pickerView.setDate(time.toCalendar());
    }

    public void show()
    {
        pickerView.show();
    }

    public enum Type
    {
        DATE_TIME(R.layout.picker_date_time_layout, new boolean[]{true, true, true, true, true,
                true}), DATE(R.layout.picker_date_layout, new boolean[]{true, true, true, false,
            false, false}), TIME(R.layout.picker_time_layout, new boolean[]{false, false, false,
            true, true, true});

        Type(int layoutId, boolean[] types)
        {
            this.layoutId = layoutId;
            this.types = types;
        }

        private int layoutId;
        private boolean[] types;

    }

    public void setPickerCallback(PickerTimeCallback callback) {
        this.callback = callback;
    }

    public interface PickerTimeCallback
    {
        void ok(XTime time);
    }
}
