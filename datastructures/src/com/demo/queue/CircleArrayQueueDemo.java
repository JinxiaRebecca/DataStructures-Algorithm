package com.demo.queue;

import java.util.Scanner;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/1,16:50
 * @version: 1.0
 * 数组模拟环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("菜单");
        boolean flag = true;
        CircleArray queue = new CircleArray(5);
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

class CircleArray{
    int[] arr;//队列中的数组
    int front;//队列的头部，指向队列头的第一个数据
    int rear;//队列的尾部，指向队列的最后一个数据的后一个位置,空出一个空间作为约定，初始值默认为0
    int maxSize;//数列的最大容量

    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];

    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //给队列中添加数据
    public void addQueue(int data){
        if(isFull()){
            System.out.println("队列已满，不能再添加数据");
            return;
        }
        arr[rear]=data;
        //rear后移
        rear = (rear+1)%maxSize;
    }

    //获取队列头的数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        int temp = arr[front];
        //后移也需要考虑取模
        front = (front+1)%maxSize;
        return temp;
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }

        for(int i=front;i<(front+getCounts());i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }


    public int getHead(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    //队列的有效数据个数
    public int getCounts(){
        return (rear-front+maxSize)%maxSize;
    }


}
