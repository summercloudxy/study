package reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {



        WeakReference<String> sr = new WeakReference<String>(new String("hello"));



        System.out.println(sr.get());

        System.gc();                //通知JVM的gc进行垃圾回收

        //JVM  gc的时候，无论内存是否充足，都会回收弱引用关联的对象
        System.out.println(sr.get());

    }

}