package com.loe.picker.util;

import com.bigkoo.pickerview.adapter.WheelAdapter;

import java.util.List;

/**
 * The simple Array wheel adapter
 */
public class CodeWheelAdapter implements WheelAdapter
{

    /** The default items length */
    public static final int DEFAULT_LENGTH = 4;

    // items
    private List<PickerBean> items;
    // length
    private int length;

    /**
     * Constructor
     * @param items the items
     * @param length the max items length
     */
    public CodeWheelAdapter(List<PickerBean> items, int length) {
        this.items = items;
        this.length = length;
    }

    /**
     * Contructor
     * @param items the items
     */
    public CodeWheelAdapter(List<PickerBean> items) {
        this(items, DEFAULT_LENGTH);
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).getName();
        }
        return "";
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    @Override
    public int indexOf(Object o){
        for(int i = 0;i<items.size();i++)
        {
            if(o == items.get(i).getName())
            {
                return i;
            }
        }
        return items.indexOf(o);
    }

}
