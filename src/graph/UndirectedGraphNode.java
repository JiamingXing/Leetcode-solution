package graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int label) {
        this.label = label;
        this.neighbors = new ArrayList<>();
    }
}
