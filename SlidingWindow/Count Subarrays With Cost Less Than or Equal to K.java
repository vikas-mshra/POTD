package SlidingWindow;

import java.util.*;

/**
    Last Attempt: 07 Feb 2026
    LeetCode Link: https://leetcode.com/problems/count-subarrays-with-cost-less-than-or-equal-to-k
    Approach: 
        maxDeque: it would store the maxIndex, then second max index....and so on (decreasing order)
        minDeque: it would store the minIndex, then second min index....and so on (increasing order)

        Use sliding window and keep moving your right pointer and keep updating your maxDeque and minDeque and  checking if the window is still valid, if its not then shift your left pointer to the right

    Time(n): O(n)
    Space(n): O(n)
*/
class Solution {
    public long countSubarrays(int[] nums, long k) {
        long countSubarrays = 0;
        int start = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        for (int end = 0; end < nums.length; end++) {

            // maxDeque would be in decreasing order
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[end]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(end);

            // minDequeue would be in increasing order
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[end]) {
                minDeque.pollLast();
            }
            minDeque.addLast(end);

            int maxIndex = maxDeque.peekFirst();
            int minIndex = minDeque.peekFirst();
            while (start <= end &&
                    (long) (nums[maxIndex] - nums[minIndex]) * (end - start + 1) > k) {
                
                // shift start pointer
                start++;
                if (maxDeque.peekFirst() < start)
                    maxDeque.pollFirst();
                if (minDeque.peekFirst() < start)
                    minDeque.pollFirst();

                maxIndex = maxDeque.peekFirst();
                minIndex = minDeque.peekFirst();
            }

            // All subarrays ending at 'end' and starting between 'start' and 'end' are valid
            countSubarrays += (end - start + 1);
        }

        return countSubarrays;
    }
}