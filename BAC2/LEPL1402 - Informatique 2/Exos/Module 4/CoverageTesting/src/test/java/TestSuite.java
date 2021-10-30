import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestSuite {
    @Test
    public void testbasic() {
        // parameters for parseExp
        char[] in = new char[]{'1', '2', '3', '4'};
        // you can also use .toCharArray from String class for this:
        // char[] in = myString.toCharArray();
        int offset = 2;
        int len = 2;
        // run the program with the given situation
        long parse = BigDecimal.parseExp(in, offset, len);
        assertEquals(4, parse);
    }
    @Test
    public void testoptionalsign() {
        // go throught first if by 2 ways
        char[] in = new char[]{'+', '2', '3', '4'};
        int offset = 0;
        int len = 4;
        long parse = BigDecimal.parseExp(in, offset, len);
        assertEquals(234, parse);
        in = new char[]{'-', '2', '3', '4'};
        offset = 0;
        len = 4;
        parse = BigDecimal.parseExp(in, offset, len);
        assertEquals(234, parse);
    }
    @Test(expected = NumberFormatException.class)
    public void test_no_exponent_digits() {
        // run the len <= 0 exception
        char[] in = new char[]{'1', '2', '3', '4'};
        int offset = 0;
        int len = -1;
        long parse = BigDecimal.parseExp(in, offset, len);
    }

    @Test(expected = NumberFormatException.class)
    public void test_toomanynonzeroexponentdigits() {
        //run the len > 10 exception
        char[] in = new char[]{'1', '2', '3', '4','5','6','7','8','9','1','2','3','4','5'};
        int offset = 0;
        int len = 15;
        long parse = BigDecimal.parseExp(in, offset, len);
    }
    @Test
    public void testlotofzeros() {
        // qo throught the while "skip leading zeros in the exponent" loop without throwing a NumberFormatException
        char[] in = new char[]{'0','0','0','0','0','0','0','0','0','0','1', '2', '3', '4'};
        int offset = 0;
        int len = 14;
        long parse = BigDecimal.parseExp(in, offset, len);
        long expect = 1234;
        assertEquals(expect, parse);
    }
    @Test(expected = NumberFormatException.class)
    public void test_c_not_digit(){
        //run the v < 0 (not a digit) exception in the for loop
        char[] in = new char[]{'1', 'b', '3', '4'};
        int offset = 0;
        int len = 4;
        long parse = BigDecimal.parseExp(in, offset, len);
    }
}
