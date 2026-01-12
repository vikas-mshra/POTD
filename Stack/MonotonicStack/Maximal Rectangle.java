package Stack.MonotonicStack;

import java.util.*;
/**
    Last Attempt: 11 Jan 2026
    LeetCode Link: https://leetcode.com/problems/maximal-rectangle/
    Approach: The idea is to transform the 2D binary matrix into a series of 1D arrays by processing the matrix row by row. For each row, we build a histogram where each column represents the number of consecutive '1's ending at the current row. If the current cell contains '1', we increment the height of that column; otherwise, we reset it to 0. This effectively “merges” the current row with the rows above it and converts the 2D problem into a histogram problem for each row. For every such histogram, we then apply the Largest Rectangle in Histogram algorithm (using a monotonic increasing stack) to compute the maximum rectangular area for that row. The maximum area obtained across all rows is the answer.
    Time(n): O(m * n)
    Space(n): O(m * n)
*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // heights[j] = height of consecutive '1's ending at current row in column j
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                heights[c] = matrix[r][c] == '1' ? heights[c] + 1 : 0;
            }
            // Treat current row's heights as a histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>(); // {height, leftBoundaryIndex}

        for (int i = 0; i < heights.length; i++) {
            int leftBoundary = i;

            // Current bar limits the height of previous taller bars
            while (!stack.isEmpty() && stack.peek()[0] > heights[i]) {
                int[] bar = stack.pop();
                int height = bar[0];
                int startIndex = bar[1];

                maxArea = Math.max(maxArea, height * (i - startIndex));
                leftBoundary = startIndex;
            }

            stack.push(new int[] { heights[i], leftBoundary });
        }

        // Remaining bars extend to the end of the histogram
        int n = heights.length;
        while (!stack.isEmpty()) {
            int[] bar = stack.pop();
            int height = bar[0];
            int startIndex = bar[1];

            maxArea = Math.max(maxArea, height * (n - startIndex));
        }

        return maxArea;
    }
}
