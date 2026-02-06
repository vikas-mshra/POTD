package Arrays;

import java.util.*;
/**
    Last Attempt: 05 Feb 2026
    LeetCode Link: https://leetcode.com/problems/minimum-removals-to-balance-array/
    Approach: Sort the array and start looking from left and check the number of elements that need to be removed to consider nums[i] * k as valid minimum. Repeat and keep track of min
    Time(n): O(nlogn + nlogn)
    Space(n): O(n)
*/
class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        // Maps value -> last index of that value in the sorted array
        TreeMap<Long, Integer> valueToLastIndex = new TreeMap<>();

        // Store only unique values with their last occurrence
        for (int i = 0; i < n; i++) {
            if (i + 1 != n && nums[i] == nums[i + 1])
                continue;
            valueToLastIndex.put((long) nums[i], i);
        }

        int minElementsRemoved = Integer.MAX_VALUE;

        // Treat nums[i] as the minimum element of the remaining array
        // if the left side number of ele is greater than minElementsRemoved, we should stop
        for (int i = 0; i < n && i < minElementsRemoved; i++) {

            // Skip duplicate minimum candidates
            if (i != 0 && nums[i] == nums[i - 1])
                continue;

            long maxAllowedValue = (long) nums[i] * k;

            // Find the largest value <= maxAllowedValue
            Map.Entry<Long, Integer> validMaxEntry =
                    valueToLastIndex.floorEntry(maxAllowedValue);

            if (validMaxEntry != null) {

                // Elements greater than allowed max
                int removedFromRight = n - validMaxEntry.getValue() - 1;

                // Elements smaller than chosen minimum
                int removedFromLeft = i;

                int totalRemoved = removedFromLeft + removedFromRight;
                minElementsRemoved = Math.min(minElementsRemoved, totalRemoved);
            }
        }

        return minElementsRemoved == Integer.MAX_VALUE ? 0 : minElementsRemoved;
    }
}
