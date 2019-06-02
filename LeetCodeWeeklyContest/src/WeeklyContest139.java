/**
 * Title:
 * Desc:
 * Created by Myth on 6/2/2019
 */
public class WeeklyContest139 {
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
    public static String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        if (len1 == 0 || len2 == 0) return "";

        if (len1 >= len2 ) {
            int mod = len1 % len2;
            if (str1.substring(0, len1-mod).equals())
        } else {

        }


    }

    public static void main(String[] args) {
        System.out.println(WeeklyContest139.gcdOfStrings("ABABAB", "ABAB"));
    }
}
