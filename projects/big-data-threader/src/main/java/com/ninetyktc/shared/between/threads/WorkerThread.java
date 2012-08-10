package com.ninetyktc.shared.between.threads;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */
public class WorkerThread extends Thread {
    private DataStore dataStore;
    private String threadType;
    private String threadName;

    public WorkerThread(String threadName, DataStore dataStore, String threadType) {
        this.threadName = threadName;
        this.dataStore = dataStore;
        this.threadType = threadType;
    }

    public void run() {
        if (threadType.equals("put")) putdataStore();
        if (threadType.equals("get")) getdataStore();
    }

    private void putdataStore() {
        for (int i = 0; i < 10; i++) {
            dataStore.putResponseTime("key", i);
            System.out.println(threadName + " put: " + i);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getdataStore() {
        int val = 0;
        for (int i = 0; i < 10; i++) {
            val = dataStore.getResponseTime("key");
            System.out.println(threadName + " get: " + val);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
