package com.xyzlf.leet;

/**
 * @author xyzlf
 * @date 2024/3/4 18:22
 */
public class Common {

    public static void main(String[] args) {
        int f = jumpFloor(4);
        int f2 = jumpFloor2(4);
        System.out.println("   f=" + f + "   f2=" + f2);
    }

    /**
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * @param n 台阶数
     * @return int
     */
    public static int jumpFloor(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return jumpFloor(n-1) + jumpFloor(n-2);
    }

    //非递归实现
    public static int jumpFloor2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre1 = 1, pre2 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

}
