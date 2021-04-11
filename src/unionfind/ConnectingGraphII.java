package unionfind;

public class ConnectingGraphII {
    int[] parent;
    int[] size;
    int num;

    public ConnectingGraphII(int n) {
        this.parent = new int[n+1];
        this.size = new int[n+1];
        this.num = n;
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void connect(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            parent[root_a] = root_b;
            size[root_b] += size[root_a];
            num --;
        }
    }

    public int query(int a) {
        int root_a = find(a);
        return size[root_a];
    }

    public int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public int queryNumberOfComponents() {
        return num;
    }
}
