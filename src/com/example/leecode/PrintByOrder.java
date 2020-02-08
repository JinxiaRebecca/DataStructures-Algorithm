package com.example.leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/8,11:10
 * @version: 1.0
 * 某个打印机根据打印队列执行打印任务。打印任务分为九个优先级，分别采用数字1~9表示，数字越大优先级越高。
 * 打印机每次从队列头部取出第一个任务A，然后检查队列余下任务中有没有比A优先级更高的任务，如果有比A优先级高的任务，
 * 则将任务A放到队列尾部，否则执行任务A的打印。请编写一个程序，根据输入的打印队列，输出实际打印顺序
 *
 */
public class PrintByOrder {
    public static void main(String[] args) {
        int[] input = {1,3,9,8,4};
        int[] output = new int[input.length];
        printOrder(input,5,output);

    }

    static  void printOrder(int input[], int len, int output[]){
        List<Integer> list = new LinkedList<Integer>();
        for(int i=0;i<len;i++){
            list.add(input[i]);
        }

        int index = 0;
        while(list.size()>0){
            int head = ((LinkedList<Integer>) list).getFirst();
            boolean flag =false;
            for(int i=0;i<list.size();i++){
                if(head<list.get(i)){
                    flag = true;
                    break;
                }
            }
            if(flag){//存在比头部大的
                ((LinkedList<Integer>) list).removeFirst();
                ((LinkedList<Integer>) list).addLast(head);
            }else{
                ((LinkedList<Integer>) list).removeFirst();
                for(int i=0;i<len;i++){
                    if(input[i]==head){
                        output[index]=i;
                        index++;
                        break;
                    }
                }

            }
        }
        System.out.println(Arrays.toString(output));


    }
}

