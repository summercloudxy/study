package concurrentstudy.latch;

public class Test {
    public static void main(String[] args) {
        MyLatch myLatch = new MyLatch(10);
        for (int i = 0; i <10 ;i ++){
            Worker worker = new Worker(myLatch);
            worker.start();
        }
        myLatch.await();
        System.out.println("等待结束");
    }
}
