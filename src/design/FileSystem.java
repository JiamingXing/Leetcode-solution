package design;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {

    TreeNode root;

    public FileSystem() {
        this.root = new TreeNode("root", 0);
    }

    public boolean createPath(String path, int value) {
        String[] dirs = path.split("/");
        TreeNode iter = root;
        for (int i = 1; i < dirs.length-1; i++) {
            String curName = dirs[i];
            if (!iter.child.containsKey(curName)) {
                return false;
            }
            iter = iter.child.get(curName);
        }
        String newDir = dirs[dirs.length-1];
        if (iter.child.containsKey(newDir)) {
            return false;
        }
        iter.child.put(newDir, new TreeNode(newDir, value));
        return true;
    }

    public int get(String path) {
        String[] subPath = path.split("/");
        TreeNode iter = root;
        for (int i = 1; i < subPath.length; i++) {
            if (!iter.child.containsKey(subPath[i])) {
                return -1;
            }
            iter = iter.child.get(subPath[i]);
        }
        return iter.val;
    }

    class TreeNode {
        String dir;
        int val;
        Map<String, TreeNode> child;
        public TreeNode(String dir, int val) {
            this.dir = dir;
            this.val = val;
            this.child = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        String path = "/leet/code";
        String[] dirs = path.split("/");
        System.out.println(dirs[0].isEmpty());
        System.out.println(dirs.length);
        System.out.println(dirs[1]);
    }
}
