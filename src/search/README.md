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