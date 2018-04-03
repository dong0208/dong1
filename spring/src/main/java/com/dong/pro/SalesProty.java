package com.dong.pro;

public class SalesProty extends Sales {
    @Override
    public void Sales() {
        System.out.println("售前咨询");
        super.Sales();
        System.out.println("售后服务");
    }
}
