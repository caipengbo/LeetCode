package greedy;

/**
* Title:  134. 加油站
* Desc: 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
说明: 
    如果题目有解，该答案即为唯一答案。
    输入数组均为非空数组，且长度相同。
    输入数组中的元素均为非负数。
* Created by Myth-PC on 23/01/2020 in VSCode
*/
public class P134GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, count, sum = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
        }

        for (int i = 0; i < n; i++) {
            int j = i;
            sum = arr[j];
            count = 0;
            while (sum >= 0) {
                count++;
                if (count == n) return i;
                j = (j+1) == n ? 0 : j+1;
                sum += arr[j];
            }
        }
        return -1;
    }
    // 优化：一次遍历
    // 如果
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length, ans = 0;
        // int[] arr = new int[n];
        int gasSum = 0, costSum = 0;
        for (int i = 0; i < n; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            // arr[i] = gas[i] - cost[i];
        }
        if (costSum > gasSum) return -1;
        // 一定存在(那么就找起始点)
        gasSum = 0;
        costSum = 0;
        for (int i = 0; i < n; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            if (costSum > gasSum) {
                gasSum = 0;
                costSum = 0;
                ans = i + 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        P134GasStation p134 = new P134GasStation();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(p134.canCompleteCircuit(gas, cost));
    }
}