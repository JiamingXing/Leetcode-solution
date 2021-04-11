package amazon;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortAmazonOrders {
    char c;
    public void sortOrders(List<List<String>> orders) {
        Collections.sort(orders, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                String s1 = o1.get(0);
                String s2 = o2.get(0);
                if ((isPureDigital(s1) && isPureDigital(s2)) || (!isPureDigital(s1) && !isPureDigital(s2))) {
                    int i = 1;
                    int j = 1;
                    while (i < o1.size() && j < o2.size()) {
                        if (o1.get(i).compareTo(o2.get(j)) == 0) {
                            i ++;
                            j ++;
                        } else {
                            return o1.get(i).compareTo(o2.get(j));
                        }
                    }
                    if (i < o1.size()) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (isPureDigital(s1)){
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
    private boolean isPureDigital(String s) {
//        if (s == null || s.isEmpty()) {
//            return false;
//        }
//        for (char c : s.toCharArray()) {
//            if (!Character.isDigit(c)) {
//                return false;
//            }
//        }
//        return true;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
