package com.dong.pro;

public class Mp3 implements Player {
    @Override
    public void play(String musicName) {
        System.out.println("播放音乐"+ musicName);
    }

    @Override
    public void stop() {
        System.out.println("停止播放");
    }
}
