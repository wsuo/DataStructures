package top.wsuo.array;

/**
 * @Author shuo wang
 * @Date 2020/3/31 0031 14:33
 * @Version 1.0
 */
public class Array01 {
    private int[] array;
    private int size;

    private Array01(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    private void insert(int element, int index) {

        // index > size 的原因是在尾部插入也成立
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出数组的范围!");
        }

        // 如果超出长度限制
        if (size >= array.length) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size++;
    }

    private void output() {
        for (int i = 0; i < size; i++) {
            System.out.println("第"+ (i+1) + "个元素为: " + array[i]);
        }
    }

    // 数组扩容
    private void resize() {
        int[] array02 = new int[array.length * 2];
        // 数组复制
        System.arraycopy(array, 0, array02, 0, array.length);
        array = array02;
    }

    public static void main(String[] args) {
        Array01 array01 = new Array01(10);
        array01.insert(3, 0);
        array01.insert(7, 1);
        array01.insert(4, 2);
        array01.insert(5, 3);
        array01.insert(8, 2);
        array01.output();
    }

}
