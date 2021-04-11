package string;

//题目要求 根据String build这个tree 你永远都是先build left child如果存在的话

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int firstParen = s.indexOf("(");
        int val = firstParen == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, firstParen));
        TreeNode root = new TreeNode(val);
        if (firstParen == -1) {
            return root;
        }
        int count = 0;
        int start = firstParen;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count ++;
            } else if (s.charAt(i) == ')') {
                count --;
            }
            if (start == firstParen && count == 0) {
                root.left = str2tree(s.substring(start+1, i));
                start = i + 1;
            } else if (count == 0) {
                root.right = str2tree(s.substring(start+1, i));
            }
        }
        return root;
    }
}
