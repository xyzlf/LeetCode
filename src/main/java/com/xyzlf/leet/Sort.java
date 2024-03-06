package com.xyzlf.leet;

import java.util.Arrays;

/**
 * @author xyzlf
 * @date 2024/3/5 14:07
 */
public class Sort {

    public static void main(String[] args) {
//        int[] array = {4, 6, 8, 3, 9, 2, 1, 5, 7};
//        print(array);
//
//        System.out.println("\n------分割线-----\n");
//        //选择排序
//        int[] selectResult = selectSort(array);
//        print(selectResult);
//
//        System.out.println("\n------分割线-----\n");
//
//        //冒泡排序
//        int[] bubbleResult = bubbleSort(array);
//        print(bubbleResult);

        System.out.println("\n------分割线-----\n");

        //快速排序
        int[] a = {2, 4, 6, 1, 3, 7, 9, 8, 5};
        quickSort2(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序
     *
     * @param array 源数组
     * @return 排序后的数组
     */
    public static int[] selectSort(int[] array) {
        if (null == array) {
            return null;
        }
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);

        for (int i = 0; i < newArray.length; i++) {
            int minIndex = i;
            for (int j = i; j < newArray.length; j++) {
                if (newArray[minIndex] > newArray[j]) {
                    minIndex = j;
                }
            }
            int temp = newArray[minIndex];
            newArray[minIndex] = newArray[i];
            newArray[i] = temp;
        }
        return newArray;
    }

    /**
     * 冒泡排序
     *
     * @param array 源数组
     * @return 排序后的数组
     */
    public static int[] bubbleSort(int[] array) {
        if (null == array) {
            return null;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "，");
        }
    }

    /**
     * 快速排序
     * //时间复杂度O(n*logn)，空间复杂度O(n*logn)
     *
     * @param arr        源数组
     * @param startIndex 开始索引
     * @param endIndex   结束索引
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            //找出基准
            int partition = partition(arr, startIndex, endIndex);
            System.out.println("partition:" + partition);
            //分成两边递归进行
            quickSort(arr, startIndex, partition - 1);
            quickSort(arr, partition + 1, endIndex);
        }
    }

    //找基准
    //int[] a = {2, 4, 6, 1, 3, 7, 9, 8, 5};
    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            //找到left比基准大，right比基准小，进行交换
            if (left < right) {
                swap(arr, left, right);
            }
        }
        //第一轮完成，让left和right重合的位置和基准交换，返回基准的位置
        swap(arr, startIndex, left);
        return left;
    }

    //两数交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //int[] a = {2, 4, 6, 1, 3, 7, 9, 8, 5};
    public static void quickSort2(int[] a, int start, int end) {
        if (start < end) {
            int stard = a[start]; //基准

            System.out.println("基准值：" + stard + " left:" + start + " right:" + end);

            int left = start;
            int right = end;
            while (left < right) {
                //从右边开始直到找到比基准小的数
                while (left < right && a[right] >= stard) {
                    right--;
                }
                a[left] = a[right];
                //从左边开始直到找到比基准大的数
                while (left < right && a[left] <= stard) {
                    left++;
                }
                a[right] = a[left];

                System.out.println(Arrays.toString(a));
            }
            a[left] = stard;

            System.out.println("xxxx:" + Arrays.toString(a));
            System.out.println("start:" + start + " left:" + left + " right:" + right);

            quickSort2(a, start, left - 1);
            quickSort2(a, right + 1, end);
        }
    }

}
