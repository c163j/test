package com.cj;

import java.io.File;

public class TestFile {

    public static void main(String[] args) {
        for (int i=1;i<=381;i++)
        {
            File file = new File("D:\\哔哩哔哩\\83622425\\"+i);
            Boolean flag = file.exists();
            if(!flag)
            {
                // wdt
                System.out.println("========="+i+"=========");
            }
        }

    }
}
