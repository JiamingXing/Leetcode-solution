package topologicalsort;

import java.util.LinkedList;
import java.util.Queue;

//想一想在有向图

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        int m = prerequisites.length, n = prerequisites[0].length;
        int[] inDegree = new int[numCourses];
        boolean[][] relation = new boolean[numCourses][numCourses];
        for (int i = 0; i < m; i++) {
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            inDegree[cur] ++;
            relation[pre][cur] = true;
        }
        Queue<Integer> Q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                Q.offer(i);
            }
        }
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            res ++;
            for (int i = 0; i < numCourses; i++) {
                if (relation[cur][i]) {
                    if (--inDegree[i] == 0) {
                        Q.offer(i);
                    }
                }
            }
        }
        return res == numCourses;
    }
}
