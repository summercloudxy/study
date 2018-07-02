package concurrentstudy.blockingqueue;

public class Test {
    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);
        new Consumer(myBlockingQueue).start();
        new Producer(myBlockingQueue).start();
    }
}
