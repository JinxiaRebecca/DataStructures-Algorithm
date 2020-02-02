package com.demo.linkedlist;



/**
 * @author:Rebecca Jin
 * @date: 2020/2/1,17:37
 * @version: 1.0
 * 利用水浒英雄排名案例演示单链表的创建，删除，修改
 */
public class SinglieLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero5 = new HeroNode(5, "关胜", "大刀");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        SingleLinkedList linkedList = new SingleLinkedList();
        /*linkedList.add(hero1);
        linkedList.add(hero3);
        linkedList.add(hero2);
        linkedList.add(hero5);
        linkedList.add(hero4);*/
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero5);
        linkedList.addByOrder(hero4);

        System.out.println("删除前");
        linkedList.list();


        /*linkedList.delNode(hero3);
        System.out.println("删除后");*/
        /*System.out.println("修改后~~~~~");
        linkedList.update(new HeroNode(3,"吴用","天机星"));
        linkedList.list();*/
        //
        /*HeroNode node = findNode(linkedList, 2);
        System.out.println("倒数第2个节点：" + node);*/

        System.out.println("反转后的链表如下");
        reverseSingleLinkedlist(linkedList);
        linkedList.list();


    }

    /**
     * 查找倒数第k个节点：
     * 思路：1.先判断链表是否为空，为空，则直接返回
     * 2.遍历链表，得到链表的总节点数size
     * 3.遍历到size-index则是
     *
     * @param index
     * @return
     */
    public static HeroNode findNode(SingleLinkedList linkedList, int index) {
        HeroNode head = linkedList.getHead();
        if (head.getNext() == null) {
            return null;
        }
        HeroNode temp = head.getNext();
        int size = 0;
        while (true) {
            if (temp == null) {
                break;
            }
            size++;
            temp = temp.getNext();
        }
        if (index < 0 || index > size) {
            return null;
        }

        temp = head.getNext();

        for (int i = 0; i < (size - index); i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * 实现单向链表的反转
     * 思路：定义一个新的节点，从头遍历链表，没遍历一个取出一个放到新节点对应的链表的最前端，
     * 最终将head的next指向新节点的next
     *
     * @param linkedList
     */
    public static void reverseSingleLinkedlist(SingleLinkedList linkedList) {
        HeroNode head = linkedList.getHead();
        if(head.getNext() == null || head.getNext().getNext()==null){
            return;
        }
        //创建一个新的节点
        HeroNode newNode = new HeroNode();

        HeroNode curr = head.getNext();//指向当前节点
        HeroNode next = null;//指向当前节点的下一个节点
        //遍历原来的链表，取出节点并放在新链表的前端
        while(curr!=null){
            next = curr.getNext();
            curr.setNext(newNode.getNext());//curr的下一个节点指向新的链表的最前端
            newNode.setNext(curr);
            curr = next;
        }
            head.setNext(newNode.getNext());
    }

}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");//头节点，不存放具体的数据，作用就是指向链表的头部

    /**
     * 给链表添加数据，不考虑排名顺序，直接加到链表的后端
     * 思路：创建临时节点，指向当前的链表的节点，遍历找到链表的后端，将数据加入
     *
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        //遍历寻找链表的尾部
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();//temp后移
        }
        temp.setNext(node);
    }

    /**
     * 考虑英雄的排名，添加英雄，如果排名已经存在，则提示不能加入
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        //创建临时变量，位于添加位置的前一个节点，否则添加不进去
        HeroNode temp = head;
        boolean flag = false;//表示英雄的排名是否存在，默认为不存在
        while (true) {
            //表示找到链表尾部，结束
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == node.getNo()) {//表示排名已经存在
                flag = true;
                break;
            } else if (temp.getNext().getNo() > node.getNo()) {
                break;
            }
            temp = temp.getNext();
        }
        if (!flag) {
            //node的下一个节点指向temp的下一个节点
            node.setNext(temp.getNext());
            //node指向temp的下一个节点
            temp.setNext(node);

        } else {
            System.out.println("英雄排名已经存在");
        }

    }

    /**
     * 删除节点：找到删除节点的前一个节点temp,将temp.next=temp.next.next;
     *
     * @param node
     */
    public void delNode(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到删除的节点，默认为没有
        //找需要删除的节点的位置
        while (true) {
            if (temp.getNext() == null) {//找到链表的尾部
                break;
            }
            if (temp.getNext().getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        if (flag) {
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.println("删除的节点不存在");
        }

    }

    /**
     * 修改节点的属性，规定节点的no不能改
     *
     * @param node
     */
    public void update(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.getNext().setName(node.getName());
            temp.getNext().setNickname(node.getNickname());
        } else {
            System.out.println("需要修改的节点不存在");
        }

    }

    //查找有效节点的个数
    public int countsOfNodes() {
        int counts = 0;
        if (head.getNext() == null) {//节点为空
            return counts;
        }
        HeroNode temp = head.getNext();
        while (temp != null) {
            counts++;
            temp = temp.getNext();
        }
        return counts;
    }


    /**
     * 遍历链表，并显示数据
     */
    public void list() {
        HeroNode temp = head.getNext();
        if (head.getNext() == null) {
            System.out.println("链表为空，不能遍历");
            return;
        }
        while (true) {
            System.out.println(temp);
            //到链表尾部
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
    }

    public HeroNode getHead() {
        return this.head;
    }

}


class HeroNode {
    private int no;//英雄的排名
    private String name;//英雄名
    private String nickname;//昵称
    private HeroNode next;//指向下一个节点

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}