package sort;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Title: 220. 存在重复元素 III
 * Desc: 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * Created by Myth-Lab on 10/18/2019
 */
public class P220ContainsDuplicate3 {
    
    // 让他们分别包含区间 ..., [0,t], [t+1, 2t+1], ......,[0,t],[t+1,2t+1],...。
    // 我们把桶来当做窗口，于是每次我们只需要检查 xx 所属的那个桶和相邻桶中的元素就可以了。
    // 终于，我们可以在常量时间解决在窗口中搜索的问题了。
    // 还有一件值得注意的事，这个问题和桶排序的不同之处在于每次我们的桶里只需要包含
    // 最多一个元素就可以了，因为如果任意一个桶中包含了两个元素，
    // 那么这也就是意味着这两个元素是 足够接近的 了，这时候我们就直接得到答案了。

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    // 初始化一颗空的二叉搜索树 set
    // 对于每个元素x，遍历整个数组
    //     在 set 上查找大于等于x的最小的数，如果s−x ≤ t则返回 true
    //     在 set 上查找小于等于x的最大的数，如果x−g ≤ t则返回 true
    //     在 set 中插入x
    // 如果树的大小超过了k, 则移除最早加入树的那个数。
    // 返回 false
    // 时间复杂度：O(nlog(min(n,k)))
    // 我们需要遍历这个 n 长度的数组。对于每次遍历，在 BST 中 搜索，插入 或者 删除 都需要花费 O(log min(k,n)) 的时间。

    // 空间复杂度：O(min(n,k))
    // 空间复杂度由 BST 的大小决定，其大小的上限由 k 和 n 共同决定。
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // x后面的数  >= x的数 
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;
    
            // x前面的数  <= x的数  
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;
    
            // 维持滑动窗口的有序
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


}
