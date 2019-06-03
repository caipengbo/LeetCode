/**
 * Title:
 * Desc:
 * Created by Myth on 6/2/2019
 */
public class WeeklyContest139 {
    // 1071 题目
    public static int getRepet(String ss) {
        int len=ss.length();
        int next[]=new int [len+1];
        next[0]=-1;
        int i=0,j=-1;
        while(i<len)
        {
            if(j==-1||ss.charAt(i)==ss.charAt(j))
            {
                i++;
                j++;
                next[i]=j;
            }
            else  j=next[j];
        }
        if(len%(len-next[len])==0)
            return len/(len-next[len]);
        else
            return 0;

    }
    public static String gcdOfStrings2(String str1, String str2) {
        int rep1 = getRepet(str1);
        int rep2 = getRepet(str2);
        if (rep1 == 0 || rep2 == 0) return "";
        String str1Sub = str1.substring(0, (str1.length()/rep1));
        String str2Sub = str2.substring(0, (str2.length()/rep2));
        if (str1Sub.equals(str2Sub)) {
            return str1Sub;
        } else {
            return "";
        }
    }
    // 我的思路：去掉 两个串的前缀，剩下的部分，再次对比，直到一个串为空（规定），另一个就是GCD
    // 错误代码：
    public static String gcdOfStrings3(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        if (len1 == 0 || len2 == 0) return "";

        if (len1 >= len2 ) {
            return isRep(str1, str2);
        } else {
            return isRep(str2, str1);
        }

    }
    public static String isRep(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        int mod = len1 % len2;
        if (mod == 0) {
            for (int i = 0; i < len1; i+=len2) {
                if (!str1.substring(i, i+len2).equals(str2)) {
                    return "";
                }
            }
            return str2;
        }
        if (str1.substring(0, len1-mod).equals(str2)) {
            String sub = str1.substring(len1-mod, len1);
            int lenSub = sub.length();
            if (len2 % lenSub != 0) return "";
            else {
                for (int i = 0; i < len2; i+=lenSub) {
                    if (!str2.substring(i, i+lenSub).equals(sub)) {
                        return "";
                    }
                }
                return sub;
            }
        } else {
            return "";
        }
    }
    // 简洁正确的代码
    public static String gcdOfStrings1(String str1, String str2) {
        if (str1.length() < str2.length()) return gcdOfStrings1(str2, str1);
        else if (!str1.startsWith(str2)) return "";
        else if (str2.length() == 0) return str1;
        else return gcdOfStrings1(str1.substring(str2.length()), str2);
    }

    public static void main(String[] args) {
        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
        String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        System.out.println(WeeklyContest139.gcdOfStrings1(str1, str2));
    }
}
