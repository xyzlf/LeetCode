package com.xyzlf.leet;

import java.util.*;

/**
 * @author xyzlf
 * @date 2024/3/4 16:37
 *
 * 二叉树
 */
public class BinaryTree {

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        /**
         *            0
         *       1        2
         *    3    4   5     6
         *  7                   8
         */
        TreeNode root = new TreeNode(0);

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        TreeNode node7 = new TreeNode(7);

        TreeNode node8 = new TreeNode(8);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = node7;

        node6.right = node8;

        printTreeNode(root);

        int maxHeight = maxHeight(root);
        System.out.println("最大深度为：" + maxHeight);

        int maxWidth = maxWidth(root);
        System.out.println("最大宽度为：" + maxWidth);

        preOrderPrint(root);

        System.out.println("=====");

        TreeNode newTreeNode = reverseTreeNode(root);
        preOrderPrint(newTreeNode);


    }

    /**
     * 二叉树最大深度
     * @param root {@link TreeNode}
     * @return int
     */
    public static int maxHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 二叉树最大宽度
     * @param root {@link TreeNode}
     * @return int
     */
    public static int maxWidth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Integer width = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> numberQueue = new LinkedList<>();

        queue.offer(root);
        numberQueue.offer(1);

        while (!queue.isEmpty()) {

            int size = queue.size();
            int left = Integer.MAX_VALUE;
            int right = Integer.MIN_VALUE;

            for (int i = 0; i<size; i++) {
                TreeNode treeNode = queue.poll();
                Integer number = numberQueue.poll();

                left = Math.min(number, left);
                right = Math.max(number, right);

                if (null == treeNode) {
                    break;
                }

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                    numberQueue.offer(2 * number);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                    numberQueue.offer(2 * number + 1);
                }
            }
            width = Math.max(width, right - left + 1);
        }
        return width;
    }

    /**
     * 二叉树反转
     * @param root {@link TreeNode}
     * @return TreeNode
     */
    public static TreeNode reverseTreeNode(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode left = reverseTreeNode(root.left);
        TreeNode right = reverseTreeNode(root.right);

        if (null == left && null == right) {
            return root;
        }
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 二叉树先序遍历
     * @param root {@link TreeNode}
     */
    private static void preOrderPrint(TreeNode root) {
        if (null == root) {
            return;
        }
        System.out.println(root.value);
        preOrderPrint(root.left);
        preOrderPrint(root.right);
    }

    /**
     * 二叉树的蛇形打印
     * @param root {@link TreeNode}
     */
    public static void printTreeNode(TreeNode root) {
        if(null == root) {
            return;
        }
        List<Integer> numberList = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean flag = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tempNode = queue.poll();
                if (null == tempNode) {
                    continue;
                }
                if (!flag) {
                    tempList.add(tempNode.value);
                } else {
                    tempList.add(0, tempNode.value);
                }

                if (tempNode.left != null) {
                    queue.offer(tempNode.left);
                }

                if (tempNode.right != null) {
                    queue.offer(tempNode.right);
                }
            }

            numberList.addAll(tempList);
            flag = !flag;
        }

        System.out.println(Arrays.toString(numberList.toArray()));
    }

}
