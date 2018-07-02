package concurrentstudy.blockingqueue;

public class Producer {
    private MyBlockingQueue myBlockingQueue;


    public Producer(MyBlockingQueue myBlockingQueue) {
        this.myBlockingQueue = myBlockingQueue;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myBlockingQueue.put("" + i);
                System.out.println("生产者生产：" + i);
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }
}
