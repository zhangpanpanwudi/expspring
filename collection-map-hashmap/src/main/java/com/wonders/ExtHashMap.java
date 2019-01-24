package com.wonders;

/**
 * @author: zph
 * @data: 2019
 * /01/10 22:00
 */
public class ExtHashMap<K,V> implements ExtMap<K,V> {
    //table实际的大小
    private int size;
    //存放数据
    Node[] table=null;
    //hashmap的负载因子，负载因子越小，hash冲突的概率越小
    float DEFAULT_LOAD_FACTOR = 0.5f;
    //table的默认大小
    static int DEFAULT_INITIAL_CAPACITY = 16; // 16

    public V put(K k, V v) {
        //1、首先判断数组是否为空，如果为空，初始化数组
        if(table==null){
            table= new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2、判断数组是否需要扩容，
        if(size>(DEFAULT_LOAD_FACTOR*DEFAULT_INITIAL_CAPACITY)){
            //对table进行扩容
            resize();
        }
        //3、根据key计算index值，
        int index = getIndex(k, DEFAULT_INITIAL_CAPACITY);
        Node node = table[index];
        Node<K, V> newNode = null;
        //如果节点为空，表示没有hash冲突，
        if(node==null){
            newNode = new Node<K, V>(k,v,null);
            size++;
        }else{
            Node<K, V> nextNode = node;
            while (nextNode!=null){
                Object nodeKey = node.getKey();
                //hash碰撞，同时key值相等，更新value
                if(nodeKey.equals(k)||nodeKey==k){
                    return (V) node.setValue(v);
                }
                nextNode=nextNode.next;
            }
            //hash碰撞，但是没有相同key,链表上新增一个节点
            newNode = new Node<K, V>(k,v,node);
            size++;
        }
        table[index]=newNode;
        return null;
    }

    /**
     * table扩容方法
     */
    private void resize() {
        //1、生成新的数组
        Node[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1];
        //2、重新计算index，将旧数据放到新的table中
        for(int i=0;i<size;i++){
            Node<K, V> oldNode = table[i];
            while(oldNode!=null){
                table[i]=null;
                K oldNodeKey = oldNode.getKey();
                int newIndex = getIndex(oldNodeKey, newTable.length);

                Node newNode = newTable[newIndex];
                Node oldNode1=oldNode.next;
                if(newNode!=null){
                    oldNode.next=newNode;
                }else{
                    oldNode.next=null;
                }
                newTable[newIndex]=oldNode;
                oldNode=oldNode1;
            }
        }
        table = newTable;
        DEFAULT_INITIAL_CAPACITY=newTable.length;
        newTable=null;
    }

    private int getIndex(K oldNodeKey, int length) {
        int hashCode = oldNodeKey.hashCode();
        int index =hashCode%length;
        return index;
    }

    public V get(K k) {
        Node<K, V> node = getNode(table[getIndex(k, DEFAULT_INITIAL_CAPACITY)], k);
        return node == null ? null : node.value;
    }

    public Node<K, V> getNode(Node<K, V> node, K k) {
        while (node != null) {
            if (node.getKey().equals(k)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    // 测试方法.打印所有的链表元素
    void print() {

        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.print("下标位置[" + i + "]");
            while (node != null) {
                System.out.print("[ key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node = node.next;
                // if (node.next != null) {
                // node = node.next;
                // } else {
                // // 结束循环
                // node = null;
                // }

            }
            System.out.println();
        }

    }

    private class Node<K,V> implements Entry<K,V>{
        //map中的key
        private K key;
        //map中的value
        private V value;
        //下一个节点node
        private Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node() {
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

}
