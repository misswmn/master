package com.roncoo.master.sort;

/**
 * 快速排序
 *
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/15 12:21
 */
public class QuickSort {
    private static final int[] arrays = new int[]{2, 6, 1, 3, 5};

    private static void quickSort(int[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound) return;
        partition(arr, leftBound, rightBound);
    }

    // 分区
    private static void partition(int[] arr, int leftBound, int rightBound) {
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;
        while (left < right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    private static void printArr() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        quickSort(arrays, 0, arrays.length - 1);
        printArr();
    }
}
