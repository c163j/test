package com.cj;

import java.io.File;

public class TestFile {

    public static void main(String[] args) {
        for (int i=1;i<=200;i++)
        {
            File file = new File("C:\\哔哩哔哩\\48144058\\"+i);
            Boolean flag = file.exists();
            if(!flag)
            {
                // wdt
                System.out.println("========="+i+"=========");
            }
        }

    }
}
