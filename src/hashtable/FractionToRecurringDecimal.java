package hashtable;

import java.util.HashMap;
import java.util.Map;

//在一开始把num和denom转换成long？

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((numerator > 0) ^ (denominator >0) ? "-" : "");
        sb.append(numerator / denominator);
        numerator %= denominator;
        if (numerator == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Integer, Integer> map = new HashMap<>();
        map.put(numerator, sb.length());
        while (numerator != 0) {
            numerator *= 10;
            sb.append(numerator / denominator);
            numerator %= denominator;
            if (map.containsKey(numerator)) {
                sb.insert(map.get(numerator), "(");
                sb.append(")");
                break;
            } else {
                map.put(numerator, sb.length());
            }
        }
        return sb.toString();
    }
}
