package Arrays.Bipartite;

import java.util.*;
/**
    Last Attempt: 05 Feb 2026
    LeetCode Link: https://leetcode.com/problems/is-graph-bipartite/
    Approach: Bipartite graph problem (VERY BASIC). Iterate the graph with a color(0) and change the color of its neighbors to (1). If you encounter any node whose neighbor has the same color as the currNode, it means the graph is not bipartite, you can return false

    Bipartite graph: You can color the graph with two colors and two neighbor must have different colors

    Time(n): O(V + E)
    Space(n): O(V) {stack depth max can be V}
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;

        // 1: Iterate using DFS
        int[] color = new int[nodes]; // {0: RED, 1: GREEN}
        Arrays.fill(color, -1);
        for (int i = 0; i < nodes; i++) {
            if (color[i] == -1 && !dfs(i, color, 0, graph))
                return false;
        }
        return true;
    }

    private boolean dfs(int u, int[] color, int currColor, int[][] graph) {
        color[u] = currColor;
        for (int v : graph[u]) {
            if (color[v] == currColor)
                return false;
            if (color[v] == -1 && !dfs(v, color, 1 - currColor, graph))
                return false;
        }
        return true;
    }
}