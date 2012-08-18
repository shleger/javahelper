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
                for (int i = 0; i < 30; i++) {
                    System.out.println("Operator.run:\t" + i + "\t Do export things " + getName());

                }
                notify();
            }
        }
    }

}
