package com.roncoo.master.sort;

/**
 * 插入排序
 *
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/14 19:32
 */
public class InsertionSort {
    private static final int[] arrays = new int[]{2, 6, 1, 3, 5};

    private static void insertionSort() {
        for (int i = 1; i < arrays.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arrays[j] < arrays[j - 1]) {
                    swap(arrays, j, j - 1);
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void printArr() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        insertionSort();
        printArr();
    }
}
