package stack;

//利用先进先出的Queue构建stack

//你要像明白一点一个先进后出 一个先出后进 你只要想办法把他们顺序颠倒过来是不是就可以了？

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
    Queue<Integer> Q;
    /** Initialize your data structure here. */
    public ImplementStackUsingQueues() {
        Queue<Integer> Q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        Q.add(x);
        for (int i = 1; i < Q.size(); i++) {
            Q.add(Q.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return Q.poll();
    }

    /** Get the top element. */
    public int top() {
        return Q.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return Q.isEmpty();
    }
}
