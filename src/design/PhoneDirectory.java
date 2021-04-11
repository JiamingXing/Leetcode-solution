package design;

import java.util.*;

public class PhoneDirectory {
    Set<Integer> assigned;
    List<Integer> available;
    Random rand;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.assigned = new HashSet<>();
        this.available = new ArrayList<>();
        for (int i = 0; i < maxNumbers; i++) {
            available.add(i);
        }
        this.rand = new Random();
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.size() == 0) {
            return -1;
        }
        int index = rand.nextInt(available.size());
        int res = available.get(index);
        assigned.add(res);
        available.remove(index);
        return res;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !assigned.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (assigned.contains(number)) {
            available.add(number);
            assigned.remove(number);
        }
    }
}
