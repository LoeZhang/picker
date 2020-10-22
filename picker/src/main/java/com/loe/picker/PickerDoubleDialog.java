package com.loe.picker;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.loe.picker.util.OnLoadOkListener;
import com.loe.picker.util.OptionsPickerNetView;
import com.loe.picker.util.PickerBean;

import java.util.ArrayList;
import java.util.List;

public class PickerDoubleDialog
{
    private OptionsPickerNetView pickerView;
    private PickerCallback pickerCallback;
    private int index1 = -1;
    private int index2 = -1;
    private PickerBean bean1;
    private PickerBean bean2;

    private TextView textTail1;
    private String tail1;
    private TextView textTail2;
    private String tail2;

    private TextView textTitle;

    public PickerDoubleDialog(Context context, List<PickerBean> list, PickerCallback callback)
    {
        this(context, null, list, callback);
    }

    public PickerDoubleDialog(Context context, final String title, List<PickerBean> list, PickerCallback callback)
    {
        pickerCallback = callback;
        pickerView = new OptionsPickerNetView(new OptionsPickerNetView.Builder(context)
                .setLayoutRes(R.layout.picker_layout, new CustomListener()
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

                textTail1 = v.findViewById(R.id.textTail1);
                textTail2 = v.findViewById(R.id.textTail2);
                setTail(tail1, tail2);
            }
        })
                .setContentTextSize(16)
                .setTextColorCenter(context.getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(0xffbbbbbb))
        {
            @Override
            public void onSelect(int options1, int options2, int options3, PickerBean item1, PickerBean item2, PickerBean item3)
            {
                try
                {
                    if(pickerCallback.onSelect(options1, options2, item1, item2))
                    {
                        index1 = options1;
                        index2 = options2;
                        bean1 = item1;
                        bean2 = item2;
                        pickerView.dismiss();
                    }
                }catch (Exception e)
                {
                    Log.e("pickerView",e + "");
                }
            }

            @Override
            public void loadList2(int pi1, PickerBean parent, OnLoadOkListener onLoadOkListener)
            {
                pickerCallback.onLoadList2(pi1, parent, onLoadOkListener);
            }

            @Override
            public void loadList3(int pi1, int pi2, PickerBean parent, OnLoadOkListener onLoadOkListener)
            {
            }
        };
        ArrayList<PickerBean> list2 = new ArrayList<>();
        list2.add(new PickerBean(""));
        pickerView.setPicker(list, list2, null);
    }

    public void setTitle(String title)
    {
        textTitle.setText(title);
    }

    public void setTail(String tail1, String tail2)
    {
        this.tail1 = tail1;
        this.tail2 = tail2;
        if(textTail2 != null)
        {
            textTail1.setText(tail1);
            textTail2.setText(tail2);
            textTail1.setVisibility(tail1 == null? View.GONE: View.VISIBLE);
            textTail2.setVisibility(tail2 == null? View.GONE: View.VISIBLE);
        }
    }

    public void select(int index1, int index2)
    {
        pickerView.setSelectOptions(index1, index2);
    }

    public int getIndex1()
    {
        return index1;
    }

    public int getIndex2()
    {
        return index2;
    }

    public PickerBean getBean1()
    {
        return bean1;
    }

    public PickerBean getBean2()
    {
        return bean2;
    }

    public boolean isSelect()
    {
        return index1 >= 0 && index2 > 0;
    }

    public void setPickerCallback(PickerCallback pickerCallback)
    {
        this.pickerCallback = pickerCallback;
    }

    public void show()
    {
        if(!pickerView.isShowing())
        {
            pickerView.show();
        }
    }

    public interface PickerCallback
    {
        boolean onSelect(int index1, int index2, PickerBean bean1, PickerBean bean2);

        void onLoadList2(int pi1, PickerBean parent, OnLoadOkListener onLoadOkListener);
    }
}