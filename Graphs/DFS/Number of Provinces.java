package Graphs.DFS;

import java.util.*;
/**
    Last Attempt: 31 Jan 2026
    LeetCode Link: https://leetcode.com/problems/number-of-provinces/
    Approach: Create an adjL, and traverse the graph using DFS and count the number of times you have to pick a node to traverse all nodes in the graph
    Time(n): O(V^2)
    Space(n): O(V + E)
*/

class Solution {
    public int findCircleNum(int[][] isConnected) {
        // 1: Create Adj list
        Map<Integer, List<Integer>> adjL = new HashMap<>();
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1)
                    adjL.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
            }
        }

        // 2: Traverse over the graph with dfs
        int provinceCount = 0;
        boolean[] isVisited = new boolean[isConnected.length];
        for (Map.Entry<Integer, List<Integer>> entrySet : adjL.entrySet()) {
            if (!isVisited[entrySet.getKey()]) {
                provinceCount++;
                dfs(entrySet.getKey(), adjL, isVisited);
            }
        }
        return provinceCount;
    }

    private void dfs(int u, Map<Integer, List<Integer>> adjL, boolean[] isVisited) {
        isVisited[u] = true;
        for (int v : adjL.getOrDefault(u, Collections.emptyList())) {
            if (!isVisited[v])
                dfs(v, adjL, isVisited);
        }
    }
}