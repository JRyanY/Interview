package com.ryan.Interview.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
    }
}
