package com.wonders;

/**
 * @author: zph
 * @data: 2019/01/07 20:58
 */
public class ExtLinkedList<E> {
    //链表的实际长度
    private int size;

    private Node first;

    private Node last;

    public ExtLinkedList() {
        this.size=0;
    }

    public void add(E e){
        Node node = new Node(e);
        if(size==0){
            first=node;
        }else{
            node.prev=last;
            last.next=node;
        }

        last=node;
        size++;
    }

    public void add(int index,E e){
        checkIndex(index);
        //判断如果index为最后一个位置，执行普通插入，
        if(index==size){
            add(e);
        }else{
            Node newNode = new Node(e);
            Node oldNode = getNode(index);
            if(oldNode!=null){
                Node oldNode_prev = oldNode.prev;
                //如果是头结点插入，更新first
                if(oldNode_prev!=null){
                    oldNode_prev.next=newNode;
                    newNode.prev=oldNode_prev;
                }else{
                    first=newNode;
                }
                newNode.next=oldNode;
                oldNode.prev=newNode;
            }
            size++;
        }

    }

    public Object get(int index){
        checkIndex(index);
        return getNode(index).object;
    }

    public void remove(int index){
        checkIndex(index);
        Node oldNode = getNode(index);
        if(oldNode!= null){
            Node oldNode_next = oldNode.next;
            Node oldNode_prev = oldNode.prev;
            if(oldNode_next!=null){
                oldNode_prev.next=oldNode_next;
                oldNode.next=null;
            }else{
                last=oldNode_prev;
            }
            if(oldNode_prev!=null){
                oldNode_next.prev=oldNode_prev;
                oldNode.prev=null;
            }else{
                first=oldNode_next;
            }
            oldNode.object = null;
            size--;
        }
    }

    private Node getNode(int index){
        Node node = null;
        if(first!=null){
            node=first;
            for(int i=0;i<index;i++){
                node = node.next;
            }
        }
        return node;
    }


    private void checkIndex(int index) {
        if(!isElementIndex(index)){
            throw new IndexOutOfBoundsException("查询越界");
        }
    }

    private boolean isElementIndex(int index) {
        return index>=0&&index<size;
    }


    private class Node{
        private Node next;
        private Node prev;
        private Object object;

        public Node() {
        }

        public Node(Object object) {
            this.object=object;
        }

        public Node(Node next, Node prev, E object) {
            this.next = next;
            this.prev = prev;
            this.object = object;
        }
    }

    public static void main(String[] args) {
        ExtLinkedList<String> extLinkedList = new ExtLinkedList<String>();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("e");
        // System.out.println("删除之前:" + extLinkedList.get(2)); // C
        extLinkedList.add(0, "f");// fabce
        // System.out.println("删除之后:" + extLinkedList.get(2));// E
        // 其实从头查到尾### 效率不高，查询算法#####折半查找
        for (int i = 0; i < extLinkedList.size; i++) {
            System.out.println(extLinkedList.get(i));
        }
        // System.out.println(extLinkedList.get(2));
    }

}
