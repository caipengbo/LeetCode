package bisearch.usebound;

import java.util.*;

/**
 * Title: 981. 基于时间的键值存储
 * Desc: get(string key, int timestamp)
 *
 * 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
 * 如果没有值，则返回空字符串（""）。
 *
 * 本题的重点是找到 timestamp_prev:
 * Created by Myth on 8/2/2019
 */
class Data {
    String value;
    int timestamp;
    Data(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}
class TimeMap {
    private Map<String, List<Data>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<Data>());
        map.get(key).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Data> list = map.get(key);
        return binarySearch(list, timestamp);
    }
    private String binarySearch(List<Data> list, int timestamp) {
        int l = 0, r = list.size();
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (list.get(m).timestamp < timestamp) l = m + 1;
            else r = m;
        }
        // 寻找
        if (l == 0) return list.get(0).timestamp == timestamp ? list.get(l).value : "";
        else if (l == list.size() || list.get(l).timestamp != timestamp) return list.get(l-1).value;
        else return list.get(l).value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */


public class P981TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeMap obj = new TimeMap();
        obj.set("love","high",10);
        obj.set("love","low",20);
        System.out.println(obj.get("love",5));
        System.out.println(obj.get("love",10));
        System.out.println(obj.get("love",15));
        System.out.println(obj.get("love",20));
        System.out.println(obj.get("love",25));
    }
}
