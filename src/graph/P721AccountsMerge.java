package graph;

import java.util.*;

/**
 * Title: 721. 账户合并（很棒的一个题目）
 * Desc: 如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。
 * 请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。
 *
 * Created by Myth-Lab on 10/27/2019
 */
public class P721AccountsMerge {
    // 难点：如何存储
    // 如何表示成图：谁是顶点？边是什么？
    // 注意路径压缩，并不都是指向祖先的！！！
    public String find(String mail, Map<String, String> disjoint) {
        while (!disjoint.get(mail).equals(mail)) {
            disjoint.put(disjoint.get(mail), disjoint.get(disjoint.get(mail)));
            mail = disjoint.get(mail);
        }
        return mail;
    }
    // 合并算法
    public void union(String mail1, String mail2, Map<String, String> disjoint) {
        String iParent = find(mail1, disjoint);
        String jParent = find(mail2, disjoint);
        if (!iParent.equals(jParent)) {
            disjoint.put(jParent, iParent);
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Mail: Name
        Map<String, String> mail2Name = new HashMap<>();
        // mail : parentMail
        Map<String, String> mailDisjoint = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                mail2Name.put(account.get(i), account.get(0));
                mailDisjoint.put(account.get(i), account.get(i));
            }
        }
        for (List<String> account : accounts) {
            String mail1 = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                String mail2 = account.get(i);
                union(mail1, mail2, mailDisjoint);
            }
        }
        // 要弄清楚 并查集中到底存的是什么！！
        // 难点：构造结果集 parentMail : Mail_list
        HashMap<String, TreeSet<String>> retMap = new HashMap<>();
        for (List<String> account : accounts) {
            String parentMail = find(account.get(1), mailDisjoint);
            TreeSet<String> mailSet = retMap.getOrDefault(parentMail, new TreeSet<>());
            for (int i = 1; i < account.size(); i++) {
                mailSet.add(account.get(i));
            }
            retMap.put(parentMail, mailSet);
        }
        List<List<String>> ret = new LinkedList<>();
        for (String parentMail : retMap.keySet()) {
            LinkedList<String> mails = new LinkedList<>(retMap.get(parentMail));
            mails.addFirst(mail2Name.get(parentMail));
            ret.add(mails);
        }
        return ret;
    }

    public static void main(String[] args) {
        P721AccountsMerge p721 = new P721AccountsMerge();
        List<String> acc1 = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
        List<String> acc2 = Arrays.asList("John", "johnnybravo@mail.com");
        List<String> acc3 = Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com");
        List<String> acc4 = Arrays.asList("Mary", "mary@mail.com");
        List<List<String>> accounts = new LinkedList<>();
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);
        accounts.add(acc4);
        List<String> acc11 = Arrays.asList("David","David0@m.co","David4@m.co","David3@m.co");
        List<String> acc22 = Arrays.asList("David","David5@m.co","David5@m.co","David0@m.co");
        List<String> acc33 = Arrays.asList("David","David1@m.co","David4@m.co","David0@m.co");
        List<String> acc44 = Arrays.asList("David","David0@m.co","David1@m.co","David3@m.co");
        List<String> acc55 = Arrays.asList("David","David4@m.co","David1@m.co","David3@m.co");
        List<List<String>> accounts2 = new LinkedList<>();
        accounts2.add(acc11);
        accounts2.add(acc22);
        accounts2.add(acc33);
        accounts2.add(acc44);
        accounts2.add(acc55);
        System.out.println(p721.accountsMerge(accounts));
    }
}
