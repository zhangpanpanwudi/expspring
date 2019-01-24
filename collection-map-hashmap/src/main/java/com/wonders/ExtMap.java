package com.wonders;

/**
 * @author: zph
 * @data: 2019/01/10 21:46
 */
public interface ExtMap<K,V> {

    /**
     * 向集合中添加元素
     * @param k
     * @param v
     * @return
     */
    public V put(K k,V v);

    /**
     * 根据K从map中查询元素
     * @param k
     * @return
     */
    public V get(K k);

    /**
     * 获取集合元素个数
     * @return
     */
    public int size();

    interface Entry<K,V>{
        K getKey();

        V getValue();

        V setValue(V value);
    }
}
