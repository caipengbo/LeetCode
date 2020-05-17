# LeetCode总结

# 目录：

- 搜索(回溯、BFS、DFS): 
	- 回溯：数独、N皇后、37、51、79、93、[212、301]
	- BFS：矩阵、单词变换
	- 排列、组合、分割、子集：四大类问题，常用回溯解决
- 二分查找：
- 双指针
- 链表
- 二叉树
- 动态规划
- 排序
- 分治
- 贪心
- 数学
- 数据结构

# 搜索

## 回溯
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
## BFS
BFS 总是优先搜索距离根节点近的节点，因此它搜索到的路径就是最短路径。
### 例题
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
## 排列组合