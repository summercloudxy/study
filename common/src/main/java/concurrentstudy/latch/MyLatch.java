package concurrentstudy.latch;

public class MyLatch {
    private int count;

    public MyLatch(int count) {
        this.count = count;
    }

    public synchronized void await() {

        while (count != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void countDown(){
        count--;
        if (count == 0) {
            notifyAll();
        }
    }
}
