import search.P40CombinationSum2;
import util.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static util.Input.int2dListToString;
import static util.Input.stringToIntegerArray;

/**
 * Title:
 * Desc:
 * Created by Myth on 7/16/2019
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = Input.getBufferReader();
        String line;
        while ((line = in.readLine()) != null) {
            int[] candidates = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            List<List<Integer>> ret = new P40CombinationSum2().combinationSum2(candidates, target);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}
