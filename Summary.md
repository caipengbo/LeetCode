# LeetCode总结

# 目录

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
  - 位运算
  - 数论：素数
  - 概率：洗牌算法、蓄水池抽样、蒙特卡洛
  - 特殊的数
  - 数字的转化
- 动态规划
- 排序
- 数据结构
  - 单调栈
  - 单调队列
- 图
- 分治
- 贪心

# 心法宝典

1. 递归要素：开头-判断边界（退出条件）；中间-进行相关的计算、缩小范围递归（经常用到全局变量哦）；结尾-返回值（还要学会如何利用返回值）

# 搜索

## 1. 回溯
回溯就是有规律的遍历，改变状态，然后再该回来状态(回溯)。
### 模版
参数：原始参数（题目必备的参数）、start（开始位置）,  cur（当前答案）, ans（保存的所有答案）;
Java中注意如果将当前答案添加到最终ans list时，要new一个新的对象
```Java
// 找到所有方案
void findSolutions(n, other params) {
    if (found a solution) {  // 找到一个答案
        // solutionsFound = solutionsFound + 1;
        addSolution();  // 将当前solution添加到solution list中
        // if (solutionsFound >= solutionTarget) 
        return  // System.exit(0);
    }
    // 有的写不成循环，要分多个情况进行讨论；本质上循环就是多个情况，只不过写起来比较简单而已
    for (val = first to last) {  // or start（开始位置参数） to last
        if (!isValid(val, n)) continue;  // 剪枝 
        applyValue(val, n);  // 改变参数
        findSolutions(n+1, other params);  // 递归
        removeValue(val, n);  // 取消 改变参数
    }
}
```
> 还有一种方案是：将每一步的剪枝移动到循环的前部，让其 return false(见79题)，具体采取哪种方案视情况而定

```Java
// 一个方案是否存在
boolean findSolutions(n, other params) {
    if (found a solution) {
        displaySolution();
        return true;
    }
    for (val = first to last) {  // or start（开始位置参数）to last
        if (!isValid(val, n)) continue;
        applyValue(val, n);
        if (findSolutions(n+1, other params)) return true;
        removeValue(val, n);
    }
   return false;
}
```
### 例题

37. 数独
解9 X 9的数独
```Java
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        backtracking(board, 0, 0);
    }
    // row, col  当前位置
    private boolean backtracking(char[][] board, int row, int col) {
        for (int i = row; i < 9; i++) {
            for (int j = col; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (backtracking(board, i, j+1)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
            col = 0; // 注意此处
        }
        return true;
    }
    // 在(row, col) 位置 放置 c,是否有效
    private boolean isValid(char[][] board, int row, int col, char c) {
        int x, y; // 3X3 index
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.'&& board[row][i] == c) return false; 
            if (board[i][col] != '.' && board[i][col] == c) return false; 
            // 3X3 格 难点！！！
            x = 3 * (row / 3) + i / 3;
            y = 3 * (col / 3) + i % 3;
            if (board[x][y] != '.' && board[x][y] == c) return false;
        }
        return true;
    }
```
51. N皇后问题
N个皇后放置在 n X n 的棋盘上，不能同行， 不能同列，不能同对角线

```Java
public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> ans = new ArrayList<List<String>>();
        backtracking(board, 0, ans);
        return ans;
    }
    private void backtracking(char[][] board, int col, List<List<String>> ans) {
        if (col == board.length) {
            ans.add(generateAns(board));
            return;
        }
        // 从左往右 按列放置 皇后
        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                backtracking(board, col+1, ans);
                board[i][col] = '.';
            }
        }
    }
    // （x,y） 位置是否可以放置 皇后
    // 主对角线: 有 行号 - 列号 = 常数
    // 次对角线有 行号 + 列号 = 常数
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) { // 按列放，所以只要求 <y 的列没被放置即可
                if (board[i][j] == 'Q' && (x-y == i-j || x+y == i+j || x == i)) {
                    return false;
                }
            }
        }
        return true;
    }
```

79. Word Search
给定一个二维网格和一个单词，找出该单词是否存在于网格中，单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
> 注意：同一个单元格内的字母不允许被重复使用。 

```Java
private boolean backtracking(char[][] board, int i, int j, String word, int charPos) {
        if (charPos == word.length()) return true;
        // 将判断移到此处来
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        char c = board[i][j];
        if (c == '#' || c != word.charAt(charPos)) return false;
        board[i][j] = '#';
        if (backtracking(board, i-1, j, word, charPos+1)) return true;
        if (backtracking(board, i, j-1, word, charPos+1)) return true;
        if (backtracking(board, i+1, j, word, charPos+1)) return true;
        if (backtracking(board, i, j+1, word, charPos+1)) return true;
        board[i][j] = c;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) return false;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
```
93. 复原IP地址
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
> 输入: "25525511135"  输出: ["255.255.11.135", "255.255.111.35"]

```Java
public List<String> restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        LinkedList<String> cur = new LinkedList<>();
        restoreIpAddresses(s, ans, cur, 0);
        return ans;
    }
    private void  restoreIpAddresses(String s, List<String> ans, LinkedList<String> cur, int pos) {
        if (cur.size() == 4) {
            if (pos == s.length()) {
                ans.add(String.join(".", cur));
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break;
            String seg = s.substring(pos, pos+i);
            if ((i > 1 && seg.startsWith("0")) || Integer.valueOf(seg) > 255) break;
            cur.addLast(seg);
            restoreIpAddresses(s, ans, cur, pos+i);
            cur.removeLast();
        }
    }
```
## 2. BFS
BFS 总是优先搜索距离根节点近的节点，因此它搜索到的路径就是最短路径。
***例题***

127. Word Ladder 
单词接龙：给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：每次转换只能改变一个字母；转换过程中的中间单词必须是字典中的单词。
说明: 如果不存在这样的转换序列，返回 0；所有单词具有相同的长度；所有单词只由小写字母组成；字典中不存在重复的单词；你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
```Java
private int bfs(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0; // endWord不在list中，直接返回
        queue.add(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size(); i > 0; i--) {
                String word = queue.poll();
                // 扩展新的一层节点 Wordlist = expand(word)
                char[] wordChars = word.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char ch = wordChars[j];
                    for (char k = 'a'; k <= 'z'; k++) { // 替换每一位， 产生新的单词比较一下，是否在wordList中
                        if (ch == k) continue;
                        wordChars[j] = k;
                        String newWord = new String(wordChars);
                        // 如果最终结果在 新获得的节点中 ，返回
                        if (newWord.equals(endWord)) return step+1;
                        if (!set.contains(newWord)) continue;
                        set.remove(newWord); // 从wordList中移除，放置重复操作
                        queue.add(newWord);
                    }
                    wordChars[j] = ch; // 恢复被替换的位置
                }
            }
        }
        // 没找到
        return 0;
    }
```
542. 01 矩阵
给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。两个相邻元素间的距离为 1 
```Java
// 从0开始扩展，和 0 挨着的距离为 1，然后把和0挨着的往外接着扩展，依次距离+1
private int[][] bfs(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    used[i][j] = true;
                }
            }
        }
        // 四个方向
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : directions) {
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || used[r][c]) continue;
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1; // 周边元素+1
                used[r][c] = true;
                queue.offer(new int[]{r, c});
            }
        }
        return matrix;
}
```

752. 打开转盘锁
一个4位密码锁，转轮可以正向和逆向旋转，目标target不会在deadends里面出现。
```Java
private int bfs(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains(target)) return -1;
        Queue<String> queue = new LinkedList<>();
        int level = -1;
        char temp;
        queue.add("0000");
        while (!queue.isEmpty()) {
            level++;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                String node = queue.poll();
                if (node.equals(target)) return level;
                if (deadSet.contains(node)) continue;
                deadSet.add(node);
                // 扩展节点
                char[] chars = node.toCharArray();
                for (int j = 0; j < 4; j++) { // 改变每一位
                    // 正向扩展
                    temp = chars[j];
                    chars[j] = (temp == '9' ? '0' : (char) (temp + 1));
                    queue.add(new String(chars));
                    // 逆向扩展
                    chars[j] = temp;
                    chars[j] = (temp == '0' ? '9' : (char) (temp - 1));
                    queue.add(new String(chars));
                    chars[j] = temp;
                }
            }
        }
        return -1;
    }
    public int openLock(String[] deadends, String target) {
        return bfs(deadends, target);
    }
```
1162. 地图分析（多源BFS）
大小为 N x N 的grid，上面的每个单元格都用 0 和 1 标记好了；其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
> 海洋到陆地的最近距离，让这个最近距离最远。
```Java
public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        // grid==2 已经被访问
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[] {i, j});
            }
        }
        int count = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0]-1 >=0 && grid[cur[0]-1][cur[1]] == 0) {
                    grid[cur[0]-1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]-1, cur[1]});
                }
                if (cur[0]+1 < m && grid[cur[0]+1][cur[1]] == 0) {
                    grid[cur[0]+1][cur[1]] = 2;
                    queue.add(new int[] {cur[0]+1, cur[1]});
                }
                if (cur[1]-1 >= 0 && grid[cur[0]][cur[1]-1] == 0) {
                    grid[cur[0]][cur[1]-1] = 2;
                    queue.add(new int[] {cur[0], cur[1]-1});
                }
                if (cur[1]+1 < n && grid[cur[0]][cur[1]+1] == 0) {
                    grid[cur[0]][cur[1]+1] = 2;
                    queue.add(new int[] {cur[0], cur[1]+1});
                }
            }
        }
        return count <= 0 ? -1 : count;
}
```
## 3. 排列、组合
**排列**
46. 全排列
给定一个 没有重复数字 的序列，返回其所有可能的全排列。
```Java
private void backtracking(int[] nums, int count, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(cur));

            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // if (cur.contains(nums[i])) continue; 使用一个boolean数组来记录是否的包含该元素
            if (!visited[i]) {
                visited[i] = true;
                cur.add(nums[i]);
                backtracking(nums, count+1, visited, cur, ans);
                visited[i] = false;
                cur.remove(cur.size()-1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        boolean[] visited = new boolean[nums.length];
        List<Integer> cur = new ArrayList<>();
        backtracking(nums, 0, visited, cur, ans);
        return ans;
    }
```
47. 全排列2
给定一个可包含重复数字的序列，返回所有不重复的全排列。
```Java
private void backtracking(int[] nums, int count, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                // 去重, 注意 !visited[i-1] 这个判断条件
                // 如果不加这个条件的话，就永远不能选重复的元素，把所有的枝都减了！！！
                if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
                // if (i > 0 && nums[i] == nums[i-1]) continue;
                visited[i] = true;
                cur.add(nums[i]);
                backtracking(nums, count+1, visited, cur, ans);
                visited[i] = false;
                cur.remove(cur.size()-1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        boolean[] visited = new boolean[nums.length];
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, 0, visited, cur, ans);
        return ans;
    }
```
784. 字母大小写全排列
给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
```Java
   private void backtracking(String S, int start, char[] cur, List<String> ans) {
        ans.add(new String(cur));
        for (int i = start; i < S.length(); i++) {
            if (S.charAt(i) >= 'a' && S.charAt(i) <= 'z') {
                cur[i] = (char) (cur[i] - 'a' + 'A');
                backtracking(S, i+1, cur, ans);
                cur[i] = (char) (cur[i] - 'A' + 'a');
            }
            if (S.charAt(i) >= 'A' && S.charAt(i) <= 'Z') {
                cur[i] = (char) (cur[i] - 'A' + 'a');
                backtracking(S, i+1, cur, ans);
                cur[i] = (char) (cur[i] - 'a' + 'A');
            }
        }
    }
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        backtracking(S, 0, S.toCharArray(), ans);
        return ans;
    }
```
996. 正方形数组的数目
给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。
```Java
    private int ans = 0;
    private boolean isSquare(int number) {
        int a = (int) Math.sqrt(number);
        return a*a == number;
    }
    private void backtracking(int[] A, int count, boolean[] used, List<Integer> cur) {
        if (count == A.length) {
            ans++;
            return;
        }
        for (int i = 0; i < A.length; i++) {
            // 仍然是排列那一套
            if (i > 0 && A[i] == A[i-1] && !used[i-1]) continue;
            if (!used[i]) {
                if (cur.size() == 0 || isSquare(A[i]+cur.get(cur.size()-1))) {
                    cur.add(A[i]);
                    used[i] = true;
                    backtracking(A, count+1, used, cur);
                    used[i] = false;
                    cur.remove(cur.size()-1);
                }
            }
        }
    }
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A); // 涉及到去重
        boolean[] used = new boolean[A.length];
        backtracking(A, 0, used, new ArrayList<>());
        return ans;
    }
```
**组合**
17. 组合电话号码
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
```Java
private String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // DFS 递归函数
    // pos是位置，cur 是当前生成的串
    private void dfs(String digits, int pos, String cur, List<String> ans) {
        // dfs end
        if (pos >= digits.length()) {
            ans.add(cur);
            return;
        }
        // loop and recursive
        String letters = map[digits.charAt(pos)-'0'];
        for (int i = 0; i < letters.length(); i++) {
            // 注意此处
            // Error: cur = cur + letters.charAt(i);
            String newCur = cur + letters.charAt(i);
            dfs(digits, pos+1, newCur, ans);
            // 这个地方没有做 remove 操作，因为 new 了一个新的cur出来（便于add到List中）,以前的cur没有改变
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (!"".equals(digits)) {
            dfs(digits, 0, "", ans);
        }
        return ans;
    }
```
22. 括号生成
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
```Java
    // 回溯(不带循环的回溯，因为每种选择不太一样，无法使用循环语句写出)
    // 结束：串的长度=2*n
    // 限制条件：左括号小于n时都可以放； 右括号小于左括号数目时才可以放
    private void backtracking(int n, int open, int close, String cur, List<String> ans) {
        if (cur.length() == 2*n) {
            // 不用new String，因为String每次会产生新的对象
            ans.add(cur);
            return;
        }
        if (open < n) {
            // 注意 cur+"(" 会产生新的对象
            backtracking(n, open+1, close, cur+"(", ans);
        }
        if (close < open) {
            backtracking(n, open, close+1, cur+")", ans);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtracking(n, 0, 0, "", ans);
        return ans;
    }
```
39. Combination Sum
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
> 说明：candidates 中的数字可以无限制重复被选取；所有数字（包括 target）都是正整数；解集不能包含重复的组合；和 17题的区别：17题没有约束条件（DFS列举出所有的情况），39题有约束条件（可以剪枝、回溯）。

相关题目：40、216
```Java
private void backtracking(int[] candidates, int target, int i, List<Integer> cur, List<List<Integer>> ans) {
    if (target == 0) {
        ans.add(new ArrayList<>(cur));
        return;
    }
    for (int j = i; j < candidates.length; j++) {
        if (candidates[j] > target) break; // 剪枝 因为排序了，所以后面的数字肯定都大于target
        cur.add(candidates[j]);
        backtracking2(candidates, target-candidates[j], j, cur, ans);
        cur.remove(cur.size()-1);
    }
}
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    if (target <= 0) return ans;
    Arrays.sort(candidates);
    backtracking(candidates, target, 0, new ArrayList<>(), ans);
    return ans;
}
```
40. Combination Sum 2
在39的基础上，允许candidates有重复元素,
说明：candidates 中的每个数字在每个组合中 只能 使用一次(39题是可以重复选择任意次)；所有数字（包括目标数）都是正整数；解集不能包含重复的组合。
```Java
    // 回溯（需要排序candidates）
    private void backtracking(int[] candidates, int target, int i, int sum, List<Integer> cur, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            // 去除重复组合
            if (j > i && candidates[j] == candidates[j-1]) continue;
            if (sum+candidates[j] > target) break; // 剪枝（要求candidates增序）
            cur.add(candidates[j]);
            // 每个数字只能选一次 所以j+1
            backtracking(candidates, target, j+1, sum+candidates[j], cur, ans);
            cur.remove(cur.size()-1);
        }

    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (target <= 0) return ans;
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0, new ArrayList<>(), ans);
        return ans;
    }
```
77. 组合
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
```Java
    private void backtracking(int n, int k, int start, List<Integer> cur, List<List<Integer>> ans) {
        if (k == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= n; i++) {
            cur.add(i);
            backtracking(n, k-1, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <=0 || n <= 0) return ans;
        List<Integer> cur = new ArrayList<>();
        backtracking(n, k, 1, cur, ans);
        return ans;
    }
```
216. 组合2
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。说明：所有数字都是正整数；解集不能包含重复的组合。
```Java
    // 回溯法
    private void backtracking(int k, int n, int start, List<Integer> cur, List<List<Integer>> ans) {
        if (k == 0 && n == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (n < i) break; // 剪枝
            cur.add(i);
            backtracking(k-1, n-i, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <=0 || n <= 0) return ans;
        List<Integer> cur = new ArrayList<>();
        backtracking(k, n, 1, cur, ans);
        return ans;
    }
```
## 4. 分割、子集

131. 分割回文串
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
```Java
    private void backtracking(String s, int start, List<String> cur, List<List<String>> ans) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                cur.add(s.substring(start, i+1));
                backtracking(s, i+1, cur, ans);
                cur.remove(cur.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end)
            if(s.charAt(start++) != s.charAt(end--)) return false;
        return true;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }
```
698. 划分K个相等的子集
给定一个整数数组  nums(nums[i] > 0) 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4  输出： True    说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
```Java
    private boolean backtracking(int[] nums, int k, int target, int cur, int start, boolean[] used) {
        if (k == 0) return true;
        if (cur == target) {
            // 构建下一个集合
            return backtracking(nums, k-1, target, 0, 0, used);
        }
        for (int i = start; i < nums.length; i++) {
            if (!used[i] && cur+nums[i] <= target) {
                used[i] = true;
                if (backtracking(nums, k, target, cur+nums[i], i+1, used)) return true;
                used[i] = false;
            }
        }
        return false;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (maxNum < nums[i]) maxNum = nums[i];
        }
        if (sum % k != 0 || maxNum > sum/k) return false;
        boolean[] used = new boolean[nums.length];
        return backtracking(nums, k, sum/k, 0, 0, used);
    }
```
78. 子集
给定一组 不含重复元素 的整数数组 nums，返回该数组所有可能的子集（幂集）。说明：解集不能包含重复的子集。
```Java
    private void backtracking(int[] nums, int start, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            backtracking(nums, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtracking(nums, 0, cur, ans);
        return ans;
    }
```
90. 子集2
在 78 题的基础上，nums是可能包含重复元素的整数数组。
```Java
    private void backtracking(int[] nums, int start, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            // 和上一个数字相等就跳过去, 注意 i > start
            if (i > start && nums[i] == nums[i-1]) continue;
            cur.add(nums[i]);
            backtracking(nums, i+1, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, 0, cur, ans);
        return ans;
    }
```
## 5. 图的搜索
图的搜索DFS 和 BFS，常用的就是DFS，矩阵上搜索，连通性、Flood Fill问题。练习DFS，也就练习了递归。DFS重要的就是染色（谁访问过，做的标记是不一样的）。

200. 岛屿的数量
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
```Java
public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // bfs(grid, i, j);
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
// DFS
private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
```
695. 岛屿的最大面积
改编自200题

```Java
    int count = 0;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count = 0;
                if (grid[i][j] == 1) dfs(grid, m, n, i, j);
                max = Math.max(max, count);
            }
        }
        return max;
    }
    private void dfs2(int[][] grid, int m, int n, int i, int j) {
        if (i < 0 || j < 0 || i >=m || j >= n) return;
        if (grid[i][j] == 0) return;
        grid[i][j] = 0;
        count++;
        dfs(grid, m, n, i-1, j);
        dfs(grid, m, n, i+1, j);
        dfs(grid, m, n, i, j-1);
        dfs(grid, m, n, i, j+1);
    }
```
733. 图像渲染
相连的同颜色上色，最后返回经过上色渲染后的图像。
```Java
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) return image;
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        int m = image.length, n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oldColor || image[i][j] == newColor) return;
        image[i][j] = newColor;
        dfs(image, i-1, j, oldColor, newColor);
        dfs(image, i+1, j, oldColor, newColor);
        dfs(image, i, j-1, oldColor, newColor);
        dfs(image, i, j+1, oldColor, newColor);
    }
```
802. 找到最终的安全状态
     在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K,  无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。哪些节点最终是安全的？ 结果返回一个有序的数组。
     该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数.  图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。

     <img src="./pic/p802.png" style="zoom:50%;" />

```Java
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ret = new LinkedList<>();
        int[] flag = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            dfs(graph, i, flag);
        }
        for (int i = 0; i < graph.length; i++) {
            if (flag[i] == 1) ret.add(i);
        }
        return ret;
    }
    private boolean dfs(int[][] graph, int i, int[] flag) {
        if (flag[i] == -1) return true;
        if (flag[i] == 1) return false;
        flag[i] = -1;
        for (int adj : graph[i]) {
            if(dfs(graph, adj, flag)) return true;
        }
        flag[i] = 1;
        return false;
    }
```

841. 钥匙和房间
有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。最初，除 0 号房间外的其余所有房间都被锁住。你可以自由地在房间之间来回走动。如果能进入每个房间返回 true，否则返回 false。
```Java
public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] visited = new boolean[rooms.size()];
    visited[0] = true;
    dfs(rooms, 0, visited);
    for (boolean visit : visited) {
        if (!visit) return false;
    }
    return true;
}
private void dfs(List<List<Integer>> rooms, int i, boolean[] visited) {
    for (Integer key : rooms.get(i)) {
        if (!visited[key]) {
            visited[key] = true;
            dfs(rooms, key, visited);
        }
    }
}
```
## 6. 并查集

TODO

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
> - 注意初始位置(`start` 和 `end`), `end = length-1` 和  `end = length` 结果是一样的（？？？n=2的时候剑指offer P3.2题），用作数组下标的时候最好使用 `end = length-1`
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
# 双指针

双指针主要分为三类：左右（一般是数组或者字符串，经典的有三数之和和四数之和），快慢指针（一般是和链表环有关系），滑动窗口

## 1. 序列

3. 无重复字符的最长子串
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。注意区分子串与子序列区别
```Java
// [i, j), 无重复的时候j++, 有重复的时候i++
public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();
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

