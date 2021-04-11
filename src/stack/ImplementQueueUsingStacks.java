package stack;

import java.util.Stack;

//其实和 implement stack using queue的核心思想是相同的
//既然queue和stack存储的顺序是完全相反的
//那么我们就想 比如我们一个stack中有123 那你想让1出来 就得都倒出来
//需要另一个stack 来装 第二stack是专门用来pop的
//你push的时候还要把第二个stack的number倒回去...

//总结就是 两个stack  一个用来 push  一个用来 pop...
//用queue实现是stack的话 我们只需要一个Q 每次push新的元素 我们如何保证这个元素都在第一个呢？？
//都把之前的元素poll出来重新push放到最后面去...

public class ImplementQueueUsingStacks {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
