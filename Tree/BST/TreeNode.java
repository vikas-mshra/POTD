package Tree.BST;

import java.util.*;
/**
    Last Attempt: 08 Feb 2026
    LeetCode Link: https://leetcode.com/problems/balance-a-binary-search-tree/
    Approach: Do a inorder traversal to get the sorted list from a BST then build a new balanced BST by picking the middle element as the root and recursively doing the same for the left and right halves.
    Time(n): O(n)
    Space(n): O(n)
*/

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    private List<Integer> nodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inorder(root);
        return buildTree(0, nodes.size() - 1);
    }

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        nodes.add(root.val);
        inorder(root.right);
    }

    private TreeNode buildTree(int start, int end) {
        if (start > end)
            return null;

        // Pick the middle element to ensure the tree is balanced
        int mid = start + ((end - start) >> 1);
        TreeNode node = new TreeNode(nodes.get(mid));

        // Recursively build left and right subtrees
        node.left = buildTree(start, mid - 1);
        node.right = buildTree(mid + 1, end);

        return node;
    }
}