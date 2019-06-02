package util;

import java.io.*;

/**
 * Title: 从文件中读取数据, 构建链表或者树
 * Desc: [1, 2, 3]
 * Created by Myth on 5/15/2019
 */
public class Input {
    public static BufferedReader getBufferReader() {
        File file = new File("src/input.txt");
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader;
    }
}
