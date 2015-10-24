package com.saike.grape.test;

public class ThreadLocalTest {
    // 静态初始化final修饰,可以在静态方法或者静态代码块中进行修改等
    private final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
        System.out.println(ThreadLocalTest.getString());
    }

    public static String getString() {
        String string = threadLocal.get();
        if (string == null) {
            string = "默认值";
            threadLocal.set(string);
        }
        return string;
    }
}
