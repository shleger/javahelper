package ru.saa.start;

import ru.saa.thread.BigDataProcessor;
import ru.saa.thread.FeedBack;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */


public class Runner {

    FeedBack feedBack;

    public Runner() {
    }

    public Runner(FeedBack feedBack) {
        this.feedBack = feedBack;
    }

    public static void main(String... args) {


        Runner runner = new Runner(new FeedBack());
        runner.runRunner();


    }


    public void runRunner() {
        System.out.println("Start main");

        BigDataProcessor bigDataProcessor = new BigDataProcessor();
        bigDataProcessor.setDelayTime(5000L);


        Thread thread = new Thread(bigDataProcessor);

//        synchronized (feedBack) {
//
//        	try {
//				this.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//
//		}


        try {
            thread.start();
            thread.join();

            while (thread.isAlive()) {

                System.out.print("is Alive");
            }

        } catch (InterruptedException e) {
        }

        System.out.println("Stopped main");

    }
}
