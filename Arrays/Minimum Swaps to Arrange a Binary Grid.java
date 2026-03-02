package Arrays;

import java.util.*;
/**
    Last Attempt: 01 Mar 2026
    LeetCode Link: https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/
    Approach: Calculate the rightmost one index for each row and then try to place the valid row at each position. We would start from the top and try to place the valid row at each position. If the current row is not valid, we would find a row that satisfies the condition for this position and swap it with the current row. We would continue this process until we have placed all the rows in the correct position.
    Time(n): O(n^2)
    Space(n): O(n)
*/
class Solution {
    public int minSwaps(int[][] grid) {
        int size = grid.length;

        // rightmostOne[i] = index of the rightmost 1 in row i
        int[] rightmostOne = new int[size];

        for (int row = 0; row < size; row++) {
            rightmostOne[row] = getRightmostOneIndex(grid[row]);
        }

        int swaps = 0;

        // Try to place a valid row at each position
        for (int targetRow = 0; targetRow < size; targetRow++) {
            if (targetRow >= rightmostOne[targetRow])
                continue;

            // Find a row that satisfies the condition for this position
            for (int candidate = targetRow + 1; candidate < size; candidate++) {

                // Row is valid if its rightmost 1 is <= targetRow
                if (targetRow >= rightmostOne[candidate]) {

                    // Bring this row up using adjacent swaps
                    swaps += (candidate - targetRow);

                    int selectedValue = rightmostOne[candidate];

                    // Shift rows down to fill the gap
                    while (candidate > targetRow) {
                        rightmostOne[candidate] = rightmostOne[candidate - 1];
                        candidate--;
                    }

                    rightmostOne[targetRow] = selectedValue;
                    break;
                }
            }

            // If current row still invalid, it's impossible
            if (targetRow < rightmostOne[targetRow]) {
                return -1;
            }
        }

        return swaps;
    }

    // Returns index of the rightmost 1 in the row
    private int getRightmostOneIndex(int[] row) {
        int col = row.length - 1;

        while (col >= 0 && row[col] == 0) {
            col--;
        }

        // If no 1 exists, treat as valid with index 0
        return (col == -1) ? 0 : col;
    }
}