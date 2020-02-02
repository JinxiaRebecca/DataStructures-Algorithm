package com.demo.linkedlist;

/**
 * @author:Rebecca Jin
 * @date: 2020/2/2,8:42
 * @version: 1.0
 * 双向链表的增删改查
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");
        Node node4 = new Node(4, "关胜");
        Node node5 = new Node(5, "公孙胜");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(node1);
        linkedList.add(node4);
        linkedList.add(node3);
        linkedList.add(node5);
        linkedList.add(node2);
        /*linkedList.addByOrder(node1);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node5);
        linkedList.addByOrder(node2);*/

        System.out.println("添加的链表数据如下：");
        linkedList.list();

        linkedList.delNode(6);
        System.out.println("删除一个节点后的数据如下：");
        linkedList.list();


    }
}

class DoubleLinkedList{
    public Node head = new Node(0,"");

    //给链表添加数据
    public void add(Node node){
        Node temp = head;
        while(true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;

    }

    //按照顺序添加数据
    public void addByOrder(Node node){
        Node curr = head;
        boolean flag = false;//表示排名是否存在，默认为不存在
        while(true){
            if(curr.next==null){
                break;
            }
            if(curr.next.no==node.no){
                flag = true;
                break;
            }
            if(curr.next.no>node.no){
                break;
            }
            curr = curr.next;
        }
        if(!flag){
            curr.next = node ;
            node.pre = curr;
        }else{
            System.out.println("添加的英雄排名存在");
        }

    }

    //删除链表的数据
    public void delNode(int no){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;//表示是否找到需要删除的节点
        Node temp = head.next;
        while(true){
            if(temp == null){//找到了链表的尾部
               break;
            }
            if(temp.no==no){
               flag = true;
               break;
            }
            temp = temp.next;
        }
        if(flag){
           temp.pre.next = temp.next;
           if(temp.next!=null){
               temp.next.pre = temp.pre;
           }

        }else{
            System.out.println("删除的节点没有找到");
        }
    }

    //遍历链表
    public void list(){
        if(head.next==null){
            return;
        }
        Node temp = head.next;
        while(true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;//temp后移
        }
    }
}

class Node{
    public int no;
    public String name;
    public Node pre;//指向前一个节点
    public Node next;//指向后一个节点

    public Node() {
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
