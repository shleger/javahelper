package com.ninetyktc.shared.between.threads;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;
import java.util.concurrent.*;

public class DataStore {
    private Map<String,Integer> responseTimes = new ConcurrentHashMap<String,Integer>();
    private boolean available = false;

    public DataStore() {
    }

    public synchronized void putResponseTime(String key, int val) {
        responseTimes.put(key, val);
        notifyAll();
    }

    public synchronized int getResponseTime(String key) {
        notifyAll();
        int val = -1;
        if (responseTimes.containsKey(key)) {
            val = responseTimes.get(key);
        }
        return val;
    }
}
