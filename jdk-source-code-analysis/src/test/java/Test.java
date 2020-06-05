import java.math.BigDecimal;

public class Test {

    @org.junit.Test
    public void test1(){
        BigDecimal a = new BigDecimal(1.01);
        BigDecimal b = new BigDecimal(2.02);

        BigDecimal c = new BigDecimal("1.01");
        BigDecimal d = new BigDecimal("2.02");

        BigDecimal e = new BigDecimal(Double.toString(1.01));
        BigDecimal f = new BigDecimal(Double.toString(2.02));

        BigDecimal g = BigDecimal.valueOf(1.01);
        BigDecimal h = BigDecimal.valueOf(2.02);

        System.out.println(a.add(b));
        System.out.println(c.add(d));
        System.out.println(e.add(f));
        System.out.println(g.add(h));
    }
}
