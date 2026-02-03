package SlidingWindow;

import java.util.*;

/**
    Last Attempt: 02 Feb 2026
    LeetCode Link: https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii
    Approach: We will have a window of size and we would keep sliding the window and calculate the kMin element in those window and return the min sum from all the window
    Time(n): O(n logk)
    Space(n): O(k)
*/
class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        // 1: store the kMin element and remaining element in a sliding window
        TreeSet<int[]> kMin = new TreeSet<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        TreeSet<int[]> remaining = new TreeSet<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        long total = nums[0];
        long minSum = Long.MAX_VALUE;
        
        // 2: Slide the window where the end - start <= dist
        for (int end = 1; end < n; end++) {
            total += nums[end];
            kMin.add(new int[] { nums[end], end });

            // if we have more than k min, then poll the largest one and push into the remaining one
            if (kMin.size() == k) {
                int[] largestPair = kMin.pollLast();
                remaining.add(largestPair);
                total -= largestPair[0];
            }

            // valid windows
            if (end - dist >= 1) {
                minSum = Math.min(minSum, total);

                // remove the expiredIndex
                int start = end - dist;
                int[] toRemove = new int[] { nums[start], start };

                if (kMin.remove(toRemove)) {
                    total -= toRemove[0];

                    if (!remaining.isEmpty()) {
                        int[] promote = remaining.pollFirst();
                        kMin.add(promote);
                        total += promote[0];
                    }
                } else {
                    remaining.remove(toRemove);
                }
            }
        }
        return minSum;
    }
}