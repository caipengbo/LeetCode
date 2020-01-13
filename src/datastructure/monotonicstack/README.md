# 单调栈可以解决的问题

- 下一个更小的元素 Next Less Element（pop时进行操作）
- 前一个更小的元素 Previous Less Element（push时进行操作）


以递增栈为例：
```Java
    pop时候（操作），
        peek的下一个小的元素 是 cur
             // 错误说法：cur的前一个最大是peek(错误：因为会重复更新cur)： 这是一个循环前面的都是比cur大的，第84题就是利用这个
        
        // 注意：有的进不去pop循环，说明他没有next less element，可以通过赋初始值进行处理
        
    push 的时候（操作）：
        cur的前一个小的元素 是peek
            // 错误说法：peek的下一个大的是cur（错误，有的peek弹出栈了，不能进行赋值）
        // 注意：stack为空的是，要进行判断
```

> 递减栈是下一个（前一个）更大的元素

# 模版

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