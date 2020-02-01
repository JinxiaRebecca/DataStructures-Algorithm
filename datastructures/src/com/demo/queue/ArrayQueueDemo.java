package com.demo.queue;

import java.util.Scanner;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/1,15:20
 * @version: 1.0
 * 数组模拟队列(先进先出)，并完成队列的入队和出队；
 * 这种队列存在的问题是：数组不能复用
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("菜单");
        boolean flag = true;
        ArrayQueue queue = new ArrayQueue(5);
        while(flag){
            System.out.println("a(add):添加数据");
            System.out.println("s(show):显示队列数据");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("e(exit):退出程序");
            char key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'g':
                    queue.getQueue();
                    break;
                case 'h':
                    System.out.println(queue.getHead());
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default: break;
            }
        }


    }
}

class ArrayQueue{
    int[] arr;//队列中的数组
    int front;//队列的头部，指向队列头的前一个数据
    int rear;//队列的尾部，指向队列的最后一个数据
    int maxSize;//数列的最大容量

    //构造器
    public ArrayQueue(int maxSize){
        arr = new int[maxSize];
        front = -1;
        rear = -1;
        this.maxSize = maxSize;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return this.rear==this.front;
    }

    //判断队列是否已满
    public boolean isFull(){
        return this.rear==maxSize-1;
    }

    //给队列添加数据
    public void addQueue(int data){
        //先判断队列是否已满，满了就不能再添加数据
        if(isFull()){
            System.out.println("队列已满，不能再添加数据");
            return;
        }
        //rear后移
        rear++;
        arr[rear] = data;
    }

    //数据出队列
    public int getQueue(){
        if(isEmpty()){
           throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    //遍历显示队列数据
    public void showQueue(){
        if(isEmpty()){
           throw new RuntimeException("队列为空");
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列头的数据
    public int getHead(){
        if(isEmpty()){
           throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

}
