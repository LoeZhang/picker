package com.loe.picker.util;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 网络时间类
 */
public class XTime implements Serializable
{
    private Calendar calendar;

    public XTime()
    {
        this(System.currentTimeMillis());
    }

    public XTime(long millis)
    {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
    }

    public XTime(String s)
    {
        calendar = Calendar.getInstance();
        setTime(s);
    }

    /**
     * 格式化动态时间
     */
    public String formatDynamic()
    {
        StringBuffer sb = new StringBuffer();
        XTime nowTime = new XTime();
        // 如果是今天
        if (isSameDate(nowTime))
        {
            sb.append("今天 ");
        }
        else
        {
            nowTime.addDate(-1);
            // 如果是昨天
            if (isSameDate(nowTime))
            {
                sb.append("昨天 ");
            }
            else
            {
                nowTime.addDate(-1);
                // 如果是前天
                if (isSameDate(nowTime))
                {
                    sb.append("前天 ");
                }
                else
                {
                    // 如果是今年
                    if (new XTime().getYear() == getYear())
                    {
                        // 显示月日
                        sb.append(getMonth() + "月" + getDate() + "日 ");
                    }
                    else
                    {
                        // 显示全部日期
                        sb.append(formatDateCN() + " ");
                    }
                }
            }
        }
        // 加入时、分
        sb.append(getHour() + ":" + (getMinute() < 10 ? "0" + getMinute() : getMinute()));
        return sb.toString();
    }

    /**
     * 格式化显示
     */
    public String format(String code)
    {
        return formatDate(code) + " " + formatTime();
    }

    /**
     * 格式化显示
     */
    public String formatStatic(String code)
    {
        return formatStaticDate(code) + " " + formatStaticTime();
    }

    /**
     * 格式化显示日期
     */
    public String formatDate(String code)
    {
        return getYear() + code + getMonth() + code + getDate();
    }

    /**
     * 格式化显示日期
     */
    public String formatStaticDate(String code)
    {
        return getYear() + code + (getMonth() > 9 ? "" : "0") + getMonth() + code + (getDate() > 9 ? "" : "0") + getDate();
    }

    /**
     * 格式化显示汉字日期
     */
    public String formatDateCN()
    {
        return getYear() + "年" + getMonth() + "月" + getDate() + "日";
    }

    /**
     * 格式化显示时间
     */
    public String formatTime()
    {
        return String.format("%d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    /**
     * 格式化显示时间
     */
    public String formatStaticTime()
    {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    /**
     * 格式化显示时间
     */
    public String formatTimeMS()
    {
        return String.format("%02d:%02d", getMinute(), getSecond());
    }

    /**
     * 格式化显示时段时间
     */
    public String formatPeriodTime()
    {
        String period;
        int h = getHour();
        if (h >= 0 && h < 6)
        {
            period = "凌晨";
        }
        else
        {
            if (h < 11)
            {
                period = "早晨";
            }
            else
            {
                if (h < 14)
                {
                    period = "中午";
                }
                else
                {
                    if (h < 17)
                    {
                        period = "下午";
                    }
                    else
                    {
                        period = "晚上";
                    }
                }
            }
        }
        return period + " " + getHour() + ":" + (getMinute() < 10 ? "0" + getMinute() :
                getMinute());
    }

    /**
     * 是否是相同的一天
     */
    public boolean isSameDate(XTime netTime)
    {
        return netTime.getYear() == getYear() && netTime.getMonth() == getMonth() && netTime.getDate() == getDate();
    }

    /**
     * 某月有多少天
     */
    public static int computingMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 设置时间戳
     */
    public XTime setTime(long millis)
    {
        calendar.setTimeInMillis(millis);
        return this;
    }

    /**
     * 设置时间字符串
     */
    public XTime setTime(String s)
    {
        try
        {
            String[] ss = s.split(" ");
            String[] ds = ss[0].split("[-.]");
            setYMD(Integer.parseInt(ds[0]), Integer.parseInt(ds[1]), Integer.parseInt(ds[2]));
            setMillis(0);
            if (ss.length > 1)
            {
                String[] ts = ss[1].split(":");
                setHMS(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]), Integer.parseInt(ts[2]));
            }
            else
            {
                setHMS(0, 0, 0);
            }
        }catch (Exception e)
        {
        }
        return this;
    }

    /**
     * 增加年数
     */
    public XTime addYear(int n)
    {
        setYear(getYear() + n);
        return this;
    }

    /**
     * 增加月数
     */
    public XTime addMonth(int n)
    {
        setMonth(getMonth() + n);
        return this;
    }

    /**
     * 增加天数
     */
    public XTime addDate(int n)
    {
        setDate(getDate() + n);
        return this;
    }

    /**
     * 增加小时
     */
    public XTime addHour(int n)
    {
        setHour(getHour() + n);
        return this;
    }

    /**
     * 增加分钟
     */
    public XTime addMinute(int n)
    {
        setMinute(getMinute() + n);
        return this;
    }

    /**
     * 增加秒
     */
    public XTime addSecond(int n)
    {
        setSecond(getSecond() + n);
        return this;
    }

    /**
     * 增加毫秒
     */
    public XTime addMillis(int n)
    {
        setMillis(getMillis() + n);
        return this;
    }

    public int subDate(XTime time)
    {
        return (int)((toTimeMillis() - time.toTimeMillis()) / 86400000);
    }

    public int subMonth(XTime time)
    {
        int dd = getDate() - time.getDate();
        int dm =
                getMonth() - time.getMonth() - (dd < 0 ? 1 : 0) + (getYear() - time.getYear()) * 12;
        return dm;
    }

    public int subYear(XTime time)
    {
        return subMonth(time) / 12;
    }

    public int toNowDate()
    {
        return new XTime().subDate(this);
    }

    public int toNowMonth()
    {
        return new XTime().subMonth(this);
    }

    public int toNowYear()
    {
        return new XTime().subYear(this);
    }

    public int getYear()
    {
        return calendar.get(Calendar.YEAR);
    }

    public String getSimpleYear()
    {
        int y = getYear() % 100;
        return y < 9 ? "0" : "" + y;
    }

    public int getMonth()
    {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getDate()
    {
        return calendar.get(Calendar.DATE);
    }

    public int getHour()
    {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute()
    {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond()
    {
        return calendar.get(Calendar.SECOND);
    }

    public int getMillis()
    {
        return calendar.get(Calendar.MILLISECOND);
    }

    public int getHmsSecond()
    {
        return getSecond() + getMinute() * 60 + getHour() * 3600;
    }

    public XTime setYear(int year)
    {
        calendar.set(Calendar.YEAR, year);
        return this;
    }

    public XTime setMonth(int month)
    {
        calendar.set(Calendar.MONTH, month - 1);
        return this;
    }

    public XTime setDate(int date)
    {
        calendar.set(Calendar.DATE, date);
        return this;
    }

    public XTime setHour(int hour)
    {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    public XTime setMinute(int minute)
    {
        calendar.set(Calendar.MINUTE, minute);
        return this;
    }

    public XTime setSecond(int second)
    {
        calendar.set(Calendar.SECOND, second);
        return this;
    }

    public XTime setMillis(int millis)
    {
        calendar.set(Calendar.MILLISECOND, millis);
        return this;
    }

    public XTime setYMD(int year, int month, int date)
    {
        setYear(year);
        setMonth(month);
        setDate(date);
        return this;
    }

    public XTime setHMS(int hour, int minute, int second)
    {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        return this;
    }

    @Override
    public String toString()
    {
        return calendar.getTimeInMillis() + "";
    }

    /**
     * 输出为时间戳
     */
    public long toTimeMillis()
    {
        return calendar.getTimeInMillis();
    }

    /**
     * 输出为日历表
     */
    public Calendar toCalendar()
    {
        return calendar;
    }
}