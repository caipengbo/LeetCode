
# 数学

## 采样
采样， 拒绝采样（蒙特卡洛），洗牌算法

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
    // 从后往前，不断的从[0,i]中去随机数与i交换
    // i 代表位置
    for (int i = n-1; i >= 0; i--) {
        swap(arr, i, random.nextInt(i+1));  //  random.nextInt(i+1): [0, i] 包括i 中取随机位置
    }
}
```
1. 对于arr[i],洗牌后在第n-1个位置的概率是1/n（第一次交换的随机数为i）
2. 在n-2个位置概率是[(n-1)/n] * [1/(n-1)] = 1/n，（第一次交换的随机数不为i，第二次为arr[i]所在的位置（注意，若i=n-1，第一交换arr[n-1]会被换到一个随机的位置））
3. n-k 位置： (n-k)/n * (1/(n-k))

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
当i=n+1时，前K个元素，每个元素被选中（留在蓄水池的概率是）（k/n）*(n/(n+1)) = k / (n+1)

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
