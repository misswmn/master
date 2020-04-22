package com.roncoo.master.sort;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/14 17:02
 */
public class BubbleSort {
    private static final int[] arrays = new int[]{2, 6, 1, 3, 5};

    private static void bubbleSort() {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays.length - 1 - i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int t = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = t;

                    printArr();
                }
            }
        }
    }

    private static void printArr() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        bubbleSort();
        printArr();
    }
}
