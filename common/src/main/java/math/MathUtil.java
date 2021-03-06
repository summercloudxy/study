package math;

public class MathUtil {

    /**
     * @param i   要操作的数
     * @param pos 第几位
     * @return 把 i 的第pos 位变为0后的值
     */
    public static int do0(int i, int pos) {
        int src = Integer.MAX_VALUE ^ (1 << (pos - 1));
        return i & src;
    }

    /**
     * @param i     要操作的数
     * @param pos    第几位
     * @return 把 i 的第pos 位变为1后的值
     */
    public static int do1(int i, int pos) {
        return i | (1 << pos - 1);
    }

    public static void main(String[] args) {
        int i = 0x01;
        Integer i1 = do1(i, 4);
        System.out.println(Integer.toBinaryString(i1));
    }

}
