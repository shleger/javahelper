package ru.saa.main;

import ru.saa.thread.Task;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */


public class Runner {

    public static void main(String ... args){
        System.out.println("Start main");

        Task task = new Task();
        task.setDelayTime(5000L);

        Thread thread = new Thread(task);


//        try {
//            thread.join();
            thread.start();
//        } catch (InterruptedException e) {
//        }


    }
}
