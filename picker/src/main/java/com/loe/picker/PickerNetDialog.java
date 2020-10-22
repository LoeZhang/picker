package com.loe.picker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.loe.picker.util.OnLoadOkListener;
import com.loe.picker.util.PickerBean;
import com.loe.picker.util.SameEngine;

import java.util.List;

public class PickerNetDialog
{
    private OptionsPickerView pickerView;
    private PickerCallback pickerCallback;
    private List<PickerBean> mList;
    private int index = -1;
    private PickerBean bean;

    private TextView textTitle;
    private String title;

    private boolean isLoad;

    private Context context;

    public PickerNetDialog(Context context, PickerCallback callback)
    {
        this(context, null, callback);
    }

    public PickerNetDialog(Context context, final String title, PickerCallback callback)
    {
        this.context = context;
        this.title = title;
        pickerCallback = callback;
        loadData(false);
    }

    private void init(List<PickerBean> list)
    {
        mList = list;
        pickerView = new OptionsPickerView(new OptionsPickerView.Builder(context, new
                OptionsPickerView.OnOptionsSelectListener()
                {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v)
                    {
                        index = options1;
                        bean = mList.get(options1);
                        pickerCallback.onSelect(options1, bean);
                    }
                }).setLayoutRes(R.layout.picker_layout, new CustomListener()
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
                .setContentTextSize(16)
                .setTextColorCenter(context.getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(0xffbbbbbb));
        pickerView.setPicker(list);
    }

    private void loadData(final boolean isShow)
    {
        if(!isLoad)
        {
            if(isShow) isLoad = true;
            pickerCallback.onLoadList(new OnLoadOkListener()
            {
                @Override
                public void onLoadOk(List<PickerBean> list)
                {
                    isLoad = false;
                    if(list != null)
                    {
                        init(list);
                        if(isShow)
                        {
                            show();
                        }
                    }
                }
            });
        }
    }

    public void setList(List<PickerBean> list)
    {
        index = -1;
        bean = null;
        mList = list;
        pickerView.setPicker(list);
    }

    public void setTitle(String title)
    {
        this.title = title;
        textTitle.setText(title);
    }

    public int getIndex()
    {
        return index;
    }

    public void select(int index)
    {
        if (index < mList.size())
        {
            this.index = index;
            bean = mList.get(index);
            pickerView.setSelectOptions(index);
            pickerCallback.onSelect(index, mList.get(index));
        }
    }

    public void selectCode(String code)
    {
        for (int i = 0; i < mList.size(); i++)
        {
            PickerBean bean = mList.get(i);
            if(code.equals(bean.getCode()))
            {
                select(i);
            }
        }
    }

    public void selectName(String name)
    {
        for (int i = 0; i < mList.size(); i++)
        {
            PickerBean bean = mList.get(i);
            if(name.equals(bean.getName()))
            {
                select(i);
            }
        }
    }

    /**
     * 模糊匹配名称选择
     */
    public void selectSameName(String sameName)
    {
        SameEngine engine = new SameEngine(sameName);
        for (PickerBean bean : mList)
        {
            engine.add(bean.getName());
        }
        selectName(engine.getSame(15f));
    }

    public boolean hasCode(String code)
    {
        for (int i = 0; i < mList.size(); i++)
        {
            PickerBean bean = mList.get(i);
            if(code.equals(bean.getCode()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean hasName(String name)
    {
        for (int i = 0; i < mList.size(); i++)
        {
            PickerBean bean = mList.get(i);
            if(name.equals(bean.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSelect()
    {
        return index >= 0;
    }

    public void show()
    {
        if(pickerView != null)
        {
            if(!pickerView.isShowing())
            {
                pickerView.show();
            }
        }else
        {
            loadData(true);
        }
    }

    public void setPickerCallback(PickerCallback pickerCallback)
    {
        this.pickerCallback = pickerCallback;
    }

    public interface PickerCallback
    {
        void onSelect(int index, PickerBean bean);

        void onLoadList(OnLoadOkListener onLoadOkListener);
    }
}