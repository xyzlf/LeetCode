package com.xyzlf.leet;

import java.util.Stack;

/**
 * @author xyzlf
 * @date 2024/3/4 15:59
 *
 * 单链表反转算法
 */
public class ListReverse {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(" ----- 反转前 -----");
        ListNode tempNode = node1;
        while (tempNode != null) {
            System.out.println(tempNode.val);
            tempNode = tempNode.next;
        }

        System.out.println(" ----- 反转后 -----");
        ListNode newNode = reverseList2(node1);
        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

    /**
     * 迭代实现
     * @param root rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList(ListNode root) {
        ListNode preNode = null;
        while (root != null) {
            ListNode nextNode = root.next;
            root.next = preNode;
            preNode = root;
            root = nextNode;
        }
        return preNode;
    }

    /**
     * 递归实现
     * @param root rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList2(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }

        ListNode newHead = reverseList2(root.next);
        root.next.next = root;
        root.next = null;
        return newHead;
    }

    /**
     * 用栈实现
     * @param root rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList3(ListNode root) {
        if (null == root) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.next;
        }

        ListNode newHead = stack.pop();
        ListNode tempNode = newHead;
        while (!stack.isEmpty()) {
            tempNode.next = stack.pop();
            tempNode = tempNode.next;
        }
        tempNode.next = null;

        return newHead;
    }
}
