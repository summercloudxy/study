package reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {


    public static void main(String[] args) {


        SoftReference<String> sr = new SoftReference<String>(new String("hello"));

        System.out.println(sr.get());

        System.gc();
        //JVM  gc的时候，内存不足的时候才会回收软引用关联的对象，内存充足的时候不会被回收
        System.out.println(sr.get());

    }


}