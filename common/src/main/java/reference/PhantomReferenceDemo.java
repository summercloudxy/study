package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo{


    public static void main(String[] args) {

        ReferenceQueue<String> queue = new ReferenceQueue<String>();

        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);

        //虚引用对象未必会被回收，但是永远取不到
        System.out.println(pr.get());
        //jvm回收掉对象的时候，会把该引用添加到队列中
        System.gc();
        System.out.println(pr.get());

    }
}


