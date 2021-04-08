package com.cj.bx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述：白象开发的通用方法
 * @author（创建人）： cj
 * @createDate（创建时间） ：  2021/4/8
 * @updateUser（修改人）：
 * @updateDate（修改日期）：
 * @updateRemark（修改备注）：
 * @Param
 * @return
 */
public class MethodUtils {

    private static final String SYN_SELECTFIELDS = "org,bx_year,bx_month,"
            + "bx_entry.bx_comtype bx_comtype,bx_entry.bx_saledept bx_saledept,bx_entry.bx_material bx_material,bx_entry.bx_qty bx_qty,bx_entry.bx_praprice bx_praprice";

    private static final String TEST = "org,bx_year,bx_month,"
            + ".bx_comtype,bx_saledept,bx_material,bx_qty,bx_praprice";



    public static void main(String[] args) {
        String msg = addEntryIdentification(TEST,".","bx_entry");
        System.out.println(msg);
        changeString(msg);

        //把string字符串改成string数组


        //查询的字段中，清除分录.字段名,只留下字段名
//        String clearAppoint = getClearAppoint(SYN_SELECTFIELDS, " ");
//        System.out.println(clearAppoint);

        //获取日期的年和月
//        getYearAndMonth();

        //获取根据传入的年份月份，获取该年该月的第一天和最后一天
//        List<Date> bizDate = getBizDate(2020, "06");
//        for (Date date : bizDate) {
//            System.out.println(date);
//        }
    }

    public static String[] changeString(String str){
        String[] split = str.split(",");
        String[] strs = new String[10];
        return strs;
    }


    /**
     * 描述：通过分割符来给后面那些需要加分录标识的字段名加标识并起别名
     * @author（创建人）： cj
     * @createDate（创建时间） ：  2021/4/8
     * @updateUser（修改人）：
     * @updateDate（修改日期）：
     * @updateRemark（修改备注）：
     * @Param [str, splitString]
     * @return 分录的id会给一个特殊别名entryid
     */
    public static String addEntryIdentification(String str,String splitString,String entry){
        StringBuffer sb = new StringBuffer();
        String[] splits = str.split(splitString);
        String entryString = null;
        String[] entrySplit = null;
        if(splits.length>1)
        {
            sb=entryFor(sb,splits,entry);
        }
        else
        {
            splits=str.split("\\"+splitString);
            if (splits.length>1)
            {

                sb=entryFor(sb,splits,entry);
            }
            else
            {
                //一直没有分割成功，把传进来的字符串返回出去
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private static StringBuffer entryFor(StringBuffer sb,String[] splits,String entry){
        sb.append(splits[0]);
        String entryString = splits[1];
        //用逗号分割需要加分录的字段标识
        String[] entrySplit = entryString.split(",");
        for (int i = 0; i < entrySplit.length; i++) {
            if(i==0)
            {
                //判断前半段最后一位是否是逗号
                int length = splits[0].length();
                int lastLength = splits[0].lastIndexOf(",")+1;
                if(length != lastLength)
                {
                    sb.append(",");
                }
            }
            else
            {
                sb.append(",");
            }
            sb.append(entry).append(".").append(entrySplit[i]).append(" ").append(entrySplit[i]);

        }
        return sb;
    }

    /**
     * 描述：查询的字段中，如果存在要清除的字符就分割后取后面那一段
     * @author（创建人）： cj
     * @createDate（创建时间） ：  2021/4/8
     * @updateUser（修改人）：
     * @updateDate（修改日期）：
     * @updateRemark（修改备注）：
     * @Param [str, clearString]  字符串，要清除的字符串
     * @return
     */
    public static String getClearAppoint(String str,String clearString){
        StringBuffer sb = new StringBuffer();
        String[] splits = str.split(",");
        for (int i = 0; i < splits.length; i++) {
            String s = splits[i];
            if(sb.toString()!=null && !"".equals(sb.toString()))
            {
                sb.append(",");
            }
            //判断是否是分录的值
            if (s.contains(clearString))
            {
                String[] split = s.split(" ");
                String msg = split.length > 1?split[1]:"";
                sb.append(msg);
            }
            else
            {
                sb.append(s);
            }
        }
        return sb.toString();
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
