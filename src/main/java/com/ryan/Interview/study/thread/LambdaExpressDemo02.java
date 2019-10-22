package com.ryan.Interview.study.thread;

interface Foo {
    //  void sayHello();

    int add(int x, int y);
}

/**
 * 1 函数式编程
 * int age =23;
 * <p>
 * y=kx+1;
 * <p>
 * 拷贝小括号，写死右箭头，落地大括号
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {

        Foo foo = (x, y) -> {
            System.out.println("come in add method");
            return x + y;
        };
        System.out.println(foo.add(2, 3));

    }
}
