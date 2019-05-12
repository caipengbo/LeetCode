//
// Created by Myth on 4/14/2019.
//
#include "pch.h"
// 带标记的排序实现
bool less_second(const vector<int> & m1, const vector<int> & m2) {
    return m1[2] < m2[2]; // 可以避免使用第三个元素，直接使用前面的两个值进行计算即可
}
bool sortfun(const vector<int> & m1, const vector<int> & m2) {
    return m1[0] - m1[1] < m2[0] - m2[1];
}
class Solution {
public:
    // [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
    int twoCitySchedCost(vector<vector<int>>& costs) {
        int N = costs.size();
        int sum = 0;
        sort(costs.begin(), costs.end(), sortfun);
        for (int i = 0; i < N / 2; ++i) {
            sum += costs[i][0];
        }
        for (int j = N / 2; j < N; ++j) {
            sum += costs[j][1];
        }
        return sum;
    }

    vector<vector<int>> allCellsDistOrder(int R, int C, int r0, int c0) {
        vector<vector<int>> temp;
        vector<vector<int>> ret;
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                int dis = abs(r0 - i) + abs(c0 - j);
                vector<int> vec = {i, j, dis};
                temp.push_back(vec);
            }
        }
        sort(temp.begin(), temp.end(), less_second);
        for(auto ele: temp) {
            vector<int> vec = {ele[0], ele[1]};
            ret.push_back(vec);
        }
        return ret;
    }
    // 使用lambda表达式（C++11） 改写第二题
    vector<vector<int>> allCellsDistOrder_new(int R, int C, int r0, int c0) {
        vector<vector<int>> ret;
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                int dis = abs(r0 - i) + abs(c0 - j);
                // vector<int> vec = {i, j};
                ret.push_back({i, j});
            }
        }
        sort(ret.begin(), ret.end(), [r0, c0](const vector<int> & m1, const vector<int> & m2){
            return abs(r0 - m1[0]) + abs(c0 - m1[1]) < abs(r0 - m2[0]) + abs(c0 - m2[1]);
        });
        return ret;
    }
};
int main()
{
    Solution s;

//    vector<vector<int>> v = s.allCellsDistOrder(1, 2, 0, 0);
//    for(auto ele: v) {
//        prettyPrintVector(ele);
//    }
    vector<vector<int>> v = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
    // vector<vector<int>> v = {{10,20},{30,200},{400,50},{30,20}};
    int n = s.twoCitySchedCost(v);
    cout << n << endl;
}