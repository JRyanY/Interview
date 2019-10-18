package com.ryan.Interview.study.thread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;

    public void addTO60() {
        this.number = 60;
    }

    //请注意：此时number前面是加了volatile关键字修饰，
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 * 1.1假如int number=0；number变量之前根本没有添加volatile关键字修饰,没有可见性
 * 1.2添加了volatile,可以解决可见性问题。
 * <p>
 * 2 验证volatile不保证原子性
 * 2.1原子性指的是什么意思？
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时完成，要么同时失败
 * <p>
 * 2.2 不保证原子性
 * 2.3 why
 * <p>
 * 2.4 如何解决原子性？
 * 加sync
 * 使用我们的JUC下的原子类
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少
        //暂停一会儿线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "/t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "/t finally number value:" + myData.atomicInteger);
    }


    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void setOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTO60();
            System.out.println(Thread.currentThread().getName() + "\t updated value in:" + myData.number);
        }, "AAA").start();
        //第2个线程就是我们的main线程


        while (myData.number == 0) {
            //main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get my data.number:" + myData.number);
    }
}
