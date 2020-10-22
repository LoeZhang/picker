package com.loe.picker.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 相似度识别引擎
 */
public class SameEngine
{
    private String key;
    private ArrayList<String> list;

    public SameEngine()
    {
        this("");
    }

    public SameEngine(String key)
    {
        this.key = key;
        list = new ArrayList<>();
    }

    public SameEngine add(String s)
    {
        list.add(s);
        return this;
    }

    public SameEngine add(List<String> ss)
    {
        list.addAll(ss);
        return this;
    }

    public String getKey()
    {
        return key;
    }

    public SameEngine setKey(String key)
    {
        this.key = key;
        return this;
    }

    public String getSame()
    {
        return getSameBean().name;
    }

    public String getSame(float rateLimit)
    {
        SameBean bean = getSameBean();
        if (bean.sameRate >= rateLimit)
        {
            return bean.name;
        }
        return "";
    }

    public SameBean getSameBean()
    {
        SameBean bean = new SameBean();
        for (int i = 0; i < list.size(); i++)
        {
            String name = list.get(i);
            float rate = getSameRate(key, name);
            if (rate > bean.sameRate)
            {
                bean.i = i;
                bean.name = name;
                bean.sameRate = rate;
            }
        }
        return bean;
    }

    class SameBean
    {
        public int i = -1;
        public String name = "";
        public float sameRate = 0;
    }

    public static float getSameRate(String str, String target)
    {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0 || m == 0)
        {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++)
        { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++)
        { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++)
        { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++)
            {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2)
                {
                    temp = 0;
                }
                else
                {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }

        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
    }
}
