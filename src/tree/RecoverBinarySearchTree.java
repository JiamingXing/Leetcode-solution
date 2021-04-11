package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//O(n) space的思路也比较直接 先in-order traversal BST得到一个array
//然后把问题转化成swap two elements to make array a acending array

//follow up: 如果我们O(1)的space如何实现算法 想法:可能要在DFS的过程中找到不满足条件的点 之后如何找应该swap的点呢？
// 我们可以用一个pointer
//记录出问题的点在哪里...
//这里会有一个问题 我们判断是否出问题是要比较parent和child的val 来作为判断条件 但是如果找到了 究竟parent和child 哪个点事出问题的点呢？
//

public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        helper(root, nodes);
        TreeNode[] arr = nodes.toArray(new TreeNode[0]);
        int pos = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].val < arr[i-1].val) {
                pos = i-1;
                break;
            }
        }
        int target = pos+1;
        for (int i = pos+1; i < arr.length; i++) {
            if (arr[i].val < arr[pos+1].val) {
                target = i;
                break;
            }
        }
        int temp = arr[target].val;
        arr[target].val = arr[pos].val;
        arr[pos].val = temp;
    }
    private void helper(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return;
        }
        helper(root.left, nodes);
        nodes.add(root);
        helper(root.right, nodes);
    }
}

//莫里斯遍历+O(1) space complexity
class Solution {
    public void recoverTree(TreeNode root) {
        List<TreeNode> eNodes = new LinkedList<TreeNode>(); //error nodes
        if(root == null) return;
        TreeNode current = root;
        TreeNode pre;
        TreeNode previous = null;
        while(current != null){

            if(current.left == null){

                if(previous!=null && previous.val > current.val){
                    eNodes.add(previous);
                    eNodes.add(current);
                }
                previous = current;
                current = current.right;

            }else{
                pre = current.left;
                while(pre.right != null && pre.right.val != current.val){
                    pre = pre.right;
                }

                if(pre.right == null){
                    pre.right = current;
                    current = current.left;
                }else {
                    if(previous!=null && previous.val > current.val){
                        eNodes.add(previous);
                        eNodes.add(current);
                    }

                    pre.right = null;
                    previous = current;
                    current = current.right;
                }
            }
        }

        //this is redundant check
        //if(eNodes.size() == 0) return;

        if(eNodes.size() == 2){
            pre = eNodes.get(0);
            current = eNodes.get(1);
        }else{ //this case where eNodes.size()==4
            pre = eNodes.get(0);
            current = eNodes.get(3);
        }

        int temp = pre.val;
        pre.val = current.val;
        current.val = temp;
    }
}