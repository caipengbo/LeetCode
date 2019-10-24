package graph;

import java.util.*;

/**
 * Title: 207. 课程表
 * Desc: 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * Created by Myth-Lab on 10/24/2019
 */
public class P207CourseSchedule {
    // 方法1 ： 使用DFS的方法解决，判断是否存在环！！！
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> course = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            course.add(new LinkedList<>());
        }
        // 构造逆邻接表 按照   后修课 —— 前修课  组织
        for(int[] ele : prerequisites) {
            List<Integer> prerequisite = course.get(ele[0]);
            prerequisite.add(ele[1]);
        }
        int[] flag = new int[numCourses];
        // 对于每一个课程
        for (int i = 0; i < numCourses; i++) {
            if (dfs(course, i, flag)) return false;
        }
        return true;
    }
    // DFS 判断图中是否存在环(有环肯定不能满足要求)  如果存在环，返回true
    // 利用flag判断当前节点访问(-1)的还是被其他节点访问的(1) 未被访问是 0
    private boolean dfs(List<List<Integer>> course, int i, int[] flag) {
        if (flag[i] == -1) return true;
        if (flag[i] == 1) return false;
        List<Integer> list = course.get(i);
        flag[i] = -1;  // 当前节点访问
        for (Integer node : list) {
            if (dfs(course, node, flag)) return true;
        }
        flag[i] = 1;  // 访问完毕
        return false;
    }
    // 方法2：拓扑排序 kahn 算法：每次取出入度为 0 的节点
    // 维护 入度表 和 一个 入度为0 的队列，每次都取出入度为0的节点，更新入度表， 当节点入度为0的时候，加入队列
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        Queue<Integer> zeroQueue = new LinkedList<>();  // 保存入度为 0 的节点
        int[] indegrees = new int[numCourses];  // 入度表
        List<List<Integer>> courses = new ArrayList<>(numCourses);  // 邻接表
        for (int i = 0; i < numCourses; i++) {
            courses.add(new LinkedList<>());
        }
        for (int[] ele : prerequisites) {
            indegrees[ele[0]]++;
            // 构造邻接表 按照   前修课 —— 后修课
            List<Integer> prerequisite = courses.get(ele[1]);
            prerequisite.add(ele[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) zeroQueue.add(i);
        }
        int size = 0;  // 可以学习的课程数目
        while (!zeroQueue.isEmpty()) {
            Integer zeroCourse = zeroQueue.poll();
            size++;
            List<Integer> preCourses = courses.get(zeroCourse);
            for (Integer course : preCourses) {
                if (--indegrees[course] == 0) zeroQueue.add(course);  // 更新入度表， 当节点入度为0的时候，加入队列
            }
        }
        return size == numCourses;
    }

}
