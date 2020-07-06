# 单调栈

## 可以解决的问题
递增求更小、递减求更大

一般的问题都是：在一个序列中，可以慢慢增加（减少），在突然减少（增加）的时候，可以做一些操作

以递增栈为例：
- 下一个更小的元素 Next Less Element（pop时进行操作）
- 前一个更小的元素 Previous Less Element（push时进行操作）

> 1. push当前元素的时候肯定是当前元素比当前栈顶的元素更大（栈顶元素肯定就是当前元素前一个更小的元素）
> 2. pop的时候肯定是当前元素小于栈顶元素，那么当前元素肯定是栈顶元素的下一个更小值

```Java
    pop时候（操作），
        cur 是 peek的下一个小的元素
             // 错误说法：cur的前一个最大是peek(错误：因为会重复更新cur)： 这是一个循环前面的都是比cur大的，第84题就是利用这个
        
        // 注意：有的进不去pop循环，说明他没有next less element，可以通过赋初始值进行处理
        
    push 的时候（操作）：
        cur的前一个小的元素 是 peek
            // 错误说法：peek的下一个大的是cur（错误，有的peek弹出栈了，不能进行赋值）
        // 注意：stack为空的是，要进行判断
```

> 递减栈是下一个（前一个）更大的元素

## 模版
单调递增栈
```Java
Stack<> stack;  // 存放下标或者元素  Key1

for (int i = 0; i < n; i++) {
    right[i] = n-i;  // 某些元素不能进到pop循环，所以赋初值 Key4
    while (!stack.empty() && A[stack.peek()] > A[i]) {
        int peek = stack.pop();
        right[peek] = i - peek;  //  pop时操作  Next Less Element  Key2
    }
    // 栈为空时需要特殊赋值 Key5
    left[i] = (stack.empty() ? i+1 : i - stack.peek());   // push时操作，Previous Less Element   Key3
    stack.push(i);
}
```