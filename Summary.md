# LeetCode总结

# 目录：

- 搜索(回溯、BFS、DFS): 
	- 1. 回溯：数独、N皇后、37、51、79、93、[212、301]
	- 2. BFS：矩阵、单词变换
	- 3. 排列、组合、分割、子集：四大类问题，常用回溯、DFS解决
	- 4. 图的搜索：DFS、BFS、并查集、Flood
	- 5. 并查集
- 二分查找：
- 双指针：
  - 左右指针：数组（或字符串）问题，二分查找也算是双指针
  - 快慢指针： 链表中环的问题
  - 滑动窗口
- 链表：
- 二叉树：
- 数学：
  - 位运算
  - 数论
  - 概率：洗牌算法、蓄水池抽样
  - 
- 动态规划
- 排序
- 数据结构
  - 单调栈
  - 单调队列
- 图
- 分治
- 贪心

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
