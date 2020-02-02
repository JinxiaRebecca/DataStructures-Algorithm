package com.demo.linkedlist;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/2,10:32
 * @version: 1.0
 * Josephu  问题为：设编号为1，2，… n的n个小孩围坐一圈，约定编号为k（1<=k<=n）的小孩从1开始报数，
 * 数到m 的那个小孩出列，他的下一位又从1开始报数，数到m的那个小孩又出列，依次类推，直到所有小孩出列为止，
 * 由此产生一个出队编号的序列
 * 思路分析：创建一个不带头节点的环形单向链表，由k个节点起开始数数，数到第m的那个节点删除加入list,再
 * 依次数到m删除并加入list
 */
public class CircleSingleLinkedList {
    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        /*list.add(5);
        list.showCircle();*/
        list.countChild(1,3,5);

    }
}
class CircleLinkedList{
    ChildNode first = null;

    /**
     * 创建环形链表：1.创建一个第一个节点，指向链表的头部 first
     *              2.添加第一个节点时：fist 指向新的节点，新的节点的next指向first构成环
     *              3.创建临时变量curr,指向当前的节点，curr = first
     *              4.后面再添加节点时，curr.next=新节点，新节点的next=firt
     *              5.curr后移
     * @param nums 圈的人数，即环形链表的节点数
     */
    public void add(int nums){
        if(nums<1){
            System.out.println("人数不正确");
            return ;
        }
        ChildNode curr = null;
        for(int i=1;i<=nums;i++){
            ChildNode child = new ChildNode(i);
            if(i==1){
                first = child;
                child.next = first;//构成环
                curr = first;// curr指向第一个小孩
            }else{
                curr.next = child;
                child.next = first; //构成环
                curr  = curr.next; //curr后移
            }
        }

    }


    //遍历环形链表
    public void showCircle(){
        if(first==null){
            System.out.println("链表为空");
            return;
        }
        ChildNode child = first;
        while(true){
            System.out.println(child);
            if(child.next == first){//表示遍历到链表的尾部
                break;
            }
            child  = child.next;
        }
    }

    /**
     * 完成Josephu问题
     * @param k 表示从k开始数数
     * @param m  表示数了第几个小孩
     * @param nums 原始的圈圈的人数
     * @return 小孩出列的编号
     */
    public void countChild(int k,int m,int nums){
        if(k<0 || m>nums || nums<1){
            System.out.println("参数有误，请重新输入");
            return ;
        }
        //创建环形链表
        add(nums);
        //用辅助变量找到链表的尾部
        ChildNode helper = first;
        while(true){
         if(helper.next==first){
            break;
         }
         helper = helper.next;
        }

        //first和help一起移动k-1次，到第k个位置
        for(int i=1;i<=k-1;i++){
            first = first.next;
            helper = helper.next;
        }
        //当开始报数时，first和helper同时移动m-1下，直到圈中只剩下一个小孩
        while(true){
            if(helper==first){
               break;
            }
            //first和helper同时移动m-1下，first执向的就是需要出列的小孩
            for(int j=0;j<m-1;j++){
                first = first.next;
                helper = helper.next;
                System.out.println("第"+(j+1)+"次出列的小孩编号是"+first.no);
            }
            first = first.next;
            helper.next=first;

        }
        System.out.println("最后一次出圈的小孩编号是"+first.no);


    }


}

class ChildNode{
    public int no;
    public ChildNode next;

    public ChildNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "ChildNode{" +
                "no=" + no +
                '}';
    }
}
