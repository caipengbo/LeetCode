package greedy;

import java.util.Arrays;

/**
 * Title: 135. 分发糖果 
 * Desc: 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果： 每个孩子至少分配到 1 个糖果。 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？ 
 * Created by Myth-PC on 23/01/2020 in VSCode
 */
public class P135Candy {
    // 在遍历过程中，每一步都尽量少给糖，必须加的时候加一个，
    // 体现了贪心思想：在每次选择时，以局部最优为导向，而不考虑此次操作对以后操作的影响。
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n < 2) return n;
        int[] left = new int[n];
        int[] right = new int[n];
        int count = 0;
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        // 从左到右，比左边的人糖果更多
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) left[i] = left[i-1] + 1;
        }
        // 从右边到左，比右边的人糖果更多
        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) right[i] = right[i+1] + 1;
        }
        // 选择更大的，这样就符合上面的两个条件，比周围的人都多
        for (int i = 0; i < n; i++) {
            count += Math.max(left[i], right[i]);
        }
        return count;
    }
    public static void main(String[] args) {
        P135Candy p135 = new P135Candy();
        int[] ratings1 = {1,2,2};
        int[] ratings2 = {1,0,2};
        int[] ratings3 = {3,2,1};
        System.out.println(p135.candy(ratings1));
        System.out.println(p135.candy(ratings2));
        System.out.println(p135.candy(ratings3));
    }
}