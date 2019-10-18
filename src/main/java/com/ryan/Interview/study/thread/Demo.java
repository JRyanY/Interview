package com.ryan.Interview.study.thread;

public class Demo {
    public static void setStr(String str) {
        str = "abc";


    }

    public static void main(String[] args) {
        String str = "xxx";
        setStr(str);
        System.out.println(str);
    }
}
