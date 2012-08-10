package com.ninetyktc.shared.between.threads;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        BossThread boss = new BossThread();
        boss.start();
    }
}
