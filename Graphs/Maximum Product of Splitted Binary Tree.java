package Graphs;

/**
    Last Attempt: 06 Jan 2026
    LeetCode Link: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
    Approach: What would you do if you have to do it on pen and paper, do that. Calculate the total and iterate the graph and start cutting each edge and calculate the max
    Time(n): O(n)
    Space(n): O(n)
*/
class Solution {
    private long total;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        total = calculateTotal(root);
        findMaxProduct(root);
        return (int) (maxProduct % MOD);
    }

    private long calculateTotal(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + calculateTotal(root.left) + calculateTotal(root.right);
    }

    private long findMaxProduct(TreeNode root) {
        if (root == null)
            return 0;

        long left = findMaxProduct(root.left);
        long right = findMaxProduct(root.right);

        long subtreeSum = root.val + left + right;
        long product = subtreeSum * (total - subtreeSum);

        maxProduct = Math.max(maxProduct, product);

        return subtreeSum;
    }
}