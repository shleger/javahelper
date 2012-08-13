package ru.saa.notify;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 8/14/12
 * Time: 02:33 AM
 * To change this template use File | Settings | File Templates.
 */
class Machine extends Thread {
    Operator operator; // assume this gets initialized


    public void run() {
        while (true) {
            synchronized (operator) {
                try {
                    operator.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //do hardware
            }
        }
    }

}