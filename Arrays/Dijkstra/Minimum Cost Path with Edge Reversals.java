package Arrays.Dijkstra;

import java.util.*;
/**
    Last Attempt: 27 Jan 2026
    LeetCode Link: https://leetcode.com/problems/minimum-cost-path-with-edge-reversals
    Approach: Build the adjL and create a dp which would store the cost of reaching that node. Initially every node except 0 would be infinity and as we will find better cost, we would update the dp
    Time(n): O(V + 2E)
    Space(n): O(V + 2E)
*/
class Solution {
    public int minCost(int nodeCount, int[][] edges) {
        Map<Integer, Set<Integer>> adjacency = new HashMap<>();
        Map<String, Integer> edgeCost = new HashMap<>();

        // Build graph with directional costs
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            String forwardKey = from + "#" + to;
            edgeCost.put(forwardKey, Math.min(cost, edgeCost.getOrDefault(forwardKey, cost)));
            adjacency.computeIfAbsent(from, k -> new HashSet<>()).add(to);

            String reverseKey = to + "#" + from;
            edgeCost.put(reverseKey, Math.min(2 * cost, edgeCost.getOrDefault(reverseKey, 2 * cost)));
            adjacency.computeIfAbsent(to, k -> new HashSet<>()).add(from);
        }

        int targetNode = nodeCount - 1;
        nodeCount = Math.max(nodeCount, adjacency.size());

        int[] minDist = new int[nodeCount];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.add(new int[] {0, 0});

        // Dijkstra's algorithm
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[0];
            int currCost = current[1];

            if (currCost > minDist[node])
                continue; // skip outdated entry

            if (node == targetNode)
                return currCost;

            for (int neighbor : adjacency.getOrDefault(node, Collections.emptySet())) {
                int weight = edgeCost.get(node + "#" + neighbor);
                int nextCost = currCost + weight;

                if (nextCost < minDist[neighbor]) {
                    minDist[neighbor] = nextCost;
                    minHeap.add(new int[] {neighbor, nextCost});
                }
            }
        }

        return -1;
    }
}
