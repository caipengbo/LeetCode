package math;

import java.util.*;

/**
 * Title: 264.丑数2
 * Desc: 找到第n个丑数
 * Created by Myth-Lab on 11/4/2019
 */
public class P264UglyNumber2 {
    // 生成丑数，放到什么容器里？ 如何取出
    // 个人错误代码
    public int nthUglyNumber(int n) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        while (set.size() < n) {
            TreeSet<Integer> addSet = new TreeSet<>();
            for (Integer ele : set) {
                addSet.add(ele * 2);
                addSet.add(ele * 3);
                addSet.add(ele * 5);
            }
            set.addAll(addSet);
        }
        List<Integer> arr = new ArrayList<>(set);
        return arr.get(n-1);
    }
    // 重点：如何维护顺序！！
    // 修改后的代码
    public int nthUglyNumber2(int n) {
        // 类似于归并，维护三个队列（*2队列，*3队列，*5队列）
        // 每次求 min(q1,q2,q3),直到n
        if (n <= 1) return n;
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queue5 = new LinkedList<>();
        int uglyNumber = 1;
        for (int i = 1; i < n; i++) {
            queue2.add(uglyNumber*2);
            queue3.add(uglyNumber*3);
            queue5.add(uglyNumber*5);
            uglyNumber = Math.min(Math.min(queue2.peek(), queue3.peek()), queue5.peek());
            if (uglyNumber == queue2.peek()) queue2.poll();
            if (uglyNumber == queue3.peek()) queue3.poll();
            if (uglyNumber == queue5.peek()) queue5.poll();
        }
        return uglyNumber;
    }
    public int nthUglyNumber3(int n) {
        // 类似于归并，维护三个队列（*2队列，*3队列，*5队列）
        // 每次求 min(q1,q2,q3),直到n
        if (n <= 1) return n;
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queue5 = new LinkedList<>();
        int uglyNumber = 1;
        for (int i = 1; i < n; i++) {
            queue2.add(uglyNumber*2);
            queue3.add(uglyNumber*3);
            queue5.add(uglyNumber*5);
            uglyNumber = Math.min(Math.min(queue2.peek(), queue3.peek()), queue5.peek());
            if (uglyNumber == queue2.peek()) queue2.poll();
            if (uglyNumber == queue3.peek()) queue3.poll();
            if (uglyNumber == queue5.peek()) queue5.poll();
        }
        return uglyNumber;
    }

    public static void main(String[] args) {
        P264UglyNumber2 p264 = new P264UglyNumber2();
        System.out.println(p264.nthUglyNumber2(10));
    }
}
