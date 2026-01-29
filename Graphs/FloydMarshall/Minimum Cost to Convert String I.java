package Graphs.FloydMarshall;

import java.util.*;
/**
    Last Attempt: 28 Jan 2026
    LeetCode Link: https://leetcode.com/problems/minimum-cost-to-convert-string-i
    Approach: Use Floyd Marshall to find shortest path from every character to every character then we can find the cost of converting one character to another from source to target
    Time(n): O(26^3 + len(string))
    Space(n): O(26^2)
*/
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Step 1: Initialize the distance matrix
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
            dist[i][i] = 0;
        }

        // Step 2: Load direct costs (handle duplicate rules by taking the minimum)
        for (int i = 0; i < original.length; i++) {
            char s = original[i];
            char t = changed[i];

            dist[s - 'a'][t - 'a'] = Math.min(cost[i], dist[s - 'a'][t - 'a']);
        }

        // Step 3: Floyd-Warshall Algorithm
        // via is the intermediate node
        for (int via = 0; via < 26; via++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][via] != Long.MAX_VALUE && dist[via][j] != Long.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                }
            }
        }

        // Step 4: Calculate total cost for the strings
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if (s == t)
                continue;
            if (dist[s][t] == Long.MAX_VALUE)
                return -1;
       
            totalCost += dist[s][t];
        }

        return totalCost;
    }
}