# Tree

对于树，首先就要弄清前序、中序、后序，基本上所有树的题目都是在遍历的基础上进行修改的

树的题目基本上都是递归的思想，是自顶向下的，所以先考虑特殊的（小的）树，先想好递归退出条件，然后在进行递归操作。

## 遍历

1. 三种遍历的递归写法
2. 迭代写法

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

## 根据遍历序列构建树

105 前中(唯一)
106 中后(唯一)
889 前后(不唯一)

如果一道面试题要求处理一棵二又树的遍历序列，则可以先找到二叉树的根节点，再基于根节点把整棵树的遍历序列拆分成左子树对应的子序列和右子树对应的子序列，接下来再递归地处理这两个子序列。本面试题应用的是这种思路，面试题7“重建二叉树"就是这种思路

## 返回一个值，使用两个值

典型题目：124、543、687

返回的是单个值，但是在递归函数内部，存在更新全局变量的操作，最终返回的是这个全局变量。