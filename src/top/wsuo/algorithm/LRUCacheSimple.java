package top.wsuo.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 一个更简单实用的LRUCache方案，使用LinkedHashMap即可实现。
 * LinkedHashMap提供了按照访问顺序排序的方案，内部也是使用HashMap+双向链表。
 * 只需要重写removeEldestEntry方法，当该方法返回true时，LinkedHashMap会删除最旧的结点。
 *
 * @author wjg
 *
 */
public class LRUCacheSimple {


    public static void main(String[] args) {
        LRUCacheSimple cache = new LRUCacheSimple(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private LinkedHashMap<Integer, Integer> map;

    /**
     * 只需要覆写LinkedHashMap的removeEldestEntry方法，
     * 在缓存已满的情况下返回true，内部就会自动删除最老的元素。
     * @param capacity 容量
     */
    private LRUCacheSimple(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    private int get(int key) {
        return map.getOrDefault(key, -1);
    }

    private void put(int key, int value) {
        map.put(key, value);
    }

}
