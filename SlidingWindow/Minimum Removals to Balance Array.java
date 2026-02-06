package SlidingWindow;

import java.util.*;

/**
    Last Attempt: 06 Feb 2026
    LeetCode Link: https://leetcode.com/problems/minimum-removals-to-balance-array/
    Approach: Use sliding window and check if the window is valid, then update the number of element to be remove. If the window is not valid, update your left pointer
    Time(n): O(nlogn)
    Space(n): O(1)
*/
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int minElementsRemoved = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;

        // Maximum value allowed for the current window
        long maxAllowedValue = (long) nums[left] * k;

        while (right < n) {

            // Window is valid: nums[right] satisfies max <= k * min
            if (nums[right] <= maxAllowedValue) {

                int windowSize = right - left + 1;
                int elementsRemoved = n - windowSize;

                minElementsRemoved = Math.min(minElementsRemoved, elementsRemoved);
                right++;

            } else {
                // Shrink window from the left and update allowed maximum
                left++;
                maxAllowedValue = (long) nums[left] * k;
            }
        }

        return minElementsRemoved;
    }
}


/**
    Last Attempt: 05 Feb 2026
    LeetCode Link: https://leetcode.com/problems/minimum-removals-to-balance-array/
    Approach: Sort the array and start looking from left and check the number of elements that need to be removed to consider nums[i] * k as valid minimum. Repeat and keep track of min
    Time(n): O(nlogn + nlogn)
    Space(n): O(n)
*/
class Solution2 {
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
