public class ReadVNDCurrency {
    private static final String[] WORD = {"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"} ;
    private static final String[] RANGE = {"", "", "", "nghìn", "", "", "triệu", "", "", "tỷ"};
    private static final String TEN = "mười";
    private static final String X_TEN = "mươi";
    private static final String HUNDRED = "trăm";
    private static final String H_ZERO = "linh";
    private static final String L_ONE = "mốt";

    public static String readNumber(Long number) {
        String numInStr = number.toString();
        String[] chars = numInStr.split("");
        int length = chars.length;
        if (length > 18)
            throw new RuntimeException("Supported maximum number is 999.999.999.999.999.999");
        String result = "%s";
        int count = 0;
        int i = length;
        while (count < length) {
            int mod = count % 10;
            if (count > 0 && mod > 0 && !isEmptyRange(i, numInStr))
                result = String.format(result, "%s " + RANGE[mod]);
            if (i - 3 >= 0) {
                String[] block = {chars[i-3], chars[i-2], chars[i-1]};
                result = String.format(result, "%s" + readBlock(block));
            } else if (i - 2 >= 0) {
                String[] block = {chars[i-2], chars[i-1]};
                result = String.format(result, "%s" + read2Char(block));
            } else if (i - 1 >= 0) {
                int numb = charToNumber(chars[i - 1]);
                result = String.format(result, "%s" + WORD[numb]);
            }
            count += 3;
            i = i - 3;
        }
        result = result.replace("%s", "");
        return result.trim();
    }

    private static boolean isEmptyRange(int index, String number) {
        if (index - 3 < 0) return false;
        String range = number.substring(index - 3, index);
        int numb = Integer.valueOf(range);
        return numb == 0;
    }

    private static String read2Char(String[] chars2) {
        String result = "";
        int first = charToNumber(chars2[0]);
        if (first == 1) result += (" " + TEN);
        else if (first != 0) result += (String.format(" %s %s", WORD[first], X_TEN));
        int last = charToNumber(chars2[1]);
        if(last == 1 && first > 1)
            result += (" " + L_ONE);
        else if (last != 0) result += (" " + WORD[last]);
        return result;
    }

    private static String readBlock(String[] chars3) {
        if (String.join("", chars3).equals("000")) return "";
        StringBuilder result = new StringBuilder();
        int first = charToNumber(chars3[0]);
        result.append(String.format(" %s %s", WORD[first], HUNDRED));
        if (chars3[1].equals("0") && !chars3[2].equals("0"))
            result.append(" " + H_ZERO);
        result.append(read2Char(new String[]{chars3[1], chars3[2]}));
        return result.toString();
    }

    private static int charToNumber(String c) {
        return Integer.valueOf(c);
    }
}
