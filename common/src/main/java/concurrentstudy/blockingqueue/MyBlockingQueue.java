package concurrentstudy.blockingqueue;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue {
    private Queue<String> queue = null;
    private int limit;

    public MyBlockingQueue(int limit) {

        this.limit = limit;
        this.queue = new ArrayDeque<>(limit);
    }


    public synchronized void put(String content) {
        while (queue.size() == limit) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(content);
        this.notifyAll();
    }


    public synchronized String take() {
        while (queue.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String poll = queue.poll();
        this.notifyAll();
        return poll;
    }
}
