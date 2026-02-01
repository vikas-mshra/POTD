package Graphs.TopologicalSort;

import java.util.*;
/**
    Last Attempt: 01 Feb 2026
    LeetCode Link: https://leetcode.com/problems/course-schedule/
    Approach: Kanh's Algorithm(topological sort with BFS)
    Time(n): O(E) + O(V) + O(V + E)
    Space(n): O(V + E) {space required for adjList}
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // Step 1: Calculate indegreeCount and create adj List
        int[] inDegreeCount = new int[numCourses];
        Map<Integer, List<Integer>> adjL = new HashMap<>();
        for (int[] p : prerequisites) {
            adjL.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            inDegreeCount[p[1]]++;
        }

        // Step 2: Find all the course which has 0 dependency to start with and add to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegreeCount[i] == 0)
                queue.add(i);
        
        int finishedCourse = 0;

        // Step 3: Apply bfs and if we are able to find topological sort of it, we can finish the courses and if the topological order can't be find, it means there is a cycle in the graph so we can't finish the courses
        while (!queue.isEmpty()) {
            int src = queue.remove();
            finishedCourse++;

            for (int dest : adjL.getOrDefault(src, new ArrayList<>())) {
                inDegreeCount[dest]--;
                if (inDegreeCount[dest] == 0)
                    queue.add(dest);
            }
        }

        return finishedCourse == numCourses;
    }
}