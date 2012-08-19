package ru.saa.notify;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 8/14/12
 * Time: 02:30 AM
 * To change this template use File | Settings | File Templates.
 */
class Operator extends Thread {
    long memTime = System.currentTimeMillis();
    long period = 5000;

    public void run() {
        Long cur = 0L;
        System.out.println("start Operator.run");
        while (true) {

            synchronized (this) {
                while (((cur = System.currentTimeMillis()) - memTime) > period) {
                    System.out.println("Operator.run:\t" + new Date(cur) + "\t Do export things " + getName());
                    memTime = cur;

                    notify();
                }
            }
        }
        // System.out.println("end Operator.run");
    }

}
