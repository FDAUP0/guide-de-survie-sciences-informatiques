public class BigDecimal {
    /**Translates a character array representation of a BigDecimal into a BigDecimal, accepting the same sequence of characters as the BigDecimal(String) constructor, while allowing a sub-array to be specified.
     * Note that if the sequence of characters is already available within a character array, using this constructor is faster than converting the char array to string and using the BigDecimal(String) constructor .
     * @param in - char array that is the source of characters.
     * @param offset - first character in the array to inspect.
     * @param len - number of characters to consider.
     * @Throws NumberFormatException - if in is not a valid representation of a BigDecimal
     * or the defined subarray is not wholly within in.
     *
     */
    public static long parseExp(char[] in, int offset, int len){
        long exp = 0;
        offset++;
        char c = in[offset];
        len--;
        boolean negexp = (c == '-');
        // optional sign
        if (negexp || c == '+') {
            offset++;
            c = in[offset];
            len--;
        }
        if (len <= 0) // no exponent digits
            throw new NumberFormatException();
        // skip leading zeros in the exponent
        while (len > 10 && (c=='0' || (Character.digit(c, 10) == 0))) {
            offset++;
            c = in[offset];
            len--;
        }
        if (len > 10) // too many nonzero exponent digits
            throw new NumberFormatException();
        // c now holds first digit of exponent
        for (;; len--) {
            int v;
            if (c >= '0' && c <= '9') {
                v = c - '0';
            } else {
                v = Character.digit(c, 10);
                if (v < 0) // not a digit
                    throw new NumberFormatException();
            }
            exp = exp * 10 + v;
            if (len == 1)
                break; // that was final character
            offset++;
            c = in[offset];
        }
        if (negexp) // apply sign
            exp = -exp;
        return exp;
    }

    /*public static void main(String[] args) {
        char[] chars = "1.25895".toCharArray();
        System.out.println(BigDecimal.parseExp(chars, 4, 2));
    }*/
}
