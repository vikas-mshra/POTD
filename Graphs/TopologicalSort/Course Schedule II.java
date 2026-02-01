package Graphs.TopologicalSort;

import java.util.*;
/**
    Last Attempt: 01 Feb 2026
    LeetCode Link: https://leetcode.com/problems/course-schedule-ii/
    Appraoch: Kahn's Algo (if cycle exist then not possible to take all courses)
    Time(n): O(E) + O(V) + O(V + E)
    Space(n): O(V + E)
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1: build adjL
        Map<Integer, List<Integer>> adjL = new HashMap<>();
        int[] indegreeCount = new int[numCourses];
        for (int[] p : prerequisites) {
            adjL.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
            indegreeCount[p[0]]++;
        }

        List<Integer> result = new ArrayList<>();

        // 2: find the course with 0 depedency
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegreeCount[i] == 0) {
                result.add(i);
                queue.add(i);
            }

        // 3: iterate queue until its empty
        boolean[] isVisited = new boolean[numCourses];
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            isVisited[u] = true;
            visitedCount++;

            for (int v : adjL.getOrDefault(u, Collections.emptyList())) {
                indegreeCount[v]--;
                if (indegreeCount[v] == 0) {
                    result.add(v);
                    queue.add(v);
                }
            }
        }
        if (visitedCount != numCourses)
            return new int[0];

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}

/**
    Approach: Topological sort using DFS
    Time(n): O(E) + O(V + E)
    Space(n): O(V + E)
*/
/**
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1: build adjL
        Map<Integer, List<Integer>> adjL = new HashMap<>();
        for (int[] p : prerequisites) {
            adjL.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
        }

        // 2: Iterate all courses
        boolean[] isVisited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!isVisited[i] && hasCycle(i, isVisited, inRecursion, stack, adjL)) {
                return new int[0];
            }
        }

        // 3: building the response
        int[] arr = new int[numCourses];
        int index = 0;
        while (!stack.isEmpty())
            arr[index++] = stack.pop();

        return arr;
    }

    private boolean hasCycle(int u, boolean[] isVisited, boolean[] inRecursion, Stack<Integer> stack,
            Map<Integer, List<Integer>> adjL) {
        isVisited[u] = true;
        inRecursion[u] = true;
        for (int v : adjL.getOrDefault(u, Collections.emptyList())) {
            if (inRecursion[v])
                return true;

            if (isVisited[v])
                continue;

            if (hasCycle(v, isVisited, inRecursion, stack, adjL))
                return true;
        }
        inRecursion[u] = false;
        stack.push(u);
        return false;
    }
}
*/