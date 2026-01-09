package Graphs;

import java.util.*;
/**
    Last Attempt: 09 Jan 2026
    LeetCode Link: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes
    Approach: Use DFS to iterate the tree and based on some condition we would update the result
        1. both child are same, and our depth is >= maxDepthSeen
        2. either of child (depth) is > maxDepthSeen and the depth should be = currDepth + 1

    why? because we are always return the max(left, right) and we only want to update the result when the child is returning its own depth not the grandchild depth. Because grandchild has already updated the result when it was at the top of stack
    Time(n): O(n)
    Space(n): O(n)
*/
class Solution {
    TreeNode result; // Node selected based on DFS depth comparison
    int maxDepthSeen; // Maximum depth encountered so far during traversal

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        result = root;
        dfs(root, 1);
        return result;
    }

    private int dfs(TreeNode node, int currentDepth) {
        if (node.left == null && node.right == null)
            return currentDepth;

        int leftDepth = 0;
        int rightDepth = 0;

        if (node.left != null)
            leftDepth = dfs(node.left, currentDepth + 1);

        if (node.right != null)
            rightDepth = dfs(node.right, currentDepth + 1);

        // Case 1: both children exist
        if (leftDepth != 0 && rightDepth != 0) {

            /*
             * When both subtrees reach the same depth:
             * - If this depth is deeper than any previously recorded, update both maxDepthSeen and the selected node.
             * - If equal, we still update because this node is lower in the tree (encountered later in DFS), which makes it a tighter candidate.
             */
            if (leftDepth == rightDepth && leftDepth >= maxDepthSeen) {
                maxDepthSeen = leftDepth;
                result = node;
            }

        } else {
            // Case 2: only one child exists
            int childDepth = Math.max(leftDepth, rightDepth);

            /*
             * Update only when:
             * - This path extends deeper than any previously found
             * - The depth increase is exactly one level from this node, ensuring the update is tied to the direct child, not to the grandchild 
             */
            if (childDepth > maxDepthSeen && childDepth == currentDepth + 1) {
                maxDepthSeen = childDepth;
                result = (leftDepth != 0) ? node.left : node.right;
            }
        }

        // Propagate the deepest depth upward
        return Math.max(leftDepth, rightDepth);
    }
}
