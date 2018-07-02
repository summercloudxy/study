package concurrentstudy.latch;

public class Worker extends Thread {
    private MyLatch myLatch;

    public Worker(MyLatch myLatch) {
        this.myLatch = myLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
        myLatch.countDown();
    }

}
