package Tricky;

import java.util.*;

/** 
    Last Attempt: 14 Jan 2026
    LeetCode Link: https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/description
    Approach: The idea is to identify all possible intersection points formed by the removed horizontal and vertical bars, since a square hole can only start at such intersections. The horizontal and vertical bar indices are first sorted so that consecutive removed bars can be detected easily. While iterating, consecutive bars are skipped to avoid redundant starting points. For each valid intersection, the algorithm expands downward along the horizontal bars and rightward along the vertical bars to count how many consecutive bars are removed in each direction. These counts determine the maximum possible square side length that can be formed from that intersection. The square area is computed using the minimum of the horizontal and vertical expansions, and the maximum area among all intersections is returned.
    Time(n): O(nm)
    Space(n): O(nm)
*/


class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {

        // Stores removed horizontal bar indices
        List<Integer> horizontalBars = new ArrayList<>();
        for (int bar : hBars)
            horizontalBars.add(bar);

        // Stores removed vertical bar indices
        List<Integer> verticalBars = new ArrayList<>();
        for (int bar : vBars)
            verticalBars.add(bar);

        // Sort bar indices to detect consecutive bars easily
        Collections.sort(horizontalBars);
        Collections.sort(verticalBars);

        // Maps each horizontal bar to all possible vertical bars
        // where a square hole could start
        Map<Integer, List<Integer>> possibleIntersections = new HashMap<>();

        // Find all valid (horizontal, vertical) starting points
        for (int i = 0; i < horizontalBars.size(); i++) {

            // Skip consecutive horizontal bars to avoid duplicate calculations
            if (i > 0 && horizontalBars.get(i) == horizontalBars.get(i - 1) + 1)
                continue;

            for (int j = 0; j < verticalBars.size(); j++) {

                // Skip consecutive vertical bars to avoid duplicate calculations
                if (j > 0 && verticalBars.get(j) == verticalBars.get(j - 1) + 1)
                    continue;

                // Store intersection point
                possibleIntersections
                        .computeIfAbsent(horizontalBars.get(i), k -> new ArrayList<>())
                        .add(verticalBars.get(j));
            }
        }

        int maxSquareArea = 0;

        // Evaluate square size for each valid intersection
        for (int startRow : horizontalBars) {
            List<Integer> startColumns = possibleIntersections.getOrDefault(startRow, Collections.emptyList());

            for (int startCol : startColumns) {

                // Count how many consecutive horizontal bars are removed downward
                int verticalLength = 1;
                int row = startRow;
                while (horizontalBars.contains(++row))
                    verticalLength++;

                // Count how many consecutive vertical bars are removed rightward
                int horizontalLength = 1;
                int col = startCol;
                while (verticalBars.contains(++col))
                    horizontalLength++;

                // Calculate maximum possible square side length
                int squareSide = Math.min(
                        1 + verticalLength, // top would always be 1
                        1 + horizontalLength); // left would always be 1

                // Update maximum square area
                maxSquareArea = Math.max(
                        maxSquareArea,
                        squareSide * squareSide);
            }
        }

        return maxSquareArea;
    }
}