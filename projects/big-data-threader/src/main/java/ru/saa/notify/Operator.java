package ru.saa.notify;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 8/14/12
 * Time: 02:30 AM
 * To change this template use File | Settings | File Templates.
 */
class Operator extends Thread {
    public void run() {
        while (true) {
            synchronized (this) {
                // Calculate new machine steps from shape
                notify();
            }
        }
    }

}
