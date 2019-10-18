package com.ryan.Interview.study.thread;

public class ReSortSeqDemo {
    public static void main(String[] args) {
        method01();
        method02();
    }

    static int a = 0;
    static boolean flag = false;

    public static void method01() {
        a = 1;
        flag = true;
    }

    public static void method02() {
        if (flag) {
            a = a + 5;
            while (true) {
                System.out.println("*****revalue:" + a);
            }
        }
    }
}
