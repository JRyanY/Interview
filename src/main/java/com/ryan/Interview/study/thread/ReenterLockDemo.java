package com.ryan.Interview.study.thread;


class Phone {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t #####invoked sendEmail()");
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
