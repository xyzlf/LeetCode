package com.xyzlf.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xyzlf
 * @date 2024/3/31 23:00
 */
public class ArrayAlgorithm {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = findNearNumber(array, 3);
        System.out.println("index:" + index);

        int index2 = findTargetIndex2(array, 3);
        System.out.println("index2:" + index2);

        int[] comArray = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> list = findArrayCombination(comArray, 8);
        System.out.println(Arrays.toString(list.toArray()));

        int[] rightMove = {1, 2, 3, 4, 5, 6};
        rightShift(rightMove, 3);
        System.out.println(Arrays.toString(rightMove));

        snakeFillArray(4);
    }

    /**
     * 利用二分查找数组中，与目标值最接近的数
     * @param array 源数组
     * @param target 目标值
     * @return 接近目标的数
     */
    public static int findNearNumber(int[] array, int target) {
        if (null == array) {
            return -1;
        }
        int start = 0;
        int end = array.length;
        int middle = 0;

        while (start < end) {
            middle = (start + end) / 2;
            if (target == array[middle]) {
                return array[middle];
            } else if (Math.abs(target  - array[middle]) > Math.abs(target - array[middle + 1])) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return Math.abs(target  - array[middle]) > Math.abs(target - array[middle + 1]) ? array[middle + 1] : array[middle];
    }

    /**
     * 合并两个有序数组
     * @param array1 源数组1
     * @param array2 源数组2
     * @return 新数组
     */
    public static int[] mergeArray(int[] array1, int[] array2) {
        int array1Len = array1.length, array1Index = 0;
        int array2Len = array2.length, array2Index = 0;

        int[] newArray = new int[array1Len + array2Len];
        int newArrayIndex = 0;

        while (array1Index < array1Len && array2Index < array2Len) {
            if (array1[array1Index] < array2[array2Index]) {
                newArray[newArrayIndex++] = array1[array1Index];
                array1Index++;
            } else {
                newArray[newArrayIndex++] = array2[array2Index];
                array2Index++;
            }
        }

        while (array1Index < array1Len) {
            newArray[newArrayIndex++] = array1[array1Index++];
        }

        while (array2Index < array2Len) {
            newArray[newArrayIndex++] = array2[array2Index++];
        }

        return newArray;
    }

    /**
     * 请实现有重复数字的有序数组的二分查找。输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
     *
     * @param array 源数组
     * @param target 目标值
     * @return index
     */
    public static int findTargetIndex(int[] array, int target) {
        int right = array.length - 1;
        int left = 0;
        int middle = 0;

        while (left <= right) {
            middle = (right + left) / 2;
            if (array[middle] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left + 1;
    }

    public static int findTargetIndex2(int[] array, int target) {
        if (null == array) {
            return -1;
        }
        int len = array.length;
        if (array[len - 1] < target) {
            return len + 1;
        }

        int left = 0;
        int right = len;
        int middle = 0;

        while (left <= right) {
            middle = (left + right) / 2;

            if (array[middle] == target) {
                if (middle == 0 || array[middle - 1] != target) {
                    return middle + 1;
                } else {
                    right = middle - 1;
                }
            } else if (array[middle] > target) {
                if (middle == 0 || array[middle-1] < target) {
                    return middle + 1;
                } else {
                    right = middle;
                }
            } else if (array[middle] < target) {
                left = middle + 1;
            }
        }
        return len + 1;
    }

    /**
     * 组合总和：给定一个无重复元素的数组和一个目标数，找出所有可以使数字和为目标数的组合java实现
     *
     * @param array 源数组
     * @param target 目标值
     * @return 数组组合
     */
    static List<List<Integer>> comList = new ArrayList<>();
    public static List<List<Integer>> findArrayCombination(int[] array, int target) {
        if(null == array || array.length == 0) {
            return null;
        }
        comList.clear();

        Arrays.sort(array);
        List<Integer> arrayList = new ArrayList<>();
        dfs(array, target, 0, arrayList);
        return comList;
    }

    //{10, 1, 2, 7, 6, 1, 5};
    private static void dfs(int[] array, int target, int start, List<Integer> list) {
        if (target == 0) {
            if (!comList.contains(new ArrayList<>(list))) {
                comList.add(new ArrayList<>(list));
            }
        }
        for (int i = start; i < array.length; i++) {
            if (array[i] <= target) {
                list.add(array[i]);
                dfs(array, target - array[i], i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 实现数组的循环右移
     * eg: {1, 2, 3, 4, 5, 6}  2
     *     {5, 6, 1, 2, 3, 4}
     *
     * @param array 源数组
     * @param n 移动的步数
     * @return array
     */
    public static void rightShift(int[] array, int n) {
        n = n % array.length;

        for (int i=0; i<n; i++) {
            int temp = array[array.length-1];
            for (int j=array.length-1-1; j >=0; j--) {
                array[j+1] = array[j];
            }
            array[0] = temp;
        }
    }

    /**
     * 输入一个整数n，要求输出一个N*N蛇形矩阵，比如输入4，则输出如下蛇形阵
     * https://blog.csdn.net/qq_44322357/article/details/97266778
     *
     *  1   2   3   4
     *  12  13  14  5
     *  11  16  15  6
     *  10  9   8   7
     *
     * @param n
     */
    public static void snakeFillArray(int n) {
        int round = 0; //控制圈数
        int count = 1;
        int x = 0;
        int y = 0;

        int[][] array = new int[n][n];
        for (round = 0; round < n / 2; round++) {
            /**
             * 以下循环输出：
             * 1 2 3 4
             */
            x = round;
            for (y = round; y < n - round; y++) {
                array[x][y] = count++;
            }

            /**
             * 以下循环输出：
             * 1    2   3   4
             *              5
             *              6
             *              7
             */
            y = n - round - 1;
            for (x = round + 1; x < n - round - 1; x++) {
                array[x][y] = count++;
            }

            /**
             * 以下循环输出：
             * 1    2   3   4
             *              5
             *              6
             * 10   9   8   7
             */
            x = n - round - 1;
            for (y = n - round - 1; y >= round; y--) {
                array[x][y] = count++;
            }

            /**
             * 以下循环输出：
             * 1    2   3   4
             * 12           5
             * 11           6
             * 10   9   8   7
             */
            y = round;
            for (x = n - round - 1-1; x > round; x--) {
                array[x][y] = count++;
            }
        }

        if (n % 2 == 1) {
            array[n / 2][n / 2] = count;
        }

        for (x = 0; x < n; x++) {
            for (y = 0; y < n; y++) {
                System.out.print(array[x][y] + " ");
            }
            System.out.println("");
        }
    }

}
