package concurrentstudy.blockingqueue;

import java.util.List;


public class Consumer {
    private MyBlockingQueue myBlockingQueue;

    public Consumer(MyBlockingQueue myBlockingQueue) {
        this.myBlockingQueue = myBlockingQueue;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                String take = myBlockingQueue.take();
                System.out.println("消费者消费：" + take);
                try {
                    Thread.sleep((int)Math.random()*100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
