package com.cj.bx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 白象开发的接口类
 */
public class MethodUtils {

    public static void main(String[] args) {
        getYearAndMonth();

        List<Date> bizDate = getBizDate(2020, "06");
        for (Date date : bizDate) {
            System.out.println(date);
        }
    }

    /**
     * 描述：获取日期的年和月
     * @author（创建人）： cj
     * @createDate（创建时间） ：  2021/4/7
     * @updateUser（修改人）：
     * @updateDate（修改日期）：
     * @updateRemark（修改备注）：
     * @Param []
     * @return
     */
    private static void getYearAndMonth(){
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date time = calendar.getTime();
        System.out.println(date);
        System.out.println(time+"  year:"+calendar.get(Calendar.YEAR)+"  month:"+(calendar.get(Calendar.MONTH)+1));
    }

    /**
     * 描述：根据传入的年份月份，获取该年该月的第一天和最后一天
     * @author（创建人）： cj
     * @createDate（创建时间） ：  2021/4/7
     * @updateUser（修改人）：
     * @updateDate（修改日期）：
     * @updateRemark（修改备注）：
     * @Param [year, monthString]
     * @return
     */
    private static List<Date> getBizDate(int year, String monthString){
        int month = Integer.parseInt(monthString);
        List<Date> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        //date为正数，则就是在单月的最后一天在加date天数，就到了下月去了；为负数的话就当前这个月的date减去这几天
        //这个月减1，就是上个月；date为1就是上个月的最后一天加一，也就是这个月的1号
        calendar.set(year,month-1,1);
        Date beginDate = calendar.getTime();
        //date为0，也就是这个月的最后一天不加也不减
        calendar.set(year,month,0);
        Date endDate = calendar.getTime();

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        try {
            beginDate = simple.parse(simple.format(beginDate));
            endDate = simple.parse(simple.format(endDate));
            System.out.println(simple.format(beginDate)+"  String  "+simple.format(endDate));
            System.out.println(simple.parse(simple.format(beginDate))+"  date  "+simple.parse(simple.format(endDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //把开始日期和结束日期加进去
        list.add(beginDate);
        list.add(endDate);
        return list;
    }

}
