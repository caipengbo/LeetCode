# 二分查找

重点：
1. 有序
2. 初始值
3. g函数的设计
4. 边界判断（使用左边界，但是一般需要判断一样左边界是否符合要求）

# 二分查找

## 1. 思想与模板

**思想**：二分查找并不到找到准确的答案，而是找到一个边界点。有序（数组有序、范围的潜在有序）、找边界(**临界点**)、(尝) 试每次取到的mid(g函数的设计)

区间左闭右 `[start, end )` 开模板
```Java
int start = 0, end = length - 1; 
while(start < end) {
        mid = start + (end - start) / 2;
        if g(mid) {
            end = mid;  // 左侧
        } else {
            start = mid + 1;  // 右侧
        }
}
return start;  // or how to use upper bound (start) 
```
> - 首先明确我们是对索引进行二分还是对值进行二分（有时忘记是索引，导致马虎出现bug）
> - 注意初始位置(`start` 和 `end`), 初始位置主要是用来判断当找不到结果的时候的边界情况，一般用于判断边界，P34和P35
> - `g( )` 函数：最终返回的是，第一个满足`g(m) == true`的元素下标`m`(注意`end = mid` 在前面时)
> - 返回 `start`,  找不到时，会返回左边界或者右边界

**二分查找**

```Java
public int search(int[] nums, int target) {
    int l = 0, r = nums.length-1;
    int m;
    while(l < r) {
        m = l + (r - l) / 2;
        if(nums[m] >= target) r = m;
        else l = m + 1;
    }
    if(nums[l] == target) return l;
    return -1;
}
```

**两种搜索方式**

(数组的) 索引搜索 和 (范围的) 值搜索

1. 索引搜索

- 注意不要越界（一般end初始为len-1不会越界）
- 注意最终返回的start，如何去使用它(start = 0 时，start = len-1时) 

2. 值搜索

- 值搜索一般是知道一个范围，在这个范围内进行二分搜索
- 一般搜索是在整数范围内，如果是浮点数的话，一般使用while(true)循环，然后在循环内部设置出口，这时候边界 缩小边界的时候， 使用 start=m, end=m进行缩小

## 2. 利用边界
找不到的情况？越界问题？
**例** （一个非常好的题目）
寻找有序数组中，比val值小的数的个数，注意val不一定在数组中，并且数组中可能含有重复元素
```Java
    public int findKSmallest(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int l = 0, r = nums.length-1, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if(nums[m] > val) r = m;
            else l = m + 1;
        }
        if (nums[l] > val) return l;  // 如何利用边界
        return l+1;
    }
```
**例**  [LeetCode第34题](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/)

[更多例题](https://github.com/caipengbo/AlgoEx/tree/master/LeetCode/bisearch/usebound)

4. 寻找两个有序数组的中位数
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))，可以假设 nums1 和 nums2 不会同时为空。
```Java
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l, r;
        int k1 = 0, k2 = 0;
        int median1 = 0, median2 = 0;
        if (m == 0) {
            l = nums2[0];
            r = nums2[n-1];
        } else if (n == 0) {
            l = nums1[0];
            r = nums1[m-1];
        } else {
            l = Math.min(nums1[0], nums2[0]);
            r = Math.max(nums1[m-1], nums2[n-1]);
        }
        if ((m + n) % 2 == 0) { // 偶数
            median1 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n) / 2);
            median2 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n) / 2 + 1);
            return (double)(median1+median2)/2;
        } else {
            median1 = findKSmallestInTwoArray(nums1, nums2, l, r, (m + n + 1) / 2);
            return (double) median1;
        }
    }
    public int findKSmallestInTwoArray(int[] nums1, int[] nums2, int l, int r, int k) {
        if (k == 0) return 0;
        int count1, count2;
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            count1 = findKSmallest(nums1, mid);
            count2 = findKSmallest(nums2, mid);
            if (count1 + count2 >= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    // <= val 数的数目（注意val不一定在数组中哦）
    public int findKSmallest(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int l = 0, r = nums.length-1, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if(nums[m] > val) r = m;
            else l = m + 1;
        }
        if (nums[l] > val) return l;
        return l+1;
    }
```
34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置，如果数组中不存在目标值，返回 [-1, -1]。
```Java
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1,-1};
        if (nums == null || nums.length == 0) return ret;
        int l = 0, r = nums.length - 1;
        int m;
        // 找最左边的位置
        while (l < r) {
            m = l + (r -l) / 2;
            if (nums[m] >= target) r = m;
            else l = m + 1;
        }
        if (nums[l] == target) ret[0] = l;
        // 找右边的位置
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            m = l + (r -l) / 2;
            if (nums[m] > target) r = m;
            else l = m + 1;
        }
        if ((l == 0 || l == nums.length - 1) && nums[l] == target) ret[1] = l;
        else if (l > 0 && nums[l-1] == target) ret[1] = l-1;
        return ret;
    }
```
35. 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置，你可以假设数组中无重复元素。
```Java
   // 找到
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            // 注意此处为何不是 <= ?
            if (nums[m] < target) l = m + 1; // 右侧
            else r = m;  // 左侧
        }
        return l;
    }
```
69. x 的平方根
实现 int sqrt(int x) 函数。计算并返回 x 的平方根，其中 x 是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。注意：整数的溢出 需要强制转化成 Long。
```Java
    public int mySqrt(int x) {
        int l = 0;
        int r = x / 2 + 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if ((long)m*m <= x) l = m + 1;
            else r = m;
        }
        if ((long)l * l > x) return  l - 1;
        else return l;
    }
```

## 3. 设计 g 函数(判断mid的函数)

- g函数代表第一个满足 g(m) == true 时的 m 的取值，取不到时，会获得左右边界
- g函数实际上就是起到了一个判断的作用，这就体现了二分法中 "试"这个字的含义了

162. 寻找峰值
峰值元素是指其值大于左右相邻值的元素，给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。你可以假设 nums[-1] = nums[n] = -∞。
```Java
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int l = 0, r = nums.length - 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] > nums[m+1]) r = m;
            else l = m + 1;
        }
        return l;
    }
```
287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。说明：不能更改原数组（假设数组是只读的）；只能使用额外的 O(1) 的空间；时间复杂度小于 O(n2) ；数组中只有一个重复的数字，但它可能不止重复出现一次。
```Java
    // 从1 - n 二分缩短区间，然后统计数组中小于mid的数目，如果大于mid，说明[1,mid]有重复
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) count++;
            }
            if (count > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
```
875. 在 H 小时内吃掉所有香蕉的最小速度 K
珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
```Java
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1, hi = piles[0];
        for (int i = 0; i < piles.length; i++) {
            if (hi < piles[i]) hi = piles[i];
        }
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < piles.length; i++) {
                if (piles[i] % mid == 0) count += piles[i] / mid;
                else count += piles[i] / mid + 1;
            }
            if (count <= H) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
```
1011. 在 D 天内送达包裹的能力
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
1 <= D <= weights.length <= 50000; 1 <= weights[i] <= 500。

```Java
// 最低运载能力 max(weights) <= c <= sum(weights)
    // 那么在这个区间使用二分查找寻找 第一个满足要求的
    public int shipWithinDays(int[] weights, int D) {
        int l = weights[0], r = 0;
        for (int i = 0; i < weights.length; i++) {
            r += weights[i];
            if (l < weights[i]) l = weights[i];
        }
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (canSplit(weights, D, mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    private boolean canSplit(int[] weights, int D, int capacity) {
        int count = 0;
        int temp = 0, i = 0;
        while (i < weights.length) {
            temp += weights[i];
            if (temp > capacity) {
                // System.out.println(temp - weights[i] + "--" + (i-1));
                temp = 0;
                i--;
                count++;
            }
            i++;
        }
        if (temp != 0) {
            // System.out.println(temp);
            count++;
        }
        // System.out.println(count);
        return count <= D;
    }
```

## 4. K th 问题 以及 二维问题

如何转换成 二维问题？？ 设置两个方向的坐标 i j 然后映射到 input的一维数组上。这里，g函数有一个统计的过程，通过count与K进行比较，来缩短区间。

378. 有序矩阵中第K小的元素
给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
> 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n^2。这种并不是 一个整个的排序list, 而是一个二维的，需要用到 范围搜索+索引搜索
```Java
    // 范围搜索 + 索引搜索
    // 二维数组第一个元素是最小值，最后一个元素是最大值，在这两个数之间使用二分法寻找 第K小元素
    // min(matrix) <= candidate <= max(matrix),  然后再次使用二分法统计 每一行比candidate小的元素数目，来判断candidate是否符合要求
    // 范围搜索
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length-1][matrix.length-1] + 1;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 0; i < matrix.length; i++) {
                count += countRow(matrix[i], mid);
            }
            if (count >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    // 二分法：每一行 <= target 的数目，也就是 下边界
    // 索引搜索
    public int countRow(int[] row, int target) {
        int lo = 0, hi = row.length;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (row[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
```
668. 乘法表中第k小的数

几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。

```Java
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n + 1;
        int mid, count;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            count = 0;
            for (int i = 1; i <= m; i++) {
                count += (mid/i > n ? n : mid/i);
            }
            if (count >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
```
74. 搜索二维矩阵
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：每行中的整数从左到右按升序排列；每行的第一个整数大于前一行的最后一个整数。

```Java
    // 难点： 如何将 2d -> 1d : 得到 位置的数字 然后转换成 坐标就可以了
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n;
        int mid, i, j;
        while (l < r) {
            mid = l + (r - l) / 2;
            i = mid / n;
            j = mid % n;
            if (matrix[i][j] < target) l = mid + 1;
            else r = mid;
        }
        if (l >= m * n) return false;
        i = l / n;
        j = l % n;
        return  matrix[i][j] == target;
    }
```
## 5. 旋转有序数组

**旋转数组中查找某值**
```
1) 每次检查target == nums[mid]，如果是的话，找到了结果
2) 接着，检查第一半是否是有序的（例如nums[left]<=nums[mid]，如果是，进行第3步）,否则的话。说明另一半是有序的，进行第4步。
3) 检查target是否在[left, mid-1] (例如 nums[left]<=target < nums[mid])，如果是，在第一部分进行检索（right = mid-1）；否则，在第二部分进行检索（left = mid+1）
4) 检查target是否在[mid+1, right] (例如 nums[mid]<target <= nums[right])，如果是，在第二部分进行检索（left = mid+1），否则在第一部分进行检索（right = mid-1）
```
33. 搜索旋转排序数组
假设按照升序排序的数组在预先未知的某个点上进行了旋转。 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。假设数组中不存在重复的元素，算法时间复杂度必须是 O(log n) 级别。
```Java
public int search2(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length - 1;
    int mid;
    while (left < right) {
        mid = left + (right - left) / 2;
        // if (target == nums[mid]) return mid;
        // 左边有序（旋转点在右边）
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target <= nums[mid]) right = mid;
            else left = mid + 1;
        } else { // 右边有序（旋转点在左边）
            if (nums[mid] < target && target <= nums[right]) left = mid + 1;
            else right = mid;
        }
    }
    if (nums[left] == target) return left;
    return -1;
}
```

**旋转数组中含重复元素**

当有重复数字的时候，当nums[left] == nums[mid] 的时候有可能第一部分是无序的（例如[3 1 2 3 3 3 3]），需要单独处理这种情况。在这种情况下，检查是否 `nums[mid]== nums[left] == nums[right]`，如果是，则left++，right--(往mid靠拢)。见下面的例题。

81. 搜索旋转排序数组 II
这是 33.搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
```Java
//  [1,3,1,1,1]   [1,1,1,3,1]
public boolean search(int[] nums, int target) {
    if (nums.length == 0) return false;
    int left = 0, right = nums.length - 1;
    int mid;
    while (left < right) {
        mid = left + (right - left) / 2;
        // if (target == nums[mid]) return true;
        // 和33题不同的地方
        if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
            left++;
            right--;
        } else if (nums[left] <= nums[mid]) { // 左边有序（旋转点在右边）
            if (nums[left] <= target && target <= nums[mid]) right = mid;
            else left = mid + 1;
        } else { // 右边有序（旋转点在左边）
            if (nums[mid] < target && target <= nums[right]) left = mid + 1;
            else right = mid;
        }
    }
    return nums[left] == target;
}
```
**旋转数组中的最小值**
g函数 `if (nums[mid] < nums[hi])`
153. 寻找旋转排序数组中的最小值
假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。请找出其中最小的元素。假设数组中不存在重复元素。
```Java
// 旋转点就是最小值
public int findMin(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    int mid;
    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        if (nums[mid] < nums[hi]) hi = mid;
        else lo = mid + 1;
    }
    return nums[lo];  // 得到的是 分界点 p, 左侧是有序的(0, p-1)有序, 右侧(p, len-1)也是有序的
}
```
154. 寻找旋转排序数组中的最小值II
对于153题的拓展，数组中可能存在重复元素。
```Java
public int findMin(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    int mid;
    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        if (nums[mid] < nums[hi]) hi = mid;
        else if (nums[mid] > nums[hi]) lo = mid + 1;
        else hi--;
    }
    return nums[lo];  // 得到的是 分界点 p, 左侧是有序的(0, p-1)有序, 右侧(p, len-1)也是有序的
}
```