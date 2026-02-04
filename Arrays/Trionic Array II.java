package Arrays;

/**
    Last Attempt: 03 Feb 2026
    LeetCode Link: https://leetcode.com/problems/trionic-array-ii
    Approach: 
        1. Find the decreasing subarray in the big array
        2. Move right while strictly increasing and keep track of max
        3. Move left while strictly decreasing and keep track of max
        4. Repeat step 1 from where you left in step 2
    Time(n): O(n)
    Space(n): O(1)
*/
class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long maxTrionicSum = Long.MIN_VALUE;

        int peakIndex = 0;

        // Iterate through array looking for a valid peak
        while (peakIndex < n - 1) {

            // Check if current index is a local peak (strictly greater than neighbors)
            if (peakIndex != 0
                    && nums[peakIndex - 1] < nums[peakIndex] // to avoid plateau on left
                    && nums[peakIndex] > nums[peakIndex + 1]) {

                long currentSum = nums[peakIndex];
                int right = peakIndex;

                // Step 1: move right while strictly decreasing
                while (right < n - 1 && nums[right] > nums[right + 1]) {
                    currentSum += nums[right + 1];
                    right++;
                }

                // If we reached the end, no increasing part exists
                if (right == n - 1)
                    break;

                // If plateau occurs, trionic pattern is invalid
                if (nums[right] == nums[right + 1]) {
                    peakIndex++;
                    continue;
                }

                // Step 2: move right while strictly increasing
                right++;
                currentSum += nums[right];
                long bestRightSum = currentSum;

                while (right < n - 1 && nums[right] < nums[right + 1]) {
                    currentSum += nums[right + 1];
                    bestRightSum = Math.max(bestRightSum, currentSum);
                    right++;
                }

                // Step 3: expand left while strictly decreasing
                int left = peakIndex - 1;
                currentSum = bestRightSum + nums[left];
                long bestTotalSum = currentSum;

                while (left != 0 && nums[left] > nums[left - 1]) {
                    currentSum += nums[left - 1];
                    bestTotalSum = Math.max(bestTotalSum, currentSum);
                    left--;
                }

                // Update global maximum
                maxTrionicSum = Math.max(maxTrionicSum, bestTotalSum);

                // Skip processed elements
                peakIndex = right - 1;

            } else {
                peakIndex++;
            }
        }

        return maxTrionicSum;
    }
}
