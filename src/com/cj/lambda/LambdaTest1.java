package com.cj.lambda;

import java.util.Comparator;

/**
 * 测试lambda表达式
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        threadTest();

        comparatorTest();

    }

    /**
     * 使用线程测试lambda
     */
    private static void threadTest(){
        //原始方式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable  原始方式");
            }
        };
        r1.run();

        System.out.println("==============================");

        //lambda方式
        Runnable r2 = () -> System.out.println("runnable  lambda方式");
        r2.run();
    }

    /**
     * 使用比较测试lambda
     */
    private static void comparatorTest(){
        //comparator原始方式
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int num1 = comparator1.compare(12,33);
        System.out.println("comparator原始方式："+num1);

        //comparator  lambda方式
        Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);
        int num2 = comparator2.compare(33,33);
        System.out.println("comparator lambda方式："+num2);

        //comparator  方法引用方式
        Comparator<Integer> comparator3 = Integer :: compare;
        int num3 = comparator3.compare(44,33);
        System.out.println("comparator 方法引用方式："+num3);
    }
}
