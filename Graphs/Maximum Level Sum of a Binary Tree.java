package Graphs;

import java.util.*;

/**
    Last Attempt: 05 Jan 2026
    LeetCode Link: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree
    Approach: The only way to find the max is to calculate it. Use queue
    Time(n): O(n)
    Space(n): O(n)
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        long maxRowTotal = 0;
        int maxRowTotalIndex = 0;

        int currLevel = 0;
        while (!queue.isEmpty()) {
            long currLevelTotal = 0;

            int size = queue.size();
            while (size-- > 0) {
                TreeNode treeNode = queue.remove();
                currLevelTotal += treeNode.val;

                if (treeNode.left != null)
                    queue.add(treeNode.left);
                if (treeNode.right != null)
                    queue.add(treeNode.right);
            }
            currLevel++; // level which we finish iterating

            if (currLevel == 1 || currLevelTotal > maxRowTotal) {
                maxRowTotal = currLevelTotal;
                maxRowTotalIndex = currLevel;
            }

        }

        return maxRowTotalIndex;
    }
}