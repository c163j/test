package com.cj;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 测试数字类型
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数字：");
        String str = input.next();
        String msg = new TestBigDecimal().verifyBigDecmial(str);
        System.out.println(msg);
    }

    /**
     *   判断是否小数类型
     * @param number  传入的string
     * @return
     */
    private String verifyBigDecmial(String number){
        String result = "输入正确！";
        //根据点来进行分割
        String[] strs = number.split("\\.");
        //判断输入的是否是小数字段
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        Pattern pattern2 = Pattern.compile("^[\\+]?[\\d]*$");
        boolean flag = pattern.matcher(number).matches();
        if(strs.length==2 )
        {
            //判断输入的是否是小数字段
            boolean flag1 = pattern.matcher(strs[0]).matches();
            boolean flag2 = pattern2.matcher(strs[1]).matches();

            if(!flag1 && !flag2)
            {
                //不是数字
                result = "【" +number+"】"+"只能录入数字！";
            }
        }else if(!flag)
        {
            //不是数字
            result = "【" +number+"】"+"只能录入数字";
        }

        return result;
    }
}
