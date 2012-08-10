package ru.saa.main;

import ru.saa.thread.BigDataProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */


public class Runner {

    public static void main(String... args) {
        System.out.println("Start main");

        BigDataProcessor bigDataProcessor = new BigDataProcessor();
        bigDataProcessor.setDelayTime(5000L);

        Thread thread = new Thread(bigDataProcessor);


        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Stopped main");

    }
}
