package com.xyzlf.leet;

/**
 * @author xyzlf
 * @date 2024/3/5 10:22
 *
 * 回文相关算法
 */
public class Palindrome {

    public static void main(String[] args) {
        int num = 121;
        boolean palindromeNum = isPalindromeNumber(num);
        System.out.println("是否是回文数字:" + palindromeNum);

        String str = "abcba";
        boolean palindromeStr = isPalindromeString(str);
        System.out.println("是否是回文字符串:" + palindromeStr);
    }

    /**
     * 是否是回文字符串
     * @param data String
     * @return boolean
     */
    public static boolean isPalindromeString(String data) {
        int start = 0;
        int end = data.length() - 1;
        while (start < end) {
            if (data.charAt(start++) != data.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是回文数字
     * @param data int
     * @return boolean
     */
    public static boolean isPalindromeNumber(int data) {
        int sum = 0;
        final int origin = data;

        while (data != 0) {
            sum = sum * 10 + data % 10;
            data /= 10;
        }

        if (origin == sum) {
            return true;
        }
        return false;
    }

}
