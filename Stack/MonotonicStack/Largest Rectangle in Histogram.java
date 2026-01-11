package Stack.MonotonicStack;

import java.util.*;
/**
    Last Attempt: 11 Jan 2026
    LeetCode Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
    Approach: This solution uses a monotonic increasing stack to track bars of increasing height. For each bar, if it is shorter than the bar on top of the stack, we pop from the stack and calculate the area using the popped bar as height and the current index as the right boundary. This way, we can determine the largest rectangle that each bar can form. After the iteration, we process any remaining bars in the stack by considering the full width to the end of the array. This guarantees we account for all possible rectangles.
    Time(n): O(n)
    Space(n): O(n)
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<int[]> stack = new Stack<>(); // Stores pairs of (height, starting index)

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int start = i;
            // Pop taller bars and compute area
            while (!stack.isEmpty() && stack.peek()[0] > heights[i]) {
                int[] top = stack.pop();
                int height = top[0];
                int index = top[1];
                maxArea = Math.max(maxArea, height * (i - index));
                start = index;
            }
            stack.push(new int[] { heights[i], start });
        }

        // Compute remaining areas in stack
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            int height = top[0];
            int index = top[1];
            maxArea = Math.max(maxArea, height * (n - index));
        }

        return maxArea;
    }
}