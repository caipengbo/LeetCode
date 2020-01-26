package math.sampling;

import java.util.Arrays;
import java.util.Random;

/**
* Title: Knuth洗牌算法
* Desc: 
* Created by Myth-PC on 26/01/2020 in VSCode
*/
public class Shuffle {
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void shuffle(int[] arr) {
        int n = arr.length;
        Random random = new Random();
        for (int i = n-1; i >= 0; i--) {
            swap(arr, i, random.nextInt(i+1));  // [0, i] 包括i
        }
    }
    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle();
        int[] arr = {0,1,2,3,4,5};
        shuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}