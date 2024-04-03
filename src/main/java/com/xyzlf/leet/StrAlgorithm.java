package com.xyzlf.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xyzlf
 * @date 2024/3/31 23:00
 *
 * 字符串相关算法
 */
public class StrAlgorithm {

    public static void main(String[] args) {
        String ss = "abcabcab";
        int len = maxSubStringLength(ss);
        System.out.printf("len:" + len);
    }

    /**
     * 给定一个字符串，查找不存在重复字符的最长子串的长度
     *
     * @param str String
     * @return String
     */
    public static int maxSubStringLength(String str) {
        if (null == str) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int index = 0;
        int length = str.length();
        int maxLength = 0;

        for (int i = 0; i < length; i++) {
            if (i != 0) {
                set.remove(str.charAt(i - 1));
            }
            while (index < length && !set.contains(str.charAt(index))) {
                set.add(str.charAt(index));
                index++;
            }
            maxLength = Math.max(maxLength, set.size());
        }
        return maxLength;
    }
}
