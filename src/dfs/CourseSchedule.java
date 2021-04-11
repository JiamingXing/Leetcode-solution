package com.jimmy.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[][] relation = new boolean[numCourses][numCourses];
        int[] inDgree = new int[numCourses];
        int count = 0;
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int course = prerequisites[i][0];
            inDgree[course] ++;
            relation[course][pre] = true;
        }
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDgree[i] == 0) {
                Q.offer(i);
            }
        }
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            count ++;
            for (int i = 0; i < numCourses; i++) {
                if (relation[i][cur]) {
                    inDgree[i] --;
                    if (inDgree[i] == 0) {
                        Q.offer(i);
                    }
                }
            }
        }
        return count == numCourses;
    }
}
