package stack;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        if (paths.length == 0) {
            return "/";
        }
        Deque<String> stack = new LinkedList<>();
        for (String s : paths) {
            if (s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (s.equals("")){
                continue;
            } else {
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        } else {
            while (!stack.isEmpty()) {
                sb.insert(0, "/" + stack.pop());
            }
            return sb.toString();
        }
    }
}

//主要是看下别人的代码 如何简洁..
//为什么用Dequeue？
//Stack.pop() can be replaced by stack.poll() check size ZERO instead of throwing warning msg when stack is empty.
//stack.pollLast() and StringBuilder can be used instead of creating a lot of Strings objects in the stack.
//Because the if(s.equals("..") already contain all cases contains "..", we don't need to push ".." when dealing with "space" & "."
/*
class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for(String s: path.split("/")){
            if(s.equals("..") ) stack.poll();
            else if(!s.equals("") && !s.equals(".")) stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        if(stack.size() == 0) return "/";
        while(stack.size() != 0) sb.append("/").append(stack.pollLast());
        return sb.toString();
    }
}
 */
