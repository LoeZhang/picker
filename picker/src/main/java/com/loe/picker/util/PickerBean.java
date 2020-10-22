package com.loe.picker.util;

public class PickerBean
{
    private String code;
    private String name;
    private String data;
    private boolean isSelect = false;

    public PickerBean(String code)
    {
        setCode(code);
        setName(code);
    }

    public PickerBean(String code, String name)
    {
        setCode(code);
        setName(name);
    }

    public PickerBean(String code, String name, String data)
    {
        setCode(code);
        setName(name);
        setData(data);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isSelect()
    {
        return isSelect;
    }

    public void setSelect(boolean select)
    {
        isSelect = select;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
