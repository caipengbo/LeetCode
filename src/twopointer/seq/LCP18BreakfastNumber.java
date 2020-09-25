package twopointer.seq;

import java.util.Arrays;



/**
* Title: LCP 18 早餐组合
* Desc: 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。

注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
* Created by Myth on 09/12/2020 in VSCode
*/

public class LCP18BreakfastNumber {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt=0;
        int m=staple.length,n=drinks.length;
        int i=0,j=n-1;
        while(i<m&&j>=0){
            if(staple[i]+drinks[j]<=x){
                cnt=(cnt+j+1)%1000000007;
                i++;
            }else{
                j--;
            }
        }
        return cnt%1000000007;
    }
}
