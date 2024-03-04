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
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(" ----- 反转前 -----");
        ListNode tempNode = head;
        while (tempNode != null) {
            System.out.println(tempNode.val);
            tempNode = tempNode.next;
        }

        System.out.println(" ----- 反转后 -----");
        ListNode newHead = reverseList2(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * 迭代实现
     * @param head rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }

    /**
     * 递归实现
     * @param head rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //递的过程，一次次拆解问题
        ListNode newHead = reverseList2(head.next);
        //归的过程，反转
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 用栈实现
     * @param head rootNode
     * @return {@link ListNode}
     */
    public static ListNode reverseList3(ListNode head) {
        if (null == head) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode newHead = stack.pop();
        ListNode tempNode = newHead;
        while (!stack.isEmpty()) {
            tempNode.next = stack.pop();
            tempNode = tempNode.next;
        }
        //头节点next需要置为null
        tempNode.next = null;

        return newHead;
    }
}
