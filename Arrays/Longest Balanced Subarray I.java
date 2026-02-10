package Arrays;

/**
    Last Attempt: 09 Feb 2026
    LeetCode Link: https://leetcode.com/problems/longest-balanced-subarray-i/
    Approach: Use two sets to keep track of even and odd numbers and check if the size of the sets are equal, if they are then update the max length of the balanced subarray
    Time(n): O(n^2)
    Space(n): O(n^2)
*/

import java.util.*;
class Solution {
    public int longestBalanced(int[] nums) {
        // 1. Change declaration to HashSet
        Set<Integer> evenSet = new HashSet<>();
        Set<Integer> oddSet = new HashSet<>();

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            // PRUNING:
            // If the remaining elements are fewer than the max we've already found,
            // we can stop completely. We'll never find a better answer.
            if (nums.length - i <= max)
                break;

            for (int j = i; j < nums.length; j++) {
                // 2. Just ADD. The Set automatically ignores duplicates.
                if (nums[j] % 2 == 0) {
                    evenSet.add(nums[j]);
                } else {
                    oddSet.add(nums[j]);
                }

                // 3. Logic remains the same
                if (evenSet.size() == oddSet.size()) {
                    max = Math.max(max, j - i + 1);
                }
            }

            evenSet.clear();
            oddSet.clear();
        }

        return max;
    }
}