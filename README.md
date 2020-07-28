# LeetCode总结

所有的题目总结均在每一个package的README中

## 目录
- 搜索(回溯、BFS、DFS): 
	- 1. 回溯：数独、N皇后、37、51、79、93、[212、301]
	- 2. BFS：矩阵、单词变换
	- 3. 排列、组合、分割、子集：四大类问题，常用回溯、DFS解决
	- 4. 图的搜索：DFS、BFS、并查集、Flood
	- 5. 并查集（TODO）
- 二分查找：
  - g 函数，利用边界
  - K th 问题
  - 旋转数组
- 双指针：
  - 左右指针：数组（或字符串）问题，二分查找也算是双指针，三数之和，Sunday算法
  - 快慢指针：链表中环的问题
  - 滑动窗口：更新窗口
- 链表：
  - 链表的基本操作
  - 旋转（K组旋转，奇偶旋转）、拆分
  - 归并
  - 判断环（快慢指针）
- 二叉树：
  - 遍历
  - 深度、层次
  - 树的结构相关
  - 树的路径相关，递归的过程之中不断更新全局变量
  - 剪枝
  - 二叉搜索树
- 数学：
  - 概率：洗牌算法、蓄水池抽样、蒙特卡洛
  - 数论：素数，最小公倍数，最大公约数
  - 位运算：异或，与的巧妙用法
  - 特殊的数：有效数字（状态机），第n个丑数，平方数（DP解法），回文数
  - 数字的转化：溢出检测、模拟运算（时刻注意溢出）、罗马、字符转成数字；分数转小数
  - 其他：Pow()快速幂算法，众数投票法
- 动态规划
- 排序
- 数据结构
  - 单调栈
  - 单调队列
- 图
- 分治
- 贪心

## 心法宝典

1. 递归要素：开头-判断边界（退出条件）；中间-进行相关的计算、缩小范围递归（经常用到全局变量哦）；结尾-返回值（还要学会如何利用返回值）
2. 反转链表：迭代写法返回prev，递归写法每次考虑两个节点（返回值是前递归的返回值）
3. 采样：n个数随机采样m个，knuth采样：对于每个下标[0，n) 每次n--, 若rand()%n < m时才m--
4. Shuffle：knuth Shuffle，i从后往前，每次从[0,i]选择一个位置与i交换
5. DP: DP 就是填表法，首先明确初始值（并且要明确这个值是什么值），然后要明确填表的顺序，最后是如何填（状态转移方程）。
6. 使用移位运算一定要注意运算符的优先级



# 双指针

双指针主要分为三类：左右（一般是数组或者字符串，经典的有三数之和和四数之和），快慢指针（一般是和链表环有关系），滑动窗口

## 1. 序列

3. 无重复字符的最长子串
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。注意区分子串与子序列区别
```Java
// [i, j), 无重复的时候j++, 有重复的时候i++
public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();  // int[] dic = new int[256];
    int i = 0, j = 0;
    int ret = 0;
    while (j < s.length() && i <= j) {
        // 是否存在重复字符
        if (!set.contains(s.charAt(j))) {
            set.add(s.charAt(j));
            j++;
            ret = Math.max(ret, j-i);  // 注意此处没+1，因为j先自增了
        } else {
            set.remove(s.charAt(i));
            i++;
        }
    }
    return ret;
}
```

11. 盛最多水的容器

<img src="/Users/caipb/Private/LeetCode/pic/p11.png" style="zoom:40%;" />

不能倾斜容器，且 n (数组的长度)的值至少为 2。

思路：我们要做的就是保证宽度最大的情况下，高度最大； 一开始宽度最大，然后逐步减少宽度；这个时候要不断的去更新高度，使得高度尽量的大，如何移动较大的一端，那么面积肯定是减小的；移动较小的那一个端，面积有可能增大

```Java
public int maxArea(int[] height) {
    int i = 0, j = height.length-1;
    int maxValue = 0;
    while (j - i >= 1) {
        System.out.println((j-i) * Math.min(height[i], height[j]));
        maxValue = Math.max(maxValue, (j-i) * Math.min(height[i], height[j]));
        if (height[i] < height[j]) {
            i++;
        } else {
            j--;
        }
    }
    return maxValue;
}
```

15. 三数之和
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a, b, c, 使得 a + b + c = 0 ?找出所有满足条件且不重复的三元组。
```Java
// 难点: 三数之和 -> 两数之和(哈希、排序+双指针); 排除重复
// 排序+双指针 : val<target时low++; val>target时 high--; val==target时low++ & high--
// 如何跳过重复
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ret = new LinkedList<>();
    Integer iPre = null;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length-2; i++) {
        if (nums[i] > 0) break;
        int j = i+1, k = nums.length-1, sum = 0 - nums[i];
        Integer jPre = null, kPre = null;
        if (iPre != null && iPre == nums[i]) continue;  // 去重
        iPre = nums[i];
        while (j < k) {
            if (nums[j] + nums[k] < sum) {
                j++;
            }
            else if (nums[j] + nums[k] > sum) {
                k--;
            } else {
                if (jPre == null || (jPre != nums[j] && kPre != nums[k])) {
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
                jPre = nums[j];
                kPre = nums[k];
                j++;
                k--;
            }
        }
    }
    return ret;
}
```
16. 最接近的三数之和
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
```Java
// 本题假定只有 一个答案，所以不用去重了
// 和第15题思路一模一样
public int threeSumClosest(int[] nums, int target) {
    // nums.length > 3
    Arrays.sort(nums);
    int ret = nums[0]+nums[1]+nums[2];
    for (int i = 0; i < nums.length-2; i++) {
        int j = i+1, k = nums.length-1, sum;
        while (j < k) {
            sum = nums[i] + nums[j] + nums[k];
            if (Math.abs(sum-target) < Math.abs(ret-target)) {
                ret = sum;
            }
            if (sum == target) {
                return target;
            } else if (sum > target) {
                k--;
            } else {
                j++;
            }
        }
    }
    return ret;
}
```
18. 四数之和
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d , 使得 a + b + c + d 的值与 target 相等? 找出所有满足条件且不重复的四元组。和第15题一个思路。
```Java
// 双指针 + 去重
public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ret = new LinkedList<>();
    Integer aPre = null;
    Arrays.sort(nums);
    for (int a = 0; a < nums.length-3; a++) {
        if (nums[a] > 0 && nums[a] > target) break;  // 注意一定要加nums[a] > 0
        if (aPre != null && aPre == nums[a]) continue;
        aPre = nums[a];
        Integer bPre = null;
        for (int b = a+1; b < nums.length-2; b++) {
            if (nums[b] > 0 && nums[a] + nums[b] > target) break;
            if (bPre != null && bPre == nums[b]) continue;  // 去重
            bPre = nums[b];
            int c = b+1, d = nums.length-1, sum = target - (nums[a] + nums[b]);
            Integer cPre = null, dPre = null;
            while (c < d) {
                if (nums[c] + nums[d] < sum) c++;
                else if (nums[c] + nums[d] > sum) d--;
                else {
                    if (cPre == null || (cPre != nums[c] && dPre != nums[d])) {
                        ret.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    }
                    cPre = nums[c];
                    dPre = nums[d];
                    c++;
                    d--;
                }
            }
        }
    }
    return ret;
}
```
26. 删除排序数组的重复项
不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。不需要考虑数组中超出新长度后面的元素。
```Java
public int removeDuplicates(int[] nums) {
    int i = 0, len = nums.length;
    for (int j = 0; j < len; j++) {
        if (j+1 < len && nums[j] != nums[j+1]) {
            nums[i++] = nums[j];
        }
    }
    if (len > 0) nums[i++] = nums[len-1];
    return i;
}
```
27. 移除元素
原地移除元素，分很多种情况。
```Java
// 2. 当要移动的很多时候
public int removeElement(int[] nums, int val) {
    if (nums == null || nums.length == 0) return 0;
    int i = 0, j = nums.length-1;
    while (i <= j) {
        while (i <= j && nums[i] != val) i++;
        while (i <= j && nums[j] == val) j--;
        
        if (j > i) nums[i++] = nums[j--];
    }
    // if (nums[i] == val) return i;
    return j+1;
}
// 2.2 只和最后一个元素交换
public int removeElement2(int[] nums, int val) {
    if (nums == null || nums.length == 0) return 0;
    int i = 0, n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n-1];
            n--;
        } else {
            i++;
        }
    }
    // if (nums[i] == val) return i;
    return n;
}
```
28. 模式匹配
不要求掌握较难的KMP算法，掌握Sunday算法。
```Java
// Sunday算法：如果当前窗口不匹配，比较窗口下一个字符串；下一个字符串在shift数组中的位置，就是窗口要偏移的距离
// 先计算shift数组 every char : len(needle) - max(char)   otherwise: len+1
public int strStr(String haystack, String needle) {
    // 使用 HashMap 实现shift数组
    HashMap<Character, Integer> shiftMap = new HashMap<>();
    int len = needle.length();
    for (int i = 0; i < len; i++) {
        Character character = needle.charAt(i);
        if (!shiftMap.containsKey(character)) {
            for (int j = len-1; j >= 0; j--) {
                if (needle.charAt(j) == character) {
                    shiftMap.put(character, len-j);
                    break;
                }
            }
        }
    }
    int p =  0;
    while (p + len <= haystack.length()) {
        int i;
        for (i = 0; i < len; i++) {
            if (haystack.charAt(p+i) != needle.charAt(i)) break;
        }
        if (i == len) return p;
        else if (p+len == haystack.length()) return -1;
        else p += shiftMap.getOrDefault(haystack.charAt(p+len), len+1);
    }
    return -1;
}
```
75. 颜色分类
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。使用一趟扫描。
```Java
private void swap(int[] nums, int i, int j) {
    if (nums[i] == nums[j]) return;
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
public void sortColors(int[] nums) {
    int p0 = 0, p2 = nums.length-1;
    int cur = 0;
    while (cur <= p2) {
        if (nums[cur] == 0) swap(nums, cur++, p0++);
        else if (nums[cur] == 2) swap(nums, cur, p2--);
        else cur++;
    }
}
```
88. 合并两个有序数组
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。初始化 nums1 和 nums2 的元素数量分别为 m 和 n。你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
```Java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m-1, j = n-1, k = m+n-1;
    while (i >= 0 && j >= 0) {
        if (nums1[i] < nums2[j]) {
            nums1[k--] = nums2[j--];
        } else {
            nums1[k--] = nums1[i--];
        }
    }
    while (j >= 0) {
        nums1[k--] = nums2[j--];
    }
}
```
240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
该矩阵具有以下特性：每行的元素从左到右升序排列。每列的元素从上到下升序排列。
```Java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    int m = matrix.length, n = matrix[0].length, row = 0, col = n-1;
    while (row < m && col >= 0) {
        if (matrix[row][col] < target) row++;
        else if (matrix[row][col] > target) col--;
        else return true;
    }
    return false;
}
```
283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。必须在原数组上操作，不能拷贝额外的数组。尽量减少操作次数。
```Java
private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
public void moveZeroes(int[] nums) {
    int zeroPos = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            swap(nums, zeroPos++, i);
        }
    }
}
```
## 2. 快慢指针
141. 环形链表
给一个链表判断链表是否有环，你能用 O(1)（即，常量）内存解决此问题吗？
```Java
// 使用哈希表，将每个指针地址存入，然后判断，空间复杂度 O（n）
// 快慢指针 类似于两人跑步（慢指针每次1步，快指针每次2步），那么 环形部分/1 = 循环迭代的次数
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next;
        fast = fast.next;
        if (slow == fast) return true;
    }
    return false;
}
```
142. 环形链表 II
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。说明：不允许修改给定的链表。
```Java
// 是否有环，注意返回的不是入口节点哦, 实际上是获得的环形部分的数目！！！
public ListNode hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next;
        fast = fast.next;
        if (slow == fast) return slow;
    }
    return null;
}
// 有环，找环的入口地点，然后同步走，就会走到环的入口处
public ListNode detectCycle(ListNode head) {
    ListNode node1 = head;
    ListNode node2 = hasCycle(head);
    if (node2 == null) return null;
    while (node1 != node2) {
        node1 = node1.next;
        node2 = node2.next;
    }
    return node1;
}
```
160. 相交链表
编写一个程序，找到两个单链表相交的起始节点：如果两个链表没有交点，返回 null；在返回结果后，两个链表仍须保持原有的结构；可假定整个链表结构中没有循环；程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存，如果使用 hash 很简单。
```Java
    // 方法2：如何找到两个链表的差距？ 快的指针走到头后，回到长的链表头部；慢的指针走到头，回到短的链表头部，这样就抵消了 差距
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA, q = headB;
        while (p != q) {
            p = (p == null) ? headB : p.next;
            q = (q == null) ? headA : q.next;
        }
        return p;
    }
```
234. 回文链表
请判断一个链表是否为回文链表。用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题。
```Java
// 寻找mid，翻转后半部分，最后比较
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // 偶数时，slow指向后半部分的开头，奇数时，slow指向正中间(让slow再多走一个)
    if (fast != null) slow = slow.next;
    slow = reverse(slow);

    fast = head;
    while (slow != null) {
        if (fast.val != slow.val) return false;
        slow = slow.next;
        fast = fast.next;
    }
    return true;
}
private ListNode reverse(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode pre = null, p = head, q;
    while (p != null) {
        q = p.next;
        p.next = pre;
        pre = p;
        p = q;
    }
    return pre;
}
```
## 3. 滑动窗口
209. 长度最小的子数组（双指针滑动窗口）
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
```Java
public int minSubArrayLen(int s, int[] nums) {
    int n = nums.length, res = n + 1;
    int i = 0, j;
    for (j = 0; j < n; j++) {
        s -= nums[j];
        while (s <= 0) {
            res = Math.min(res, j-i+1);
            s += nums[i++];
        }
    }
    return res % (n + 1); // res == n+1 说明不存在，返回0
}
```
1052. 爱生气的书店老板
书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
TODO
https://leetcode-cn.com/problems/grumpy-bookstore-owner/

# 链表

链表主要是：一些基本操作（遍历），比较重要的 判断链表中是否有环（**见上方快慢指针章节**），比较难得就是链表的旋转（K个一组旋转，奇偶旋转）、拆分、归并（K路归并）

## 基本操作
2. 两数相加
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
```Java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(-1);  // 初始创建一个头指针，就可以避免判断当前指针是否为空
    ListNode cur = head;
    int sum = 0;  // 余数、和使用一个变量来表示
    while (l1 != null || l2 != null || sum != 0) {
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        cur.next = new ListNode(sum % 10);  // 使用取余和取整
        cur = cur.next;
        sum = sum / 10;
    }
    return head.next;
}
```
147. 对链表进行插入操作
```Java
// 优化
public ListNode insertionSortListOpt(ListNode head) {
    ListNode dummy = new ListNode(Integer.MIN_VALUE);
    ListNode cur = head, p = null, next;
    while (cur != null) {
        // p = dummy; 每次都把 p 置在了头部位置
        if (p == null || p.val >= cur.val) p = dummy;  // 优化： 有时候不必将 p 移动至 头部位置
        next = cur.next;
        while (p.next != null && cur.val > p.next.val) {
            p = p.next;
        }
        // 注意以下两行代码
        cur.next = p.next;
        p.next = cur;
        cur = next;
    }
    return dummy.next;
}
```
445. 两数相加2
链表不是逆序方式存储的，该如何去做。
```Java
// 不修改输入链表
// 难点：1. 预先不只是位数 2. 进位不清楚  3. 计算顺序是从后往前的
// 使用栈 时间和空间复杂度 O(m+n)
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    while (l1 != null) {
        stack1.push(l1.val);
        l1 = l1.next;
    }
    while (l2 != null) {
        stack2.push(l2.val);
        l2 = l2.next;
    }

    ListNode head= null, cur;
    int sum = 0;
    while (!stack1.empty() || !stack2.empty() || sum != 0) {
        if (!stack1.empty()) sum += stack1.pop();
        if (!stack2.empty()) sum += stack2.pop();
        cur = new ListNode(sum % 10);
        cur.next = head;
        head = cur;
        sum = sum / 10;
    }
    return head;
}
```
## 删除

19. 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点（n保证有效, 不会等于0 哦），并且返回链表的头结点。使用一趟扫描。
```Java
// 简化：使用dummy，不用pre指针，直接slow指向要删除的节点的前面位置,使用 n 递减计数
public ListNode removeNthFromEnd2(ListNode head, int n) {
    if (head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode fast = dummy, slow = dummy;
    while (fast.next != null) {
        if (n <= 0) slow = slow.next;
        fast = fast.next;
        n--;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
```
82. 删除排序链表中的重复元素
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 **没有重复出现** 的数字。
```Java
public ListNode deleteDuplicates(ListNode head) {
    if(head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy, p = head;
    while (p != null) {
        if (p.next == null || p.next.val != p.val) {
            if (pre.next == p) pre = p;
            else pre.next = p.next;
        }
        p = p.next;
    }
    return dummy.next;
}
```
83. 删除排序链表中的重复元素 II
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
```Java
public ListNode deleteDuplicates(ListNode head) {
    if(head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode p = head;
    while(p != null && p.next != null) {
        if(p.val == p.next.val) {
            p.next = p.next.next;
        } else {
            p = p.next;
        }
    }
    return dummy.next;
}
```
203. 移除链表元素
删除链表中等于给定值 val 的所有节点。
```Java
public ListNode removeElements(ListNode head, int val) {
    if(head == null) return null;
    int temp = (val == -1) ? -2 : -1;
    ListNode dummy = new ListNode(temp);
    dummy.next = head;
    ListNode p = dummy;
    while(p.next != null) {
        if(p.next.val == val) {
            p.next = p.next.next;
        } else {
            p = p.next;
        }
    }
    return dummy.next;
}
```
## 旋转
24. 两两交换链表中的节点
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。给定 1->2->3->4, 你应该返回 2->1->4->3.
```Java
// 节点的移动（三个节点）
public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode cur = dummy, swap1, swap2;
    while (cur.next != null && cur.next.next != null) {
        swap1 = cur.next;
        swap2 = cur.next.next;
        cur.next = swap2;
        swap1.next = swap2.next;
        swap2.next = swap1;
        cur = swap1;
    }
    return dummy.next;
}
// 递归写法(考虑两个节点)
public ListNode swapPairsRecursive(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode nextNode = head.next;
    head.next = swapPairsRecursive(nextNode.next);
    nextNode.next = head;
    return nextNode;
}
```
25. K个一组反转链表
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 k 是一个正整数，它的值小于或等于链表的长度。 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
```Java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode p = head, q = head;
    // 找到 第 k-1个结点p
    for (int i = 0; i < k; i++) {
        if (p == null) return head;
        q = p;
        p = p.next;
    }
    q.next = null;
    ListNode newHead = reverse(head);
    head.next = reverseKGroup(p, k);
    return newHead;
}
private ListNode reverse(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode ret = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return ret;
}
```

61. 旋转链表
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
```Java
// 快指针先走k-1步， 若快指针先为null说明链表长度小于k, 则快指针走 k%n
// 最终慢指针为新头结点，快指针.next = head
public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return null;
    ListNode slow = head, fast = head, ret = null;
    int i = 0;
    for (i = 0; i < k; i++) {
        if (fast == null) break;
        fast = fast.next;
    }
    // k== 链表长度
    if (i == k && fast == null) return head;
    if (i < k) {
        k = k % i;
        fast = head;
        for (i = 0; i < k; i++) {
            fast = fast.next;
        }    
    }
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    fast.next = head;
    ret = slow.next;
    slow.next = null;
    return ret;
}
```
92. 反转链表2
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
```Java
// 设置DummyNode
public ListNode reverseBetween2(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode oldLast = dummy, before = null, cur, after;
    
    for (int i = 1; i < m; i++) {
        oldLast = oldLast.next;
    }
    ListNode reverseLast = oldLast.next;
    cur = reverseLast;
    for (int i = m; i <= n; i++) {
        after = cur.next;
        cur.next = before;
        before = cur;
        cur = after;
    }
    reverseLast.next = cur;
    oldLast.next = before;
    return dummy.next;
}
```
206. 反转链表（使用迭代 和 递归）
反转一个单链表。
```Java
// 迭代（循环版本）
public ListNode reverseList(ListNode head) {
    ListNode front = null, cur = head, back;
    while (cur != null) {
        back = cur.next;
        cur.next = front;
        front = cur;
        cur = back;
    }
    return front;
}
// 递归版本
public ListNode reverseList2(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
```
## 拆分
138. 复制带随机指针的链表
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。要求返回这个链表的深拷贝。你必须返回给定头的拷贝作为对克隆列表的引用。
难点：如何拷贝 随机节点？
思路1：使用 HashMap: 需要额外的空间   2. 旧节点和新节点 交错排列，然后复制 random，再拆开 next.
```Java
private class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
// 拆开链表的地方有点不一样
public Node copyRandomList(Node head) {
    if (head == null) return null;
    Node p = head, newNode, q;
    // 交叉排列
    while (p != null) {
        newNode = new Node(p.val, null, null);
        newNode.next = p.next;
        p.next = newNode;
        p = newNode.next;
    }
    // 赋值新节点的 random
    p = head;
    while (p != null) {
        p.next.random = (p.random != null ? p.random.next : null);
        p = p.next.next;
    }
    // 拆开链表（拆解方法 2）
    p = head;
    q = head.next;
    newNode = p.next;
    while (p.next != null && p.next.next != null) {
        p.next = p.next.next;
        q.next = q.next.next;
        p = p.next;
        q = q.next;
    }
    p.next = null;  // 注意封尾操作，详细的拆分 参见 328题
    return newNode;
}
```
328. 奇偶链表（链表的拆分）
给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。应当保持奇数节点和偶数节点的相对顺序。链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
```Java
public static ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) return head;
    ListNode p = head, q = head.next;
    ListNode evenHead = head.next;
    while (p.next != null && p.next.next != null) {  // 判断条件是 p.next,也就是说，是 到 链表的最后一个元素，这个时候需要进行封尾操作
        p.next = p.next.next;
        q.next = q.next.next;
        p = p.next;
        q = p.next;
    }
    p.next = evenHead;  // 封尾操作(该题比较特殊，如果是单纯的将一个链表拆成一个，需要进行封尾)
    // 因为 p.next == null 跳出循环， p
    return head;
}
```
## 归并
21. 合并两个有序链表(循环写法和递归写法)
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
```Java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(-1);
    ListNode p = head;
    while(l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            p.next = l1;
            l1 = l1.next;
        } else {
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
    }
    // 直接接到 剩余的链表即可
    if (l1 != null) p.next = l1;
    if (l2 != null) p.next = l2;

    return head.next;
}
public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val <= l2.val) {
        l1.next = mergeTwoListsRecursive(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoListsRecursive(l1, l2.next);
        return l2;
    }
}
```
23. 合并K个排序链表(使用堆和不使用堆)
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
```Java
// 使用堆
public ListNode mergeKLists(ListNode[] lists) {
    int k = lists.length;
    if (k == 0) return null;
    PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(k, new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    });
    ListNode head = new ListNode(-1);
    ListNode p = head, temp;
    boolean hasNode = true;
    while (hasNode) {
        hasNode = false;
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                //  这里也要注意
                temp = lists[i];
                lists[i] = lists[i].next;
                priorityQueue.add(temp);
                hasNode = true;
            }
        }
    }
    while (!priorityQueue.isEmpty()) {
        p.next = priorityQueue.poll();
        p = p.next;
    }
    p.next = null;  // 必须加这个封尾操作，有可能会出现环
    return head.next;
}
// 不使用堆
public ListNode mergeKLists2(ListNode[] lists) {
    return mergeKLists2(lists, 0, lists.length - 1);
}
public ListNode mergeKLists2(ListNode[] lists, int start, int end) {
    if (start == end) return lists[start];
    else if (start < end) {
        int mid = start + (end - start) / 2;
        ListNode left = mergeKLists2(lists, start, mid);
        ListNode right = mergeKLists2(lists, mid+1, end);
        return mergeTwoListsRecursive(left, right);
    } else return null;
}
public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val <= l2.val) {
        l1.next = mergeTwoListsRecursive(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoListsRecursive(l1, l2.next);
        return l2;
    }
}
```
148. 排序链表（归并排序）
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
注意：递归版本使用到了 系统栈，所以空间复杂度不是是lg(n)；链表的归并需要移动指针找到链表的中点。
```Java
// 递归版本 （自顶向下）
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    // 将链表分为两段
    ListNode p = head, q = head, pre = head;
    while (q != null && q.next != null) {
        pre = p;
        p = p.next;
        q = q.next.next;
    }

    pre.next = null;  // 截断链表
    ListNode left = sortList(head);
    ListNode right = sortList(p);
    return mergeTwoListsRecursive(left, right);
}
public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    if (l1.val <= l2.val) {
        l1.next = mergeTwoListsRecursive(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoListsRecursive(l1, l2.next);
        return l2;
    }
}
// 非递归版本（迭代）
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    ListNode dummy = new ListNode(-1), cur = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            cur.next = l1;
            l1 = l1.next;
        } else {
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    if (l1 == null) {
        cur.next = l2;
    } else {
        cur.next = l1;
    } 
    return dummy.next;
}
```
# 树
树就是练习递归的地方，递归重点：边界值（提前退出），缩小范围（进入递归）（此处一般都要辅以操作），返回值（以及充分利用返回值）

## 遍历
**三种遍历方法**
94. 144. 145. 题前中后序遍历

递归写法（中序遍历为例）：
```Java
public void inorder(TreeNode root, List<Integer> ret) {
    if (root == null) return;
    inorder(root.left, ret);
    ret.add(root.val);
    inorder(root.right, ret);
}
```
迭代写法：
**前序**
```java
List<Integer> ret = new ArrayList<>();
Stack<TreeNode> stack = new Stack<>();
TreeNode cur = root;
stack.add(cur);
while (!stack.isEmpty()) {
    cur = stack.pop();
    if (cur != null) {
        ret.add(cur.val);
        stack.add(cur.right);
        stack.add(cur.left);
    }
}
return ret;
```
**中序**
```java
List<Integer> ret = new ArrayList<>();
Stack<TreeNode> stack = new Stack<>();
TreeNode cur = root;
while (cur != null || !stack.empty()) {
    while (cur != null) {
        stack.add(cur);
        cur = cur.left;
    }
    cur = stack.pop();
    ret.add(cur.val);
    cur = cur.right;
}
return ret;
```
**后序**
前序遍历的顺序是 根-左-右，后序遍历的顺序是 左-右-根
那么 前序稍微修改一下 变成 根-右-左，然后把结果倒序，就变成 左-右-根* 了
```java
// 使用LinkedList的头插，让结果倒序
LinkedList<Integer> ret = new LinkedList<>();
Stack<TreeNode> stack = new Stack<>();
TreeNode cur = root;
stack.add(cur);
while (!stack.empty()) {
    cur = stack.pop();
    if (cur != null) {
        ret.addFirst(cur.val);  // 头插法，让结果倒序
        stack.add(cur.left);
        stack.add(cur.right);
    }
}
return ret;
```
**根据遍历序列构造树**

105. 前中(唯一)
假设树中没有重复的元素，根据一棵树的前序遍历与中序遍历构造二叉树。
```Java
// 难点：如何在数组上分区域
// 方案：递归的在子数组上进行操作
public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length-1, preorder, inorder);
}
public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart >= preorder.length || inStart > inEnd) return null;
    // 当前根节点
    TreeNode root = new TreeNode(preorder[preStart]);
    // 中序遍历中找到当前的 根节点，划分左右区域(为了加速可以使用 HashMap，O(1)时间找到inIndex)
    int inIndex = 0;
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
            break;
        }
    }
    // 划分区域
    root.left = helper(preStart+1, inStart, inIndex-1, preorder, inorder);
    root.right = helper(preStart+inIndex-inStart+1, inIndex+1, inEnd, preorder, inorder);
    return root;
}
```
106. 中后(唯一)

     从中序与后序遍历序列构造二叉树

```Java
public TreeNode buildTree(int[] inorder, int[] postorder) {
    return helper(postorder.length-1, 0, inorder.length-1, postorder, inorder);
}
public TreeNode helper(int postStart, int inStart, int inEnd, int[] postorder, int[] inorder) {
    if (postStart < 0 || inStart > inEnd) return null;
    // 当前根节点
    TreeNode root = new TreeNode(postorder[postStart]);
    // 后序遍历中从后往前找到当前根节点，划分左右区域(为了加速可以使用 HashMap，O(1)时间找到inIndex)
    int inIndex = 0;
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
            break;
        }
    }
    // 划分区域(难点)
    root.left = helper(postStart-(inEnd-inIndex+1), inStart, inIndex-1, postorder, inorder);
    root.right = helper(postStart-1, inIndex+1, inEnd, postorder, inorder);
    return root;
}
```
889. 前后(不唯一)

426. 将二叉搜索树转化为排序的双向链表 
剑指offer 36题， BST中序遍历是一个有序序列，使用全局变量保存前一个节点。
```Java
TreeNode pre = null;
public TreeNode convert(TreeNode root) {
    if (root == null) return null;
    this.pre = null;
    convertHelper(root);
    TreeNode p = root;
    while(p.left != null) p = p.left;
    return p;
}
// 中序遍历 记录前一个访问的节点
// 使用全局变量保存pre，在convertHelper中传pre的值不行
public void convertHelper(TreeNode cur) {
    if (cur == null) return;
    convertHelper(cur.left);

    cur.left = this.pre;
    if (pre != null) this.pre.right = cur;
    this.pre = cur;

    convertHelper(cur.right);
}
```
## 深度

104. 二叉树的最大深度
给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。说明: 叶子节点是指没有子节点的节点。
```Java
// 递归思路：二叉树的最大深度 = max(左子树深度，右子树深度)
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right)+1;
}
```
110. 判断平衡二叉树
左右子树的高度不相差1
```Java
// 在104的思路基础上修改：先计算树的高度，如果发现当前的节点高度差大于1了
// 那么就直接返回-1
private int recursive(TreeNode root) {
    if (root == null) return 0;
    int left = recursive(root.left);
    if (left == -1) return -1;
    int right = recursive(root.right);
    if (right == -1) return -1;
    if (Math.abs(left-right)>1) return -1;
    return Math.max(left, right) + 1;
}

public boolean isBalanced(TreeNode root) {
    return recursive(root) != -1;
}
```
111. 二叉树的最小深度
```Java
// 难点：当二叉树退化成单侧时
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    // 下面两句是和Max Depth不一样的地方
    if (root.left == null) return right+1;
    if (root.right == null) return left+1;
    return Math.min(left, right)+1;
}
```
## 层序
102. 二叉树的层次遍历（广度优先遍历）
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地, 每一层都放到一个链表内，从左到右访问所有节点）。
```Java
// 使用一个队列，下一层元素数目等于当前队列中元素的数目
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) return ret;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int nodeCount = queue.size();  // Key
        List<Integer> level = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            // 锯齿形层次遍历 使用flag判断是否反转
            // if (!flag) level.add(node.val);
            // else level.addFirst(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        ret.add(level);  // 107题： ret.addFirst(level);
    }
    return ret;
}
// 递归写法(深度优先遍历)，参数包括(node, level, resList)，然后将第 K 层放到第K个 List
// 重点：什么时候初始化List
private void helper(TreeNode root, int level, List<List<Integer>> res) {
    if (root == null) return;
    // Key
    if (level >= res.size()) res.add(new LinkedList<>());
    res.get(level).add(root.val);
    // 103题锯齿形层次遍历：奇数时候头部插入结果List，偶数时候尾部插入
    // if(level % 2 == 0) res.get(level).add(root.val);
    // else ((LinkedList)res.get(level)).addFirst(root.val);
    helper(root.left, level+1, res);
    helper(root.right, level+1, res);
}
public List<List<Integer>> levelOrderRecursive(TreeNode root) {
    List<List<Integer>> res = new LinkedList<>();
    helper(root, 0, res);
    return res;
}
```
107. 二叉树的层次遍历 II
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）和102题一模一样，只不过本题需要将（层次）结果翻转一下，使用链表的头插，实现结果的翻转。
## 结构
100. 相同的树
给定两个二叉树，编写一个函数来检验它们是否相同。如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
```Java
public boolean isSameTree2(TreeNode p, TreeNode q) {
//        if (p == null && q == null) return true;  // 同时为空
//        if (p == null || q == null) return false;  // 不同时为空
    if (p == null || q == null) return p == q;  // 更精简的写法
    if (p.val != q.val) return false;  // 都不为空
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```
101. 对称二叉树（镜像对称）
给定一个二叉树，检查它是否是镜像对称的。例如，二叉树 [1,2,2,3,4,4,3]是对称的
```Java
private boolean recursive(TreeNode tree1, TreeNode tree2) {
    if (tree1 == null || tree2 == null) return tree1 == tree2;
    if (tree1.val != tree2.val) return false;
    return recursive(tree1.left, tree2.right) && recursive(tree1.right, tree2.left);
}
public boolean isSymmetric(TreeNode root) {
    return iterative(root, root);
}
```
226. 反转二叉树
```Java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);
    root.left = right;
    root.right = left;
    return root;
}
```
572. 另一个树的子树
给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
```Java
public boolean isSubtree(TreeNode s, TreeNode t) {
    if (t == null) {
        return true;
    }
    if (s == null) {
        return false;
    }
    if (s.val == t.val && isSame(s, t)) {
        return true;
    }
    return isSubtree(s.left, t) || isSubtree(s.right, t);
}
private boolean isSame(TreeNode tree1, TreeNode tree2) {
    if (tree1 == null || tree2 == null) {
        return tree1 == tree2;
    }
    return tree1.val == tree2.val && isSame(tree1.left, tree2.left) && isSame(tree1.right, tree2.right);
}
```

## 路径

112. 路径和
判断树中是否存在一个路径，和为sum.
```Java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    // if (root.left == null && root.right == null && root.val == sum) return true;
    if (root.left == null && root.right == null) return  root.val == sum;
    return hasPathSum(root.left, sum-root.val) || hasPathSum (root.right, sum-root.val); 
}
```
113. 路径和2
```Java
// 难点：如何保存结果，回溯法！！！
private void findPath(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> res) {
    if (root == null) return;
    path.add(root.val);
    if (root.left == null && root.right == null) {
        // 注意必须new一个新的list
        if (root.val == sum) res.add(new LinkedList<>(path));
    }
    findPath(root.left, sum-root.val, path, res);
    findPath(root.right, sum-root.val, path, res);
    path.removeLast();
}
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    LinkedList path = new LinkedList();
    List<List<Integer>> res = new ArrayList<>();
    findPath(root, sum, path, res);
    return res;
}
```
129. 求根到叶子节点数字之和
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。例如，从根到叶子节点路径 1->2->3 代表数字 123。
计算从根到叶子节点生成的所有数字之和。
```Java
private int sum;
public int sumNumbers(TreeNode root) {
    sum = 0;
    helper(root, 0);
    return sum;
}
private void helper(TreeNode root, int pre) {
    if (root == null) return;
    pre = pre * 10 + root.val;
    if (root.left == null && root.right == null) {
        sum += pre;
        return;
    }
    helper(root.left, pre);
    helper(root.right, pre);
}
```
235. 二叉搜索树的最近公共祖先
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。百度百科中最近公共祖先的定义为：对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
```Java
// 该题求BST的最近公共祖先，难度显著降低，BST有明显的特点
// 遍历树，如果p,q都在左（右）子树，那么就从左（右）子树进行递归，否则就找到了LCA
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
    if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
    return root;
}
```
236. 二叉树的最近公共祖先
本题比235稍微难一些，235题可以通过数值的大小判断左右子树，该题不是BST不行。
```Java
// 递归：对每个节点对应的子树，若该子树不含有p或q，返回nullptr；
// 否则，如果p和q分别位于当前子树根节点两侧，则返回当前节点，
// 否则（p和q在同一侧，或者只有某一侧有p或q）返回来自左边或右边的LCA。
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == root || q == root) return root;
    // 左边存在p或者q
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    // 右边存在p或者q
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    // p,q分别位于两侧
    if (left != null && right != null) return root;
    return (left == null) ? right : left;
}
```
以下三个题是 **使用全局变量，递归的时候更新，递归的返回值和最终的结果关系不大** 
124. 二叉树中的最大路径和
给定一个非空二叉树，返回其最大路径和。本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
```Java
private int maxSum;
public int maxPathSum(TreeNode root) {
    if (root == null) return 0;
    maxSum = root.val;
    arrowMaxPath(root);
    return maxSum;
}
private int arrowMaxPath(TreeNode root) {
    if (root == null) return 0;
    int left = arrowMaxPath(root.left);
    int right = arrowMaxPath(root.right);
    // 如何 累加值
    left = (left > 0 ? left + root.val : root.val);
    right = (right > 0 ? right + root.val : root.val);

    maxSum = Math.max(maxSum, left+right-root.val);
    return Math.max(left, right);
}
```
543. 二叉树的直径
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。注意：两结点之间的路径长度是以它们之间边的数目表示。
```Java
int max;
public int diameterOfBinaryTree(TreeNode root) {
    max = 0;
    helper(root);
    return max;
}
private int helper(TreeNode root) {
    if (root == null) {
        return -1;
    }
    if (root.left == null && root.right == null) {
        return 0;
    }
    int left = helper(root.left) + 1;
    int right = helper(root.right) + 1;
    max = Math.max(max, left+right);
    return Math.max(left, right);
}
```
687. 最长同值路径（本题是任意两个节点的路径）
本题和543几乎是同一个题目，给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。注意：这条路径可以经过也可以不经过根节点。两个节点之间的路径长度由它们之间的边数表示。
```Java
// 这种路径是 1.左子树最长同值路径（单箭头路径）  2. 右子树最长同值路径（单箭头路径） 的 最大值
private int longest = 0;
public int longestUnivaluePath(TreeNode root) {
    longest = 0;
    arrowPath(root);
    return longest;
}
private int arrowPath(TreeNode root) {
    if (root == null) return 0;
    int left = arrowPath(root.left);
    int right = arrowPath(root.right);
    int arrowLeft = 0, arrowRight = 0;
    if (root.left != null && root.left.val == root.val) arrowLeft = left + 1;
    if (root.right != null && root.right.val == root.val) arrowRight = right + 1;
    // 更新最终结果是双向的
    longest = Math.max(longest, arrowLeft + arrowRight);
    // 返回的是单向的
    return Math.max(arrowLeft, arrowRight);
}
```

## 剪枝

剪枝的要点就是让当前节点的cur.left=pruning(cur.left)  pruning函数返回当前树的根节点

669. 修剪二叉搜索树
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
```Java
// 重点在于如何剪枝，如何调整节点
// 参考思路：< L , 只保留二叉树的右子树
// > R, 只保留二叉树的左子树
public TreeNode trimBST(TreeNode root, int L, int R) {
    if (root == null) return null;
    // 调整节点(难点)
    // < L, 只保留二叉树的右子树(结果肯定在右边)
    if (root.val < L) return trimBST(root.right, L, R);
    
    // > R, 只保留二叉树的左子树(结果肯定在左边)
    if (root.val > R) return trimBST(root.left, L, R);
    
    root.left = trimBST(root.left, L, R);
    root.right = trimBST(root.right, L, R);
    return root;
}
```
814. 二叉树剪枝
给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。返回移除了所有不包含 1 的子树的原二叉树。(节点 X 的子树为 X 本身，以及所有 X 的后代。)
```Java
// 判断是否含有1
private boolean hasOne(TreeNode root) {
    if (root == null) return false;
    if (root.val == 1) return true;
    return hasOne(root.left) || hasOne(root.right);
}
public TreeNode pruneTree(TreeNode root) {
    if (!hasOne(root)) return null;
    root.left = pruneTree(root.left);
    root.right = pruneTree(root.right);
    return root;
}
// 后续遍历写法
public TreeNode pruneTree2(TreeNode root) {
    if (root == null) return null;
    root.left = pruneTree(root.left);
    root.right = pruneTree(root.right);
    if (root.val == 0 && root.left == null && root.right == null)
        return null;
    return root;
}
```
## 二叉搜索树
二叉搜索树，最常考的性质就是中序遍历的递增，一般的做法是使用递归（中序遍历），然后使用全局变量保存pre节点，然后在中间的时候（中序遍历的时候）更新全局变量。

98. 验证二叉搜索树
给定一个二叉树，判断其是否是一个有效的二叉搜索树， 空树为BST。
```Java
TreeNode pre = null;
public boolean isValidBST(TreeNode root) {
    if (root == null ) {
        return true;
    }
    if (!isValidBST(root.left)) {
        return false;
    }
    if (pre != null && root.val <= pre.val) {
        return false;
    } 
    pre = root;
    return isValidBST(root.right);
}
```
99. 恢复二叉搜索树
二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
```Java
// 难点：找到这两个错误的节点
// 有序的序列，交换两个元素，会导致增长序列出现两个(或者一个)下降点
// 两个下降点: first是第一个下降点处较大的元素；second是第二个下降点处较小的元素
// 一个下降点: first下降点处较大元素；second是下降点处较小元素
private TreeNode first = null;
private TreeNode second = null;
private TreeNode pre;
public void recoverTree(TreeNode root) {
    first = null;
    second = null;
    pre = null;
    traverse(root);
    // 交换 first 和 second
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
}
private void traverse(TreeNode root) {
    if (root == null) return;
    traverse(root.left);
    if (pre != null && pre.val > root.val) {
        // 此处是重点
        if (first == null) {
            first = pre;
            second = root;  // 注意此处（只有一个下降点时）
        } else second = root;
    }
    pre = root;
    traverse(root.right);
}
```
108. 将有序数组转换为二叉搜索树
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
```Java
// 树尽量平衡 —— 要求数组尽量划分均等(二分)（使用下标进行划分，注意数目的奇偶）
// 0 1 2 3 4 5
public TreeNode sortedArrayToBST(int[] nums) {
    TreeNode root = helper(nums, 0, nums.length-1);
    return root;
}
private TreeNode helper(int[] nums, int i, int j) {
    if (i < 0 || j >= nums.length ||  i > j) return null;
    int mid = (i+j)/2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = helper(nums, i, mid-1);
    node.right = helper(nums, mid+1, j);
    return node;
}
```
109. 将有序链表转换为二叉搜索树
```Java
public TreeNode sortedListToBST(ListNode head) {
    // while (tail.next != null) tail = tail.next;
    return helper(head, null);
}
private TreeNode helper(ListNode head, ListNode tail) {  // 左闭右开
    if (head == tail) return null;
    if (head.next == tail) return new TreeNode(head.val);
    ListNode slow = head, fast = head;
    
    while (fast != tail && fast.next != tail) {
        slow = slow.next;
        fast = fast.next.next;
    }
    TreeNode root = new TreeNode(slow.val);
    root.left = helper(head, slow); 
    root.right = helper(slow.next, tail);
    return root;
}
```
230. 二叉搜索树中第K小的元素
中序遍历
```Java
int count = 0;
public int kthSmallest(TreeNode root, int k) {
    if (root == null) {
        return -1;
    }
    int left = kthSmallest(root.left, k);
    if (left != -1) {
        return left;
    }
    count++;
    if (count == k) {
        return root.val;
    }
    return kthSmallest(root.right, k);
}
```

450. 删除二叉搜索树中的节点
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变，返回二叉搜索树（有可能被更新）的根节点的引用。
```Java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
        return null;
    }
    if (root.val != key) {
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
    } else {
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        // 难点
        TreeNode rightMaxNode = findRightMax(root);  // 找到右侧最大值
        root.val = rightMaxNode.val;  // 与当前值交换
        rightMaxNode.val = key;
        root.right = deleteNode(root.right, key);  // 在右侧递归
    }
    return root;
}
private TreeNode findRightMax(TreeNode root) {
    TreeNode p = root.right;
    while (p != null && p.left != null) {
        p = p.left;
    }
    return p;
}
```
701. 二叉搜索树中的插入操作
```Java
public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (root.val > val) {
        root.left = insertIntoBST(root.left, val);
    } else {
        root.right = insertIntoBST(root.right, val);
    }
    return root;
}
```

# 数学

## 采样

常见的采样问题（抽到的元素概率相等）：

1. 要求从N个元素中随机的抽取k个元素，其中N的大小未知

- 如果N已知的情况下，可以每次抽一个，然后使用集合判断一下是不是重复了，重复了就重新随机抽取，这种方法重复率很高效率很低，并且要求N已知，如果N不知道就无能为力了（集合的角度）;

- 概率的角度：对于每个下标[0, n)，使用概率判断取还是不取， 取到之后m--

  使用条件概率计算每一个元素的概率均是m/n，对于第二个元素概率计算方法为`第一个元素取到的概率*第二个元素取到的概率+第一个元素没有被取到的概率*第二个元素被取到的概率`）

```Java
Knuth：
	for i = [0,n)
	     if (rand() % n) < m
	             print  i
	             m --
	     n--
优化：
	int genKnuth(int m,int n) {
	    int i;
	    for(i=0;i<n;i++)
	        if(rand()%(n -i) < m) {
	            printf("%d\n", i);
	            m--;
	        }
	    return 0;
	}
```

- shuffle思想：打乱数组，然后取前m个。
```Java
public void knuthShuffle(int[] arr) {
    int n = arr.length;
    Random random = new Random();
    for (int i = n-1; i >= 0; i--) {
        swap(arr, i, random.nextInt(i+1));  // [0, i] 包括i
    }
}
```
蓄水池抽样算法
2. 给出一个数据流，这个数据流的**长度很大**或者未知。并且对该数据流中数据只能访问一次。请写出一个随机选择算法，使得数据流中所有数据被选中的概率相等。
```Java
void selectKItems(int stream[], int n, int k) { 
    int i;
    // 创建长度为k的蓄水池，将前k个元素放入蓄水池
    int reservoir[] = new int[k]; 
    for (i = 0; i < k; i++) {
        reservoir[i] = stream[i]; 
    }
    Random r = new Random();
    // 从下标k开始
    for (; i < n; i++) {
        // 每次取[0,i]随机数
        int j = r.nextInt(i+1);
        // 取得的随机位置在蓄水池长度内，更新此位置元素
        if (j < k) reservoir[j] = stream[i];
    }   
} 
```
假设i=n时候，蓄水池中的K个元素中的任一个，被选中的概率是 k/n
当i=n+1时，前的K个元素的，每个元素被选中（留在蓄水池的概率是）（k/n）*(n/(n+1)) = k / (n+1)

> 其中n/(n+1)是怎么的出来的呢？：蓄水池中的元素被替换，说明第n+1个数字被选中，概率为 k/(n+1), 与蓄水池中的任意一个1/k交换，概率为  k/(n+1)   *  1/k = 1/(n+1)， 那么没有被选中概率(留在蓄水池)就为  1-1/(n+1) = n/(n+1)

当i= n+1时也成立，所以每一个元素被选中的概率 为 K/N

拒绝采样: 使用rand7生成rand10

```Java
    public int rand7() {
        Random random = new Random();
        return random.nextInt(7)+1;
    }
    /*
    int seed7[7][7] = {
    {1 , 2 , 3 , 4 , 5 , 6 , 7},
    {8 , 9 , 10, 1 , 2 , 3 , 4},
    {5 , 6 , 7 , 8 , 9 , 10, 1},
    {2 , 3 , 4 , 5 , 6 , 7 , 8},
    {9 , 10, 1 , 2 , 3 , 4 , 5},
    {6 , 7 , 8 , 9 , 10, 0 , 0},
    {0 , 0 , 0 , 0 , 0 , 0 , 0}};
    // 如果在前40以内，1-10每一个数被取到的概率为1/10, 很典型的拒绝采样问题
     */
    public int rand10() {
        int i;
        do {
            i = (rand7()-1) * 7 + rand7();
        } while (i > 40);
        return i % 10 + 1;
    }
```

## 数论

最大公约数 —— 欧几里得算法

```Java
int euclid(int m, int n) {
	return n == 0 ? m : euclid(n, m%n);
}
// 最小公倍数
int lcm(int m, int n) {
    return (m*n)/(euclid(m, n));
}
多个数的最大公约数：a b c 的最大公约数=a b的最大公约数 与 c 的最大公约数
多个数的最小公倍数：a b c 的最大公倍数=a b的最小公倍数 与 c 的最小公倍数
```

筛选法求素数

大于1的数中，只能被1和它本身整除的数称为素数，否则称为合数，小于1的数既不是素数又不是合数。2是素数，把2的倍数筛选出去；3是素数，把3的倍数筛选出去 ... 使用`boolean`数组。
```Java
public int countPrimes(int n) {
    boolean[] notPrime = new boolean[n];
    notPrime[0] = true;
    notPrime[1] = true;
    int count = 0;
    for (int i = 2; i < n; i++) {
        if (notPrime[i]) {
            continue;
        }
        // 是素数
        count++;
        // 优化：只筛选 比 i小的素数 与 i 的乘积
        for (int j = i*2; j < n; j+=i) {
            notPrime[j] = true;
        }
    }
    return count;
}
```

## 位运算

136. 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。  扩展：除了某两个元素只出现一次外，其余均出现两次。
思路：使用异或^ , 出现两次的异或之后肯定为0，全部数字异或，最终结果即那个只出现一次的数字。
如果某两个元素出现一次，还是先异或，最终结果，找到是1的那一位；然后按照这一位将所有数字分成两份，这样，这两个只出现一次的数字，就分成了两份，就变成了上面的情况了！

137. 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
每一位相加能被3整除说明这一位出现了3次，那个只出现一次的数这一位是0；不能被3整除，说明那个只出现一次的数这一位是1。

268. 缺失的数字
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
```Java
// 0-n中找n个数，会缺一个，然后将这n个数和下标一一对应，缺失的那个数字只出现了一次，其余的数字出现了两次
public int missingNumber2(int[] nums) {
    int ret = nums.length;  // 注意初值
    for (int i = 0; i < nums.length; i++) {
        ret = (nums[i] ^ i ^ ret);
    }
    return ret;
}
```
191. 二进制1的个数
输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）
思路：数字-1与该数字相与，会将n二进制位的最后一个1变为0

201. 数字范围按位与
给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）
```Java
// 按数字范围与会得到什么？？会得到高位无变化的部分
public int rangeBitwiseAnd(int m, int n) {
    int i = 0;
    while (m != n) {
        m >>= 1;
        n >>= 1;
        i++;
    }
    return m << i;
}
```

## 数字的转换

## 特殊的数字

9. 判断回文数

将数倒转：`reversed = reversed * 10 + (number % 10)`, 与原数相等即为回文数。

65. 有效的数字

数字 0-9    指数 - "e"     正/负号 - "+"/"-"         小数点 - "."

263. 264. 丑数 

判断是否是丑数（就是只包含质因数 2, 3, 5 的正整数）。264：第n个丑数，和筛选法求素数似的，不断的生成新的丑数

```java
public int nthUglyNumber(int n) {
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
```

279. 完全平方数

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

```Java
public int numSquares(int n) {
    int[] dp = new int[n+1];
    for (int i = 1; i <= n; i++) {
        dp[i] = i;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j*j <= i; j++) {
            dp[i] = Math.min(dp[i], dp[i-j*j]+1);
        }
    }
    return dp[n];
}
```

## 其他

50. Pow(m,n)快速幂算法

```Java
// 二分，注意N正负  —— 快速幂算法
public double myPow(double x, int n) {
    // 边界一定要注意！！
    if (x == 1.0 || n == 0) return 1;
    if (x == -1.0) return ((n&1)==0) ? 1 : -1;
    if (n == (1<<31)) return 0;
    if (n > 0) return pow(x, n);
    else return 1.0/pow(x, -n);
}
// 递归会导致栈溢出 如何转化成 循环？
// 指数每次减半，底数每次增加1倍，指数是奇数的时候最终结果要乘一个（落单的）当前底数
private double powIter(double x, int posN) {
    double ret = 1.0;
    while (posN > 0) {
        if ((posN&1)==0) {  // 偶数
            posN = (posN >> 2);
            x = x * x;  // 底数每次增加
        } else {
            ret *= x;
            posN = ((posN - 1) >> 2);
            x = x * x;
        }
    }
    return ret;
}
```

169. 求众数

众数一定存在，求众数，投票法。

```Java
    public int majorityElement21(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
```

# 动态规划

## 单序列DP

### 子序列/子串问题
子序列问题一般是转化成双序列问题（二维）来解决的，只不过习惯于使用滚动数组进行状态压缩而已

### 经典股票题
其实股票题目是一个**多状态的DP**问题

### 多状态的DP
- 经典的**打家劫舍**题
- 铺地板问题

### 未分类
经典题
- 爬楼梯
- 单词拆分

## 双序列DP
- 正则表达式匹配
- 通配符匹配
- 编辑距离
- 最长公共子序列

## 棋盘类DP

## 背包问题

## 游戏（博弈）DP

## 划分型DP


