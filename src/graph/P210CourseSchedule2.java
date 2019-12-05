package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Title: 210. 课程表2
 * Desc: 207题的进阶版本，依然是拓扑排序的两种写法
 * Created by Myth-Lab on 10/24/2019
 */
public class P210CourseSchedule2 {
    // 方法 1
    private int topoIndex = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 逆邻接表 (为什么使用你邻接表，使用的是后递归，多思考一下？如果不用，会是什么序列？)（方法2 使用的是邻接表）
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new LinkedList<>());
        }
        for (int[] pre : prerequisites) {
            List<Integer> list = adjList.get(pre[0]);
            list.add(pre[1]);
        }
        int[] topoSequence = new int[numCourses];
        int[] flag = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(adjList, i, topoSequence, flag)) return new int[0];
        }
        return topoSequence;
    }
    // DFS：判断是否有环 + 记录拓扑顺序,  Flag 记录当前课程是被当前 dfs（flag = -1） 搜索到还是被其他dfs搜索到(flag = 1)（检测环）
    private boolean dfs(List<List<Integer>> adjList, int course, int[] topoSequence, int[] flag) {
        if (flag[course] == 1) return false;
        if (flag[course] == -1) return true;  // 存在环
        List<Integer> adjCourses = adjList.get(course);
        flag[course] = -1;
        for (Integer adjCourse : adjCourses) {
            if (dfs(adjList, adjCourse, topoSequence, flag)) return true;
        }
        topoSequence[topoIndex++] = course;  // 保存拓扑序列
        flag[course] = 1;
        return false;
    }
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        Queue<Integer> zeroQueue = new LinkedList<>();  // 保存入度为 0 的节点
        int[] indegrees = new int[numCourses];  // 入度表
        List<List<Integer>> courses = new ArrayList<>(numCourses);  // 邻接表
        for (int i = 0; i < numCourses; i++) {
            courses.add(new LinkedList<>());
        }
        for (int[] ele : prerequisites) {
            indegrees[ele[0]]++;
            // 构造 邻接表  前修课 —— 后修课  （思考这里为什么用邻接表）
            List<Integer> prerequisite = courses.get(ele[1]);
            prerequisite.add(ele[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) zeroQueue.add(i);
        }
        int size = 0;  // 可以学习的课程数目
        int[] topoSequence = new int[numCourses];
        while (!zeroQueue.isEmpty()) {
            Integer zeroCourse = zeroQueue.poll();
            topoSequence[size] = zeroCourse;
            size++;
            List<Integer> preCourses = courses.get(zeroCourse);
            for (Integer course : preCourses) {
                if (--indegrees[course] == 0) zeroQueue.add(course);  // 更新入度表， 当节点入度为0的时候，加入队列
            }
        }
        if (size != numCourses) return new int[0];
        else return topoSequence;
    }
}
