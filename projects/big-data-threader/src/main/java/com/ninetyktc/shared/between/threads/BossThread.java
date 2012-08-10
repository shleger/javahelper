package com.ninetyktc.shared.between.threads;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class BossThread extends Thread {
    private final ArrayList workers = new ArrayList();
    private final DataStore dataStore = new DataStore();
    private String threadType; // type of thread operation
    private int numWorkersStarted = 0; // started thread counter
    private int numThreads = 20; // number of threads to spawn

    public BossThread() {
    }

    public void run() {
        try {
            threadType = "put";
            createWorkerThreads();
            startWorkerThreads();

            threadType = "get";
            createWorkerThreads();
            startWorkerThreads();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createWorkerThreads() throws Exception {
        for (int i = 1; i <= numThreads; i++) {
            String threadName = threadType + "_WORKER_" + i;
            workers.add(new WorkerThread(threadName, dataStore, threadType));
        }
    }

    private void startWorkerThreads() throws Exception {
        int threadStartIndex = numWorkersStarted;
        for (int i = threadStartIndex; i < numThreads + threadStartIndex; i++) {
            WorkerThread worker = (WorkerThread) workers.get(i);
            Thread.sleep(250 + (int) (Math.random() * 500));
            worker.start();
            numWorkersStarted++;
        }
    }
}
