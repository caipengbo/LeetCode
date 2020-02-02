# 回溯

回溯就是有规律的遍历，改变状态，然后再该回来状态(回溯)

**模版**
参数：原始参数（题目必备的参数）、start（开始位置）， cur（当前答案）, ans（保存的所有答案）

Java中注意如果将当前答案添加到最终ans list时，要new一个新的对象
```Java
// 找到所有方案
void findSolutions(n, other params) {
    if (found a solution) {  // 找到一个答案
        // solutionsFound = solutionsFound + 1;
        addSolution();  // 将当前solution添加到solution list中
        // if (solutionsFound >= solutionTarget) : 
            // System.exit(0);
        return
    }
   // 有的写不成循环，要分多个情况进行讨论；本质上循环就是多个情况，只不过写起来比较简单而已
    for (val = first to last) {     // or start（开始位置参数） to last
        if (! isValid(val, n)) continue;  // 剪枝 
        applyValue(val, n);  // 改变参数
        findSolutions(n+1, other params);
        removeValue(val, n);  // 取消 改变参数
    }
}
```

> 还有一种方案是：将每一步的剪枝移动到循环的前部，让其 return false（见79题），具体采取哪种方案视情况而定

```Java
// 一个方案是否存在
boolean findSolutions(n, other params) {
    if (found a solution) {
        displaySolution();
        return true;
    }

    for (val = first to last) {  // or start（开始位置参数） to last
        if ( !isValid(val, n)) continue;
        applyValue(val, n);
        if (findSolutions(n+1, other params)) return true;
        removeValue(val, n);
    }
   return false;
}
```