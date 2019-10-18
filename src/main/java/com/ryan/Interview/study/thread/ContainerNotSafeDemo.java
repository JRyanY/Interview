package com.ryan.Interview.study.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();


        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException
        /**
         * 1 故障现象
         * java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *  并发争抢修改导致
         *
         * 3解决方案
         *   3.1 使用Vetor（）
         *   3.2  Collections.synchronizedList(new ArrayList<>());
         *   3.3 new CopyOnWriteArrayList
         *
         * 4优化建议（同样的错误不犯第2次）
         */
    }
}
