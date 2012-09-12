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

    public Machine(Operator operator) {
        this.operator = operator;
    }

    public void run() {
        while (true) {
            synchronized (operator) {
                try {
                    System.out.println("Machine.run" + " - write update- " + getName());
                    operator.wait();
                    System.out.println("Machine.run" + "- unWait - " + getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //do hardware
            }
        }
    }

}