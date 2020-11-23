package com.chen.algorithm.sort;

import java.util.Arrays;

import static com.chen.algorithm.sort.PartitionArray.swap;

/**
 * @author: Chentian
 * @date: Created in 2020/11/23 22:47
 * @desc
 */
public class HeapSort implements Sort{
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        heapify(arr);
        int heapSize = arr.length ;
        while (heapSize > 0){
            swap(arr, 0, --heapSize );
            siftDown(arr,0, heapSize);
        }
    }

    /**
     * 将任意数组整理成大根堆 O(n)
     * @param arr 待整理数组
     */
    private void heapify(int[] arr){
        for (int i = 0 ; i < arr.length; i++){
            siftUp(arr, i);
        }
    }

    /**
     * 对当前节点k进行上浮操作，维护大根堆 O(logn)
     * @param arr 待整理数组
     * @param k 当前节点的下标k
     */
    private void siftUp(int[] arr, int k){
        while (k > 0){
            int parent = (k - 1) >> 1 ;
            if(arr[k] <= arr[parent]){
                return;
            }
            swap(arr, k , parent);
            k = parent;
        }
    }

    /**
     * 对当前节点k进行下沉操作，维护大根堆 O(logn)
     * @param arr 待整理数组
     * @param k 当前节点的下标k
     * @param heapSize 堆的元素数量
     */
    private void siftDown(int[] arr, int k, int heapSize){
        while ( k < heapSize ){
            int left = 2 * k + 1 ;
            if(left >= heapSize){
                return;
            }
            int right = 2 * k + 2;
            int maxIndex = (right < heapSize && arr[right] > arr[left]) ? right : left;
            if( arr[k] >= arr[maxIndex] ){
                return;
            }
            swap(arr, k , maxIndex);
            k = maxIndex;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,3,3,4,5};
        new HeapSort().heapify(arr);
        System.out.println(Arrays.toString(arr));

        new HeapSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        //用对数器测试
        System.out.print("对数器测试结果：");
        new SortValidator().validate(5, 10, 500000,
                (int[] data) -> new HeapSort().sort(data));
    }

}
