package segmenttree;

public class RangeSumQueryMutable {
    private class SegmentTreeNode {
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }
    SegmentTreeNode root;
    public RangeSumQueryMutable(int[] nums) {
        this.root = buildTree(nums, 0, nums.length-1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new SegmentTreeNode(nums[start], start, start);
        } else {
            SegmentTreeNode res = new SegmentTreeNode(0, start, end);
            int mid = start + (end - start) / 2;
            res.left = buildTree(nums, start, mid);
            res.right = buildTree(nums, mid+1, end);
            res.sum = res.left.sum+res.right.sum;
            return res;
        }
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    private void updateTree(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end && root.start == pos) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {
                updateTree(root.left, pos, val);
            } else {
                updateTree(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRangeTree(root, i, j);
    }

    private int sumRangeTree(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                return sumRangeTree(root.left, start, end);
            } else if (start > mid) {
                return sumRangeTree(root.right, start, end);
            } else {
                return sumRangeTree(root.left, start, mid) + sumRangeTree(root.right, mid+1, end);
            }
        }
    }
}
