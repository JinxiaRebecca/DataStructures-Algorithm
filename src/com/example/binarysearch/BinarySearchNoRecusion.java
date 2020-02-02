package com.example.binarysearch;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/2,17:51
 * @version: 1.0
 * 使用非递归实现二分法查找
 */
public class BinarySearchNoRecusion {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int index = binarySearch(arr, 5);
        System.out.println("查找的数据的下表为"+index);

    }

    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int mid = (right+left)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){//向左查找
                right = mid-1;
            }else{//向右查找
                left = mid+1;
            }
        }
        return -1;
    }
}
