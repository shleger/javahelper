package ru.saa.notify;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 8/18/12
 * Time: 24:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class Runner {

    public static void main(String ...args){

        System.out.println("Start Runner.main");

        Operator op = new Operator();

        Machine machine = new Machine(op);
        machine.start();
        op.start();

        System.out.println("Stop Runner.main");
    }
}
