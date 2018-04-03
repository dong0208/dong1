package com.dong.inner;

public class StaticInner {
    private static int a = 4 ;
    //静态内部类
    public static class Inner{
        public void test(){
            //静态内部类只可以访问只可以访问外部类的讲台成员
            //并且他只能访问静态的
            System.out.println(a);
        }
    }
}
