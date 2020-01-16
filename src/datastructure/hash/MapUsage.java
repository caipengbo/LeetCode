package datastructure.hash;


import java.util.HashSet;
import java.util.Set;

/**
 * Title: Java 中 Map的使用详解
 * Desc:
 * Created by Myth on 9/25/2019
 */
public class MapUsage {
    static class Key{
        Integer x;
        Integer y;
        Key(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            // Pair的一种写法
            //  31 有个很好的性能，即用移位和减法来代替乘法，可以得到更好的性能：
            //  31 * i == (i << 5）- i， 现代的 VM 可以自动完成这种优化。
            int hash = 7;
            // x, y是自定义类的两个字段
            hash = 31 * hash + (x != null ? x.hashCode() : 0);
            hash = 31 * hash + (y != null ? y.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof Key) {
                Key key = (Key)obj;
                return x.equals(key.x) && y.equals(key.y);
            }
            return false;
        }
    }
    public static void main(String[] args) {
        Set<Key> set = new HashSet<>();
        set.add(new Key(1,2));
        set.add(new Key(1,2));
        set.add(new Key(2,2));
        System.out.println(set.size());
    }
}
