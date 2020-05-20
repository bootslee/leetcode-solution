package com.bootslee.leetcode;

import java.util.*;

/**
 * Created By BootsLee on 2020/5/17
 **/
public class CourseScheduleII210 {
    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     * 在选修某些课程之前需要一些先修课程。 
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        // HashSet 作为邻接矩阵
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }
        int[] mark = new int[numCourses]; // 标记数组
        Stack<Integer> stack = new Stack<>(); // 结果栈
        for (int i = 0; i < numCourses; i++) {
            if(!isCycle(graph, mark, i, stack)) return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
    private boolean isCycle(HashSet<Integer>[] graph, int[] mark, int i, Stack<Integer> stack) {
        if (mark[i] == -1) return true;
        if (mark[i] == 1) return false;

        mark[i] = 1;
        for (int neighbor : graph[i]) {
            if (!isCycle(graph, mark, neighbor, stack)) return false;
        }
        mark[i] = -1;
        stack.push(i);
        return true;
    }

    public static void main(String[] args) {
        new CourseScheduleII210().findOrder(2,new int[][]{{0,1}});
    }
}
