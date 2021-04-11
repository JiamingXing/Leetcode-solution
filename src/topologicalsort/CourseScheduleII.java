package topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> courseOrder = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        boolean[][] relations = new boolean[numCourses][numCourses];
        int count = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[1];
            int cur = prerequisite[0];
            inDegree[cur] ++;
            relations[pre][cur] = true;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                Q.offer(i);
            }
        }
        while (!Q.isEmpty()) {
            int pre = Q.poll();
            count ++;
            courseOrder.add(pre);
            for (int i = 0; i < relations.length; i++) {
                if (relations[pre][i]) {
                    if (--inDegree[i] == 0) {
                        Q.offer(i);
                    }
                }
            }
        }
        if (count != numCourses) {
            return new int[0];
        }
        int[] res = new int[courseOrder.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = courseOrder.get(i);
        }
        return res;
    }
}
