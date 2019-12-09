import org.junit.Assert;
import org.junit.Test;

public class 返回最近的不小于输入参数的2的整数次幂 {

    static final int MAXIMUM_CAPACITY = 1 << 30;


    @Test
    public void test1() {
        int i = -100000;
        for (; i < 100000; i++) {
            Assert.assertEquals(tableSizeFor(i), tableSizeFor(i));
//            System.out.println(tableSizeFor(i) + "     " + tableSizeFor(i));
        }
        System.out.println(i);
    }

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int tableSizeForByHbase(int cap) {
        return 1 << (32 - Integer.numberOfLeadingZeros(cap - 1));
    }

}
