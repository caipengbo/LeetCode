import util.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;

import static util.IOUtil.stringToIntegerArray;

/**
 * Title:
 * Desc:
 * Created by Myth on 7/16/2019
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = IOUtil.getBufferReader();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Hello");
            int[] candidates = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);
            System.out.print(target);
        }
    }
}
