- *代表需要重视的程度
- 重视的题型：排列组合

# Medium
## 1. 两数之和
求数组内=target的元素下标，HashMap

## 3. 无重复字符的最长子串
使用boolean[] 256做hash桶，使用双指针，前指针往前移填充hash，发现重复的话（更新结果），后指针前移，擦除hash，循环检查前指针指向的字符是否重复，重复就后指针前移，不重复就前指针前移。

## 5. **最长的回文子串
DP, 二维DP，填左半边，从下往上，从左往右 子串 dp[i, j] = (char_i == char_j) && dp[i+1, j-1]
```Java
for (int i = len-1; i >= 0; i--) {
    for (int j = i+1; j < len; j++) {
        if (s.charAt(i) == s.charAt(j) && (i+1 > j-1 || dp[i+1][j-1])) {
            dp[i][j] = true;
            if (j-i+1 > longest) {
                longest = j - i + 1;
                start = i;
            }
        }
    }
}
```
## 6. Z字形变换
Z字形（从上到下，从左到右排列字符串，然后按行收集），关键：控制打印方向 flag 

## 7. 整数反转
直接使用取余即可，不用区分正数负数，负数对10取余，是末尾的数字加个负号（Java是截断除法），判断溢出可以判断在ret*10+last前面判断与MAX_VALUE/10的关系。

## 8. atoi字符串转整数（未做）
溢出需要全面考虑

## 9. 回文数
不将整数转化成字符串，按照第7题，将数字反转，看看是否和原数字一样。

## 11. *盛最多水的容器
双指针，头尾各一个指针，一开始宽是最长的，所以移动短的那一端，高才有可能增加，进而面积才有可能增加。

## 12. 13 罗马数字和整型的互相转化（未做）

## 14. 最长的公共前缀
字符串数组中的最长公共前缀。很简单，索引从0开始到 最短字符串的长度len，然后遍历每个串，求当前索引位置的字符是否都相等，遇到不相等的，直接返回（公共前缀就到此）。

## 15. 三数之和
固定一个数字，然后就转化成了两数之和问题，可以使用排序+双指针写法；或者使用Hash方法。

## 16. 最接近的三数之和
双指针，和上题基本一样，固定一个数字，然后头尾双指针移动，双指针移动的方法也一样，只不过就是在内部要更新一下接近值。

## 17. *电话号码的字母组合

## 18. 四数之和
三数之和升级版，就是在三数之和的基础之上多加了一层循环，前面循环两个数，然后最后变成两数之和。

## 19. 删除链表倒数第N个结点
一个指针先走N步，然后另一个指针开始走，第一个指针走到头，第二个指针指向的位置就是倒数第N个结点。不用非得
```Java
while (fast.next != null) {
    if (n <= 0) slow = slow.next;
    fast = fast.next;
    n--;
}
slow.next = slow.next.next;
```
## 20. 有效的括号
使用栈，遍历当前字符，是)]}时候，pop出栈顶元素，查看是否匹配；是([{括号入栈。

## 21. 合并两个有序链表
归并算法

## 22. 括号生成
生成n对合法的括号字符串（括号匹配），可以组成有效括号重点是：左右括号的数目
> 非常优秀的一道回溯、DFS类题目，对于排列组合类的回溯，还需多练习。
```Java
private void dfs(int n, int l, int r, String cur, List<String> ret) {
    if (cur.length() == 2*n) {
        ret.add(cur);
        return;
    }
    if (l < n) {
        dfs(n, l+1, r, cur+'(', ret);
    }
    if (r < l) {
        dfs(n, l, r+1, cur+')', ret);
    }
}
```
## 23. 合并K个链表
使用大小为K的堆，找到最小的点，链接到结果链表，然后此节点的下一个节点加入堆（此处也是以前没有想到的地方）

## 24. ***两两交换链表节点
有一个指向前面的p, 然后q 和 q.next是要交换的两个，但是也要注意p要指向q.next。
```Java
public ListNode swapPairsNew(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode p = dummy, q = head;
    while (q != null && q.next != null) {
        p.next = q.next;
        q.next = q.next.next;
        p.next.next = q;
        // 更新 p 和 q
        p = q;
        q = q.next;
    }
    return dummy.next;
}
// 不要和 字符串翻转 代码搞混了
public ListNode swapPairsRecursive(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode nextNode = head.next;
    head.next = swapPairsRecursive(nextNode.next);
    nextNode.next = head;
    return nextNode;
}
```
## 25. ***K个一组，反转链表
```Java
// 翻转链表 递归写法
private ListNode reverse2(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode newHead = reverse2(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
```
## 26. **删除排序数组中的重复项
数组，去除重复项后,返回长度。 这种重复的要考虑相邻的元素是否相等。

## 27. 移除元素
1. 要移除的元素多
保留的少，那么就挑选出要保留的元素，从前往后放置
public int removeElement(int[] nums, int val) {
        int ans = 0;
        for(int num: nums) {
            if(num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }

2. 要移除的元素少
保留的多，那么就将要移除的挪到后面去，要保留的从后往前挪到前面去。
class Solution {
    public int removeElement(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans;) {
            if (nums[i] == val) {
                nums[i] = nums[ans - 1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
## 28. strStr()

## 29. 两数相除

# Hard
## 4. 寻找两个正序数组的中位数

## 10. 正则表达式匹配

## 30. 串联所有单词的子串
